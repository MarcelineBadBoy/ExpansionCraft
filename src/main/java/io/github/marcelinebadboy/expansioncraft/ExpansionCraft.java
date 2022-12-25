package io.github.marcelinebadboy.expansioncraft;

import com.mojang.logging.LogUtils;

import io.github.marcelinebadboy.expansioncraft.init.BlockEntityInit;
import io.github.marcelinebadboy.expansioncraft.init.BlockInit;
import io.github.marcelinebadboy.expansioncraft.init.ItemInit;
import io.github.marcelinebadboy.expansioncraft.init.MenuInit;
import io.github.marcelinebadboy.expansioncraft.init.RecipeInit;
import io.github.marcelinebadboy.expansioncraft.init.gui.FoundrySmelterScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ExpansionCraft.MODID)
public class ExpansionCraft
{
    public static final String MODID = "expansioncraft";
    
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LogUtils.getLogger();

    public ExpansionCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemInit.register(modEventBus);
        BlockInit.register(modEventBus);
        BlockEntityInit.register(modEventBus);
        
        MenuInit.register(modEventBus);
        
        RecipeInit.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        	MenuScreens.register(MenuInit.FOUNDRY_SMELTER_MENU.get(), FoundrySmelterScreen::new);
        }
    }
}