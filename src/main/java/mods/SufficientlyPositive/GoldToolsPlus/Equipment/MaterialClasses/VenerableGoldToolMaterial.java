package mods.SufficientlyPositive.GoldToolsPlus.Equipment.MaterialClasses;

import mods.SufficientlyPositive.GoldToolsPlus.init.GTPItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

public class VenerableGoldToolMaterial implements ToolMaterial {

    public static final VenerableGoldToolMaterial INSTANCE = new VenerableGoldToolMaterial();

    private final int durability;
    private final float attackDamage;

    VenerableGoldToolMaterial() {
        this.durability = (int) (1.5F * ToolMaterials.GOLD.getDurability());
        this.attackDamage = 1.0F + ToolMaterials.GOLD.getAttackDamage();
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 15.0F;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 35;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(GTPItemsInit.VENERABLE_GOLD_INGOT);
    }
}
