package mods.SufficientlyPositive.GoldToolsPlus.game.recipes.recipes;

import mods.SufficientlyPositive.GoldToolsPlus.game.recipes.serializers.InfuserRecipeSerializer;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class InfuserRecipe implements Recipe<CraftingInventory> {

    private final Ingredient blockInput1;
    private final Ingredient blockInput2;
    private final Ingredient ingotInput1;
    private final Ingredient ingotInput2;
    private final Ingredient shardInput;

    private final ItemStack output;

    private final Identifier id;

    public Ingredient getBlockInput1() {
        return blockInput1;
    }
    public Ingredient getBlockInput2() {
        return blockInput2;
    }
    public Ingredient getIngotInput1() {
        return ingotInput1;
    }
    public Ingredient getIngotInput2() {
        return ingotInput2;
    }
    public Ingredient getShardInput() {
        return shardInput;
    }

    public InfuserRecipe(Ingredient input1,
                         Ingredient input2,
                         Ingredient input3,
                         Ingredient input4,
                         Ingredient input5,
                         ItemStack output,
                         Identifier id) {
        this.blockInput1 = input1;
        this.blockInput2 = input2;
        this.ingotInput1 = input3;
        this.ingotInput2 = input4;
        this.shardInput = input5;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        if (inventory.size() < 5) return false;
        return blockInput1.test(inventory.getStack(0))
                && blockInput2.test(inventory.getStack(1))
                && ingotInput1.test(inventory.getStack(2))
                && ingotInput2.test(inventory.getStack(3))
                && shardInput.test(inventory.getStack(4));
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        return this.getOutput().copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return InfuserRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<InfuserRecipe> {
        // Define ExampleRecipe.Type as a singleton by making its constructor private and exposing an instance.
        private Type() {}
        public static final Type INSTANCE = new Type();

        // This will be needed in step 4
        public static final String ID = "infuser_recipe";
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

}
