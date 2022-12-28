package io.github.marcelinebadboy.expansioncraft.init;

import io.github.marcelinebadboy.expansioncraft.ExpansionCraft;
import io.github.marcelinebadboy.expansioncraft.init.blocks.custom.FoundrySmelterBlock;
import io.github.marcelinebadboy.expansioncraft.init.blocks.custom.NetheriteScrapBlock;
import io.github.marcelinebadboy.expansioncraft.init.blocks.custom.TinBlock;
import net.minecraft.world.level.block.SoundType;
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
    
    //Block entities
    public static final RegistryObject<Block> FOUNDRY_SMELTER = registerBlock("foundry_smelter", 
			() -> new FoundrySmelterBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE_TILES).strength(4f).requiresCorrectToolForDrops()
					.lightLevel(state -> state.getValue(FoundrySmelterBlock.LIT) ? 15 : 0)),
					CreativeTab.EXPANSION_CRAFT_TAB);
    //Blocks
    public static final RegistryObject<Block> LIFE_CRYSTAL_ORE = registerBlock("life_crystal_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3f).sound(SoundType.STONE).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
    
    public static final RegistryObject<Block> DEEPSLATE_LIFE_CRYSTAL_ORE = registerBlock("deepslate_life_crystal_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
    
	public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> TOPAZ_ORE = registerBlock("topaz_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> ZINC_ORE = registerBlock("zinc_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = registerBlock("deepslate_zinc_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE).strength(4.5f).requiresCorrectToolForDrops(), 
					UniformInt.of(3, 7)), CreativeTab.EXPANSION_CRAFT_TAB);
	
	
	public static final RegistryObject<Block> RAW_DIAMOND_BLOCK = registerBlock("raw_diamond_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_EMERALD_BLOCK = registerBlock("raw_emerald_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_LIFE_CRYSTAL_BLOCK = registerBlock("raw_life_crystal_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_RUBY_BLOCK = registerBlock("raw_ruby_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_TOPAZ_BLOCK = registerBlock("raw_topaz_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RAW_ZINC_BLOCK = registerBlock("raw_zinc_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	
	public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> LIFE_CRYSTAL_BLOCK = registerBlock("life_crystal_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> TOPAZ_BLOCK = registerBlock("topaz_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", 
			() -> new TinBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> ZINC_BLOCK = registerBlock("zinc_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> BRASS_BLOCK = registerBlock("brass_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> GUNMETAL_BLOCK = registerBlock("gunmetal_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> ROSE_GOLD_BLOCK = registerBlock("rose_gold_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
	public static final RegistryObject<Block> NETHERITE_SCRAP_BLOCK = registerBlock("netherite_scrap_block", 
			() -> new NetheriteScrapBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(40f).requiresCorrectToolForDrops()), CreativeTab.EXPANSION_CRAFT_TAB);
	
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