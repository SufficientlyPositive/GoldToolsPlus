package mods.SufficientlyPositive.GoldToolsPlus.mixins.other;

import mods.SufficientlyPositive.GoldToolsPlus.init.ItemsInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Mixin to change Piglin brute's axes to white gold axes.
 */
@Mixin(PiglinBruteEntity.class)
public abstract class PiglinBruteEntityMixin extends MobEntity {

    protected PiglinBruteEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Overrides the existing initEquipment of Piglin Brutes to given them
     * White gold axes instead of gold axes
     * @param difficult the local game difficulty.
     */
    public void initEquipment(LocalDifficulty difficult){
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ItemsInit.WHITE_GOLD_AXE));
    }
}
