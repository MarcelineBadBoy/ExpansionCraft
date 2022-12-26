package io.github.marcelinebadboy.expansioncraft.init.util;

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
        
        //Creating tags
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ExpansionCraft.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}