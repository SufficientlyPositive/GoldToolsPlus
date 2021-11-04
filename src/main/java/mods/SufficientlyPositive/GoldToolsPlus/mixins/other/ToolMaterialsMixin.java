package mods.SufficientlyPositive.GoldToolsPlus.mixins.other;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

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
    public static ToolMaterials GOLD;

    @Final
    @Shadow
    public static ToolMaterials STONE;

    /**
     * Extremely hacky way of upgrading gold mining level to stone
     */
    public int getMiningLevel() {
        if (this.miningSpeed == GOLD.getMiningSpeedMultiplier()
                && this.enchantability == GOLD.getEnchantability()
                && this.attackDamage == GOLD.getAttackDamage()
                && this.itemDurability == GOLD.getDurability()
        ) {
            return STONE.getMiningLevel();
        }
        return this.miningLevel;
    }

}
