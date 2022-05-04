package mods.SufficientlyPositive.GoldToolsPlus.mixins.other;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Mixin class to alter the mining level of gold tools to be the same as stone tools.
 */
@Mixin(ToolMaterials.class)
public abstract class ToolMaterialsMixin implements ToolMaterial {

    @Final
    @Shadow
    private int miningLevel;

    @Final
    @Shadow
    private float miningSpeed;

    @Final
    @Shadow
    private float attackDamage;

    @Final
    @Shadow
    private int enchantability;

    @Final
    @Shadow
    private int itemDurability;

    @Final
    @Shadow
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Final
    @Shadow
    public static ToolMaterials GOLD;

    @Final
    @Shadow
    public static ToolMaterials STONE;

    /**
     * Sets the mining level of any mining material with the same statistics of gold
     * to stone's mining level.
     */
    public int getMiningLevel() {
        if (this.miningSpeed == GOLD.getMiningSpeedMultiplier()
                && this.enchantability == GOLD.getEnchantability()
                && this.attackDamage == GOLD.getAttackDamage()
                && this.itemDurability == GOLD.getDurability()
                && this.miningLevel == GOLD.getMiningLevel()
                && this.getRepairIngredient().equals(GOLD.getRepairIngredient())
        ) {
            return STONE.getMiningLevel();
        }
        return this.miningLevel;
    }
}
