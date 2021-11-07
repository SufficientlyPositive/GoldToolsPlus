package mods.SufficientlyPositive.GoldToolsPlus.mixins.enchantments;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import mods.SufficientlyPositive.GoldToolsPlus.functions.EnchantmentBoostFunctions;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.EnchantCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collection;

@Mixin(EnchantCommand.class)
public abstract class EnchantCommandMixin {

    @Final
    @Shadow
    private static Dynamic2CommandExceptionType FAILED_LEVEL_EXCEPTION;

    @ModifyVariable(method = "execute",
            at = @At("HEAD")
    )
    private static int overrideEnchantMax(int level, ServerCommandSource source, Collection<? extends Entity> targets, Enchantment enchantment, int level1) {
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity livingEntity) {
                ItemStack itemStack = livingEntity.getMainHandStack();
                return level1 - EnchantmentBoostFunctions.fetchBoostAmount(itemStack, enchantment);
            }
        }
        return level1;
    }

    @ModifyVariable(method = "execute",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/Collection;iterator()Ljava/util/Iterator;",
                    ordinal = 0
            ),
            argsOnly = true
    )
    private static int part2(int level, ServerCommandSource source, Collection<? extends Entity> targets, Enchantment enchantment, int level1) {
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity livingEntity) {
                ItemStack itemStack = livingEntity.getMainHandStack();
                return level1 + EnchantmentBoostFunctions.fetchBoostAmount(itemStack, enchantment);
            }
        }
        return level1;
    }

    @Redirect(method = "execute",
            at = @At(value = "INVOKE",
                    target = "com/mojang/brigadier/exceptions/Dynamic2CommandExceptionType.create (Ljava/lang/Object;Ljava/lang/Object;)Lcom/mojang/brigadier/exceptions/CommandSyntaxException;"
            )
    )
    private static CommandSyntaxException errorFix(Dynamic2CommandExceptionType instance, Object a, Object b, ServerCommandSource source, Collection<? extends Entity> targets, Enchantment enchantment, int level) {
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity livingEntity) {
                ItemStack itemStack = livingEntity.getMainHandStack();
                int boost = EnchantmentBoostFunctions.fetchBoostAmount(itemStack, enchantment);
                return FAILED_LEVEL_EXCEPTION.create(level + boost, enchantment.getMaxLevel() + boost);
            }
        }
        return FAILED_LEVEL_EXCEPTION.create(level, enchantment.getMaxLevel());
    }

}
