package io.github.marcelinebadboy.expansioncraft.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTab {
	public static final CreativeModeTab EXPANSION_CRAFT_TAB = new CreativeModeTab("ectab") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.LIFE_CRYSTAL.get());
		}
	};
}
