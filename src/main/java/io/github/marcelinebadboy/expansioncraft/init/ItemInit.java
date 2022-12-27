package io.github.marcelinebadboy.expansioncraft.init;

import io.github.marcelinebadboy.expansioncraft.ExpansionCraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = 
			DeferredRegister.create(ForgeRegistries.ITEMS, ExpansionCraft.MODID);
	
	//Items
	public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> LIFE_CRYSTAL = ITEMS.register("life_crystal", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> RAW_DIAMOND = ITEMS.register("raw_diamond", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> RAW_EMERALD = ITEMS.register("raw_emerald", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> RAW_LIFE_CRYSTAL = ITEMS.register("raw_life_crystal", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> RAW_RUBY = ITEMS.register("raw_ruby", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> RAW_TOPAZ = ITEMS.register("raw_topaz", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> TOPAZ = ITEMS.register("topaz", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	
	public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> BRASS_INGOT = ITEMS.register("brass_ingot", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> GUNMETAL_INGOT = ITEMS.register("gunmetal_ingot", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> ROSE_GOLD_INGOT = ITEMS.register("rose_gold_ingot", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	public static final RegistryObject<Item> NETHERITE_SCRAP_INGOT = ITEMS.register("netherite_scrap_ingot", 
			() -> new Item(new Item.Properties().tab(CreativeTab.EXPANSION_CRAFT_TAB)));
	
	//Registering
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}