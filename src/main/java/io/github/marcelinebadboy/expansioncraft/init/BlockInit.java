package io.github.marcelinebadboy.expansioncraft.init;

import io.github.marcelinebadboy.expansioncraft.ExpansionCraft;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ExpansionCraft.MODID);

    //Blocks
    public static final RegistryObject<Block> LIFE_CRYSTAL_ORE = registerBlock("life_crystal_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
    
	public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> TOPAZ_ORE = registerBlock("topaz_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	
	public static final RegistryObject<Block> RAW_DIAMOND_BLOCK = registerBlock("raw_diamond_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_EMERALD_BLOCK = registerBlock("raw_emerald_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_LIFE_CRYSTAL_BLOCK = registerBlock("raw_life_crystal_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_RUBY_BLOCK = registerBlock("raw_ruby_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_TOPAZ_BLOCK = registerBlock("raw_topaz_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	
	public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> LIFE_CRYSTAL_BLOCK = registerBlock("life_crystal_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> TOPAZ_BLOCK = registerBlock("topaz_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	//Registering block
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    
    //Registering items
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    
    //Registering 
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}