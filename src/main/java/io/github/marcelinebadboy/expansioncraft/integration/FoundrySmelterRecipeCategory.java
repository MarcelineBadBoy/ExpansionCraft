package io.github.marcelinebadboy.expansioncraft.integration;

import io.github.marcelinebadboy.expansioncraft.ExpansionCraft;
import io.github.marcelinebadboy.expansioncraft.init.BlockInit;
import io.github.marcelinebadboy.expansioncraft.init.recipes.FoundrySmelterRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FoundrySmelterRecipeCategory implements IRecipeCategory<FoundrySmelterRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ExpansionCraft.MODID, "foundry_smelter");
    public final static ResourceLocation TEXTURE = new ResourceLocation(ExpansionCraft.MODID, "textures/gui/foundry_smelter_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public FoundrySmelterRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 4, 4, 168, 97);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.FOUNDRY_SMELTER.get()));
    }

    @Override
    public RecipeType<FoundrySmelterRecipe> getRecipeType() {
        return JEIPlugin.FOUNDRY_SMELTER_TYPE;
    }

    //Name displayed in the gui -> Needs to be changed
    @Override
    public Component getTitle() {
        return Component.literal("Foundry Smelting");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FoundrySmelterRecipe recipe, IFocusGroup focuses) {
        if(recipe.getIngredients().size() == 1) {
            builder.addSlot(RecipeIngredientRole.INPUT, 35, 14).addIngredients(recipe.getIngredients().get(0));

            builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 51).addItemStack(recipe.getResultItem());
        }

        if(recipe.getIngredients().size() == 2) {
            builder.addSlot(RecipeIngredientRole.INPUT, 35, 14).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.INPUT, 53, 14).addIngredients(recipe.getIngredients().get(1));

            builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 51).addItemStack(recipe.getResultItem());
        }

        if(recipe.getIngredients().size() == 3) {
            builder.addSlot(RecipeIngredientRole.INPUT, 35, 14).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.INPUT, 53, 14).addIngredients(recipe.getIngredients().get(1));
            builder.addSlot(RecipeIngredientRole.INPUT, 35, 32).addIngredients(recipe.getIngredients().get(2));

            builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 51).addItemStack(recipe.getResultItem());
        }

        if(recipe.getIngredients().size() == 4) {
            builder.addSlot(RecipeIngredientRole.INPUT, 35, 14).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.INPUT, 53, 14).addIngredients(recipe.getIngredients().get(1));
            builder.addSlot(RecipeIngredientRole.INPUT, 35, 32).addIngredients(recipe.getIngredients().get(2));
            builder.addSlot(RecipeIngredientRole.INPUT, 53, 32).addIngredients(recipe.getIngredients().get(3));

            builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 51).addItemStack(recipe.getResultItem());
        }

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 44, 68).addItemStack(new ItemStack(Items.LAVA_BUCKET));
    }
}
