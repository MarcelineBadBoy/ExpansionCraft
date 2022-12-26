package io.github.marcelinebadboy.expansioncraft.init.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.marcelinebadboy.expansioncraft.ExpansionCraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import org.jetbrains.annotations.Nullable;

public class FoundrySmelterRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public FoundrySmelterRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }
    
    //Checking if the recipe and the slot inputs are the same
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }
        
        boolean ingredient[] = new boolean[recipeItems.size()];
        for (int i = 0; i < recipeItems.size(); i++) {
        	ingredient[i] = false;
        }
        
        boolean slot[] = new boolean[5];
        for (int i = 1; i < 5; i++) {
        	slot[i] = true;
        }

        for (int i = 0; i < recipeItems.size(); i++) {
        	for (int l = 1; l < 5; l++) {
        		if(slot[l] == true && ingredient[i] == false) {
        			if(recipeItems.get(i).test(pContainer.getItem(l))) {
        				ingredient[i] = true;
            			slot[l] = false;
            		}
        		}
        	}
        }
        
        boolean returner = false;
        
        for (int i = 0; i < recipeItems.size(); i++) {
        	if(ingredient[i] == true) {
        		returner = true;
        	} else {
        		returner = false;
        	}
        }
        
        //Checking if the rest of the slots are empty
        for (int l = 1; l < 5; l++) {
    		if(slot[l] == true) {
    			if(pContainer.getItem(l).getItem() != Blocks.AIR.asItem()) {
    				returner = false;
        		}
    		}
    	}
        return returner;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    
    //Registers recipe type
    public static class Type implements RecipeType<FoundrySmelterRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "foundry_smelter";
    }

    //Registers new recipes on the client and server side
    public static class Serializer implements RecipeSerializer<FoundrySmelterRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(ExpansionCraft.MODID, "foundry_smelter");

        @Override
        public FoundrySmelterRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
            
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(ingredients.size(), Ingredient.EMPTY);

            System.out.print("TEST: " + pRecipeId + "\n");
            
            for (int i = 0; i < inputs.size(); i++) {
            	System.out.print("INGREDIENT: " + ingredients.get(i) + "\n" + Ingredient.fromJson(ingredients.get(i)) + "\n");
            	inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            
            return new FoundrySmelterRecipe(pRecipeId, output, inputs);
        }
        
        @Override
        public @Nullable FoundrySmelterRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }
            
            ItemStack output = buf.readItem();
            return new FoundrySmelterRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FoundrySmelterRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
  
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}