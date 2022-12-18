package io.github.marcelinebadboy.expansioncraft;

import io.github.marcelinebadboy.expansioncraft.init.BlockInit;
import io.github.marcelinebadboy.expansioncraft.init.ItemInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExpansionCraft.MODID)
public class ExpansionCraft {
	public static final String MODID = "expansioncraft";
	
	public ExpansionCraft() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		BlockInit.BLOCKS.register(bus);
		ItemInit.ITEMS.register(bus);
	}
}
