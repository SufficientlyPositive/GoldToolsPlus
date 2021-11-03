package mods.SufficientlyPositive.GoldToolsPlus.mixin;

import mods.SufficientlyPositive.GoldToolsPlus.init.GTPConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.EnchantCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Collection;

@Mixin(EnchantCommand.class)
public abstract class EnchantCommandMixin {

    @ModifyVariable(method = "execute",
            at = @At("HEAD")
    )
    private static int overrideEnchantMax(int level, ServerCommandSource source, Collection<? extends Entity> targets, Enchantment enchantment, int level1) {
        if (GTPConfig.enchantmentBoostable(enchantment)) {
            for (Entity entity : targets) {
                if (entity instanceof LivingEntity livingEntity) {
                    ItemStack itemStack = livingEntity.getMainHandStack();
                    return level1 - GTPConfig.getBoostedList(itemStack).getLevelBoost();
                }
            }
        }
        return level;
    }

    @ModifyVariable(method = "execute",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/Collection;iterator()Ljava/util/Iterator;",
                    ordinal = 0
            ),
            argsOnly = true
    )
    private static int part2(int level, ServerCommandSource source, Collection<? extends Entity> targets, Enchantment enchantment, int level1) {
        if (GTPConfig.enchantmentBoostable(enchantment)) {
            for (Entity entity : targets) {
                if (entity instanceof LivingEntity livingEntity) {
                    ItemStack itemStack = livingEntity.getMainHandStack();
                    return level1 + GTPConfig.getBoostedList(itemStack).getLevelBoost();
                }
            }
        }
        return level1;
    }
}
