package io.github.marcelinebadboy.expansioncraft.init;

import io.github.marcelinebadboy.expansioncraft.ExpansionCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsInit {
	//Block tags
    public static class Blocks {
    	
    	//Creating tags
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ExpansionCraft.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
    
    //Item tags
    public static class Items {
        public static final TagKey<Item> IRON_SMELTING_MATERIALS = tag("iron_smelting_materials");
        public static final TagKey<Item> COPPER_SMELTING_MATERIALS = tag("copper_smelting_materials");
        public static final TagKey<Item> GOLD_SMELTING_MATERIALS = tag("gold_smelting_materials");
        public static final TagKey<Item> TIN_SMELTING_MATERIALS = tag("tin_smelting_materials");
        public static final TagKey<Item> ZINC_SMELTING_MATERIALS = tag("zinc_smelting_materials");
        
        public static final TagKey<Item> IRON_ALLOY = tag("iron_alloy");
        public static final TagKey<Item> COPPER_ALLOY = tag("copper_alloy");
        public static final TagKey<Item> GOLD_ALLOY = tag("gold_alloy");
        public static final TagKey<Item> TIN_ALLOY = tag("tin_alloy");
        public static final TagKey<Item> ZINC_ALLOY = tag("zinc_alloy");
        
        public static final TagKey<Item> REDSTONE_ALLOY = tag("redstone_alloy");
        public static final TagKey<Item> NETHERITE_ALLOY = tag("netherite_alloy");
        
        //Creating tags
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ExpansionCraft.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}