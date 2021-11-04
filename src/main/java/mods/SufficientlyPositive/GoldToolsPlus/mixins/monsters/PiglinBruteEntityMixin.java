package mods.SufficientlyPositive.GoldToolsPlus.mixins.monsters;

import mods.SufficientlyPositive.GoldToolsPlus.init.ItemsInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PiglinBruteEntity.class)
public abstract class PiglinBruteEntityMixin extends MobEntity {

    protected PiglinBruteEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public void initEquipment(LocalDifficulty difficult){
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ItemsInit.VENERABLE_GOLD_AXE));
    }
}
