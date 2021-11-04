package mods.SufficientlyPositive.GoldToolsPlus.game.recipes.serializers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.game.recipes.recipes.InfuserRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class InfuserRecipeSerializer implements RecipeSerializer<InfuserRecipe> {
    private InfuserRecipeSerializer() {}
    public static final InfuserRecipeSerializer INSTANCE = new InfuserRecipeSerializer();
    public static final Identifier ID = GoldToolsPlusHelperFunctions.newID("infuser_recipe");

    @Override
    public InfuserRecipe read(Identifier id, JsonObject json) {
        InfuserRecipeJsonFormat recipeJson = new Gson().fromJson(json, InfuserRecipeJsonFormat.class);

        //checking
        if(recipeJson.input1 == null
                || recipeJson.input2 == null
                || recipeJson.input3 == null
                || recipeJson.input4 == null
                || recipeJson.input5 == null
                || recipeJson.outputItem == null
        ) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;

        Ingredient input1 = Ingredient.fromJson(recipeJson.input1);
        Ingredient input2 = Ingredient.fromJson(recipeJson.input2);
        Ingredient input3 = Ingredient.fromJson(recipeJson.input3);
        Ingredient input4 = Ingredient.fromJson(recipeJson.input4);
        Ingredient input5 = Ingredient.fromJson(recipeJson.input5);

        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new InfuserRecipe(input1, input2, input3, input4, input5, output, id);
    }

    @Override
    public InfuserRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient input1 = Ingredient.fromPacket(buf);
        Ingredient input2 = Ingredient.fromPacket(buf);
        Ingredient input3 = Ingredient.fromPacket(buf);
        Ingredient input4 = Ingredient.fromPacket(buf);
        Ingredient input5 = Ingredient.fromPacket(buf);
        ItemStack output = buf.readItemStack();
        return new InfuserRecipe(input1, input2, input3, input4, input5, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, InfuserRecipe recipe) {
        recipe.getInput1().write(buf);
        recipe.getInput2().write(buf);
        recipe.getInput3().write(buf);
        recipe.getInput4().write(buf);
        recipe.getInput5().write(buf);
        buf.writeItemStack(recipe.getOutput());
    }
}
