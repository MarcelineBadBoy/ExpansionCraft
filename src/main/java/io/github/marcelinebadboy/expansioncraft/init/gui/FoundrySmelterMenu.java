package io.github.marcelinebadboy.expansioncraft.init.gui;

import io.github.marcelinebadboy.expansioncraft.init.BlockInit;
import io.github.marcelinebadboy.expansioncraft.init.MenuInit;
import io.github.marcelinebadboy.expansioncraft.init.blocks.entity.FoundrySmelterEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class FoundrySmelterMenu extends AbstractContainerMenu {
    public final FoundrySmelterEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public FoundrySmelterMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(5));
    }
    
    //Registering all the slots
    public FoundrySmelterMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(MenuInit.FOUNDRY_SMELTER_MENU.get(), id);
        //Amount of slots       \/
        checkContainerSize(inv, 6);
        blockEntity = (FoundrySmelterEntity) entity;
        this.level = inv.player.level;
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        
        //Slots and their positions
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
        	this.addSlot(new SlotItemHandler(handler, 0, 48, 62));
            this.addSlot(new SlotItemHandler(handler, 1, 39, 8));
            this.addSlot(new SlotItemHandler(handler, 2, 57, 8));
            this.addSlot(new SlotItemHandler(handler, 3, 39, 26));
            this.addSlot(new SlotItemHandler(handler, 4, 57, 26));
            this.addSlot(new SlotItemHandler(handler, 5, 117, 45));
        });
        addDataSlots(data);
    }

    //Check if FoundrySmelterEntity is crafting something
    public boolean isCrafting() {
        return data.get(0) > 0;
    }
    
    //Check if FoundrySmelterEntity has fuel
    public boolean isSmelting() {
        return data.get(2) > 0;
    }
    
    //Check if FoundrySmelterEntity has fuel slot is empty
    public boolean isEmpty() {
        return this.getSlot(36).hasItem();
    }
    
    //Fancy rendering of the part of the arrow
    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 23;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
    
  //Fancy rendering of the part of the flame
    public int getScaledSmeltingProgress() {
        int smeltingProgress = this.data.get(2);
        int maxSmeltingProgress = this.data.get(3);
        int progressFireSize = 12;
        
        return maxSmeltingProgress != 0 && smeltingProgress != 0 ? smeltingProgress * progressFireSize / maxSmeltingProgress : 0;
    }
    
    //Credit goes to: diesieben07 | https://github.com/diesieben07/SevenCommons
    //---
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 6;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
    //---

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, BlockInit.FOUNDRY_SMELTER.get());
    }

    //Assign slots from the players inventory to the menu
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 93 + i * 18));
            }
        }
    }
    
    //Assign slots from the players hotbar to the menu
    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 151));
        }
    }
}
