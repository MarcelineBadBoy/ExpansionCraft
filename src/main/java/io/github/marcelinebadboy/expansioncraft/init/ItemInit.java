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
	
	//Registering
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}