package mods.SufficientlyPositive.GoldToolsPlus.CustomRecipies;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class InfuserRecipe implements Recipe<CraftingInventory> {

    private final Ingredient input1;
    private final Ingredient input2;
    private final Ingredient input3;
    private final Ingredient input4;
    private final Ingredient input5;

    private final ItemStack output;

    private final Identifier id;

    public Ingredient getInput1() {
        return input1;
    }
    public Ingredient getInput2() {
        return input2;
    }
    public Ingredient getInput3() {
        return input3;
    }
    public Ingredient getInput4() {
        return input4;
    }
    public Ingredient getInput5() {
        return input5;
    }

    public InfuserRecipe(Ingredient input1,
                         Ingredient input2,
                         Ingredient input3,
                         Ingredient input4,
                         Ingredient input5,
                         ItemStack output,
                         Identifier id) {
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.input4 = input4;
        this.input5 = input5;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        if (inventory.size() < 5) return false;
        return input1.test(inventory.getStack(0))
                && input2.test(inventory.getStack(1))
                && input3.test(inventory.getStack(2))
                && input4.test(inventory.getStack(3))
                && input5.test(inventory.getStack(4));
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
