package io.github.marcelinebadboy.expansioncraft.integration;

import io.github.marcelinebadboy.expansioncraft.ExpansionCraft;
import io.github.marcelinebadboy.expansioncraft.init.BlockInit;
import io.github.marcelinebadboy.expansioncraft.init.recipes.FoundrySmelterRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static RecipeType<FoundrySmelterRecipe> FOUNDRY_SMELTER_TYPE = new RecipeType<>(FoundrySmelterRecipeCategory.UID, FoundrySmelterRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ExpansionCraft.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FoundrySmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BlockInit.FOUNDRY_SMELTER.get()), FOUNDRY_SMELTER_TYPE);
    }

    //Recipe registration
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<FoundrySmelterRecipe> recipesSmelting = rm.getAllRecipesFor(FoundrySmelterRecipe.Type.INSTANCE);
        registration.addRecipes(FOUNDRY_SMELTER_TYPE, recipesSmelting);
    }
}
