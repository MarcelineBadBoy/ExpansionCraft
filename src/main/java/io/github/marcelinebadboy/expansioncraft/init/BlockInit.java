package io.github.marcelinebadboy.expansioncraft.init;

import io.github.marcelinebadboy.expansioncraft.ExpansionCraft;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExpansionCraft.MODID);

	public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST)));
	
	// POSZUKAJ DOKUmentacji I SPRÓBUJ OD NOWA FILMIK
	
}