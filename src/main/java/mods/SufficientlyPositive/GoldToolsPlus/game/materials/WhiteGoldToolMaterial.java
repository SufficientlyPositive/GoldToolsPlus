package mods.SufficientlyPositive.GoldToolsPlus.game.materials;

import mods.SufficientlyPositive.GoldToolsPlus.init.ItemsInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

/**
 * Values for the White Gold tool material.
 */
public class WhiteGoldToolMaterial implements ToolMaterial {

    /**
     * Public instance of the material for creating tool instances.
     */
    public static final WhiteGoldToolMaterial INSTANCE = new WhiteGoldToolMaterial();

    /**
     * Durability and attack damage values of the material.
     */
    private final int durability;
    private final float attackDamage;

    /**
     * Constructor for the material, sets durability to 1.5x normal Gold Tool durability.
     * Sets attack damage to 1 beyond Gold's attack damage.
     */
    WhiteGoldToolMaterial() {
        this.durability = (int) (1.5F * ToolMaterials.GOLD.getDurability());
        this.attackDamage = 1.0F + ToolMaterials.GOLD.getAttackDamage();
    }

    /**
     * Getter for the durability of any white_gold tools.
     * @return the durability value, should be 1.5x the gold tool's durability
     */
    @Override
    public int getDurability() {
        return this.durability;
    }

    /**
     * Getter for the mining speed of the tool material.
     * @return 15.0, needs to be this high for beacon mining of deepslate.
     */
    @Override
    public float getMiningSpeedMultiplier() {
        return 15.0F;
    }

    /**
     * Getter for the attack damage of any white_gold tools.
     * @return the attack damage value, should be gold material's attack damage + 1.
     */
    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    /**
     * Getter for the mining level of white_gold, is the same as stone: 1.
     * @return 1, the mining level of stone tools.
     */
    @Override
    public int getMiningLevel() {
        return 1;
    }

    /**
     * Getter for the enchantability value, very high for white_gold, gold: 22.
     * @return 35, very high enchantability value.
     */
    @Override
    public int getEnchantability() {
        return 35;
    }

    /**
     * Getter for repair ingredient in crafting table/anvil
     * @return instance of WHITE_GOLD_INGOT.
     */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ItemsInit.WHITE_GOLD_INGOT);
    }
}
