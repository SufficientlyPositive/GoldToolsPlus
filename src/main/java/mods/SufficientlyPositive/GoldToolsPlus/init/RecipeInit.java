package mods.SufficientlyPositive.GoldToolsPlus.init;

import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.game.recipes.recipes.InfuserRecipe;
import mods.SufficientlyPositive.GoldToolsPlus.game.recipes.serializers.InfuserRecipeSerializer;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;

public class RecipeInit {
    public static final RecipeType<InfuserRecipe> INFUSER_RECIPE_TYPE;
    public static final RecipeSerializer<InfuserRecipe> INFUSER_RECIPE_SERIALIZER;

    static {
        INFUSER_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(GoldToolsPlus.MOD_ID, InfuserRecipe.Type.ID), InfuserRecipe.Type.INSTANCE);
        INFUSER_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, InfuserRecipeSerializer.ID, InfuserRecipeSerializer.INSTANCE);
    }

    public static void init() {
        GoldToolsPlusHelperFunctions.log(Level.INFO, "Recipes registered.");
    }
}
