package io.github.marcelinebadboy.expansioncraft.init.blocks.entity;

import io.github.marcelinebadboy.expansioncraft.init.BlockEntityInit;
import io.github.marcelinebadboy.expansioncraft.init.blocks.custom.FoundrySmelterBlock;
import io.github.marcelinebadboy.expansioncraft.init.recipes.FoundrySmelterRecipe;
import io.github.marcelinebadboy.expansioncraft.init.gui.FoundrySmelterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class FoundrySmelterEntity extends BlockEntity implements MenuProvider {
	private final ItemStackHandler itemHandler = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
        
        //Only selected items can go to specific slot
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getItem() == Items.LAVA_BUCKET ||
                		stack.getItem() == Items.BUCKET;
                case 1 -> true; //true = all
                case 2 -> true; 
                case 3 -> true;
                case 4 -> true; 
                case 5 -> false; //false = none -> you can only take form that slot, and only system can place item there
                default -> super.isItemValid(slot, stack);
            };
        }
    };
    
    //Registering witch side of the block is responsible for witch slot (or multiple slots)
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 5 ||
            				(i == 0 && itemHandler.getStackInSlot(0).getItem() == Items.BUCKET), (i, s) -> false)),
            		Direction.UP, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 0,
            				(i, stack) -> itemHandler.isItemValid(0, stack))),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                            (i, stack) -> itemHandler.isItemValid(1, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2,
                    		(i, stack) -> itemHandler.isItemValid(2, stack))),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 3,
                            (i, stack) -> itemHandler.isItemValid(3, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 4,
                            (i, stack) -> itemHandler.isItemValid(4, stack))));
    
    //Registering some values need for crafting and nbt data
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 350;
    private int smeltingProgress = 0;
    private int maxSmeltingProgress = 0;
    private int exp = 0;
    
    //Assigning specific values to specific index
    //FoundrySmelterMenu used this to register if arrow and fire can be render
    public FoundrySmelterEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.FOUNDRY_SMELTER.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> FoundrySmelterEntity.this.progress;
                    case 1 -> FoundrySmelterEntity.this.maxProgress;
                    case 2 -> FoundrySmelterEntity.this.smeltingProgress;
                    case 3 -> FoundrySmelterEntity.this.maxSmeltingProgress;
                    case 4 -> FoundrySmelterEntity.this.exp;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> FoundrySmelterEntity.this.progress = value;
                    case 1 -> FoundrySmelterEntity.this.maxProgress = value;
                    case 2 -> FoundrySmelterEntity.this.smeltingProgress = value;
                    case 3 -> FoundrySmelterEntity.this.maxSmeltingProgress = value;
                    case 4 -> FoundrySmelterEntity.this.exp = value;
                }
            }

            @Override
            public int getCount() {
                return 5; //Must be the number of cases
            }
        };
    }
    
    //Name displayed in the container -> Needs to be changed
    @Override
    public Component getDisplayName() {
        return Component.literal("Foundry Smelter");
    }
    
    //Adds menu
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new FoundrySmelterMenu(id, inventory, this, this.data);
    }
    
    //Uses the knowledge about the sides of the block to map them correctly for the world
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
    	if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(FoundrySmelterBlock.FACING);

                if(side == Direction.UP || side == Direction.DOWN) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    
    //Saves information about the state of the block in case of chunk or world unloading
    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("foundry_smelter.progress", this.progress);
        nbt.putInt("foundry_smelter.smeltingProgress", this.smeltingProgress);
        nbt.putInt("foundry_smelter.maxSmeltingProgress", this.maxSmeltingProgress);
        //SAVE EXP

        super.saveAdditional(nbt);
    }
    
    //Loads saved nbt data
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("foundry_smelter.progress");
        smeltingProgress = nbt.getInt("foundry_smelter.smeltingProgress");
        maxSmeltingProgress = nbt.getInt("foundry_smelter.maxSmeltingProgress");
    }
    
    //Drops the content of the menu on destruction
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    
    //Tick
    public static void tick(Level level, BlockPos pos, BlockState state, FoundrySmelterEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }
        
        if(pEntity.smeltingProgress > 0) {
        	FoundrySmelterBlock.isSmelting(state, level, pos, true);
        	pEntity.smeltingProgress--;
        }		
        
        if(hasRecipe(pEntity)) {
        	if(pEntity.smeltingProgress > 0) {
        		pEntity.progress++;
        		setChanged(level, pos, state);
        	}
        	
        	if(pEntity.smeltingProgress == 0 && hasFuel(pEntity)) {
        		useFuel(pEntity);
        	}
        	
        	if(pEntity.smeltingProgress == 0 && pEntity.progress > 0) {
        		FoundrySmelterBlock.isSmelting(state, level, pos, false);
        		pEntity.progress--;
        	}
        	
            if(pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            setChanged(level, pos, state);
        }
    }
    
    //Reset crafting progress
    private void resetProgress() {
        this.progress = 0;
    }
    
    //Crafts item
    private static void craftItem(FoundrySmelterEntity pEntity) {
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for (int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }

        Optional<FoundrySmelterRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(FoundrySmelterRecipe.Type.INSTANCE, inventory, level);

        if(hasRecipe(pEntity)) {
            pEntity.itemHandler.extractItem(1, 1, false);
            pEntity.itemHandler.extractItem(2, 1, false);
            pEntity.itemHandler.extractItem(3, 1, false);
            pEntity.itemHandler.extractItem(4, 1, false);
            pEntity.itemHandler.setStackInSlot(5, new ItemStack(recipe.get().getResultItem().getItem(),
                    pEntity.itemHandler.getStackInSlot(5).getCount() + recipe.get().getResultItem().getCount()));
            
            pEntity.resetProgress();
            pEntity.exp += 0.7f;
        }
    }
    
    //Check if recipe is correct
    private static boolean hasRecipe(FoundrySmelterEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<FoundrySmelterRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(FoundrySmelterRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem());
    }
    
    //Checks for fuel
    private static boolean hasFuel(FoundrySmelterEntity entity) {
    	boolean returner = false;
    	if(entity.itemHandler.getStackInSlot(0).getCount() > 0) {
    		if(entity.itemHandler.getStackInSlot(0).getItem() == Items.LAVA_BUCKET ||
    				entity.itemHandler.getStackInSlot(0).getItem() == Blocks.COAL_BLOCK.asItem() ||
    				entity.itemHandler.getStackInSlot(0).getItem() == Items.BLAZE_ROD ||
    				entity.itemHandler.getStackInSlot(0).getItem() == Items.COAL ||
    				entity.itemHandler.getStackInSlot(0).getItem() == Items.CHARCOAL) { 
    			returner = true;
    		}
        }
    	return returner;
    }
    
    //Uses the fuel
    private static void useFuel(FoundrySmelterEntity entity) {
    	SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        
        if(entity.itemHandler.getStackInSlot(0).getItem() == Items.LAVA_BUCKET) {
        	entity.itemHandler.extractItem(0, 1, false);
        	entity.itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET, 1));
        	entity.smeltingProgress = 20000;
        	entity.maxSmeltingProgress = 20000;
        }
    }
    
    //Check if there is correct item in the output slot
    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(5).getItem() == stack.getItem() || inventory.getItem(5).isEmpty();
    }
    
    //Check if there is enough space for the item
    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(5).getMaxStackSize() > inventory.getItem(5).getCount();
    }
}
