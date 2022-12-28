package io.github.marcelinebadboy.expansioncraft.init.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.marcelinebadboy.expansioncraft.ExpansionCraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FoundrySmelterScreen extends AbstractContainerScreen<FoundrySmelterMenu> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(ExpansionCraft.MODID,"textures/gui/foundry_smelter_gui.png");
	
	public FoundrySmelterScreen(FoundrySmelterMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }
	
	@Override
    protected void init() {
        super.init();
    }
	
	
	//Rendering background
	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        renderLavaBucketSlot(pPoseStack, x, y);
        renderProgressArrow(pPoseStack, x, y);
        renderSmeltingProgress(pPoseStack, x, y);
	}
	
	//Rendering progress arrow
	private void renderProgressArrow(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 99, y + 16, 177, 14, menu.getScaledProgress(), 17);
        }
    }
	
	//Rendering fuel amount
	private void renderSmeltingProgress(PoseStack pPoseStack, int x, int y) {
        if(menu.isSmelting()) {
        	blit(pPoseStack, x + 48, y + 36, 176, 0, 14, 14);
            blit(pPoseStack, x + 48, y + 36, 48, 36, 14, 12-menu.getScaledSmeltingProgress());
        }
    }
	
	//Rendering fuel amount
		private void renderLavaBucketSlot(PoseStack pPoseStack, int x, int y) {
	        if(menu.isEmpty() == false) {
	        	blit(pPoseStack, x + 48, y + 53, 176, 31, 16, 16);
	        }
	    }
	
	//Rendering
    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
