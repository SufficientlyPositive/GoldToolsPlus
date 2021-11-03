package mods.SufficientlyPositive.GoldToolsPlus.init;

import mods.SufficientlyPositive.GoldToolsPlus.CustomBlocks.InfuserScreenHandler;
import mods.SufficientlyPositive.GoldToolsPlus.CustomRecipies.InfuserRecipe;
import mods.SufficientlyPositive.GoldToolsPlus.CustomRecipies.InfuserRecipeSerializer;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;


public class GTPInfuserInit {

    public static final RecipeType<InfuserRecipe> INFUSER_RECIPE_TYPE;
    public static final RecipeSerializer<InfuserRecipe> INFUSER_RECIPE_SERIALIZER;
    public static final ScreenHandlerType<InfuserScreenHandler> INFUSER_SCREEN_HANDLER;

    static {
        INFUSER_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(GoldToolsPlus.MOD_ID, InfuserRecipe.Type.ID), InfuserRecipe.Type.INSTANCE);
        INFUSER_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, InfuserRecipeSerializer.ID, InfuserRecipeSerializer.INSTANCE);

        INFUSER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(GoldToolsPlus.MOD_ID, "infuser"), InfuserScreenHandler::new);
    }

    public static void init() {
        GoldToolsPlus.log(Level.INFO, "Initialising Infuser screens/recipes");
    }
}