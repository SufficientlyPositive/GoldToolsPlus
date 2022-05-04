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

/**
 * Mixin for editing the enchant command to provide the desired functionality.
 * Allows for enchanting an item from level:1-maxLevel+boost.
 */
@Mixin(EnchantCommand.class)
public abstract class EnchantCommandMixin {

    @Final
    @Shadow
    private static Dynamic2CommandExceptionType FAILED_LEVEL_EXCEPTION;

    /**
     * Mixin to modify the enchantment variable level sent to the command to bypass a check later.
     *
     * @param level -
     * @param source -
     * @param targets the targets of the enchant command.
     * @param enchantment the enchantment to apply.
     * @param level1 the level of the enchantment to apply.
     * @return the new modified enchantment level level1 - boost (of material).
     */
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

    /**
     * After the check, modifies the enchantment variable level once again, back to its original levels.
     *
     * @param level -
     * @param source -
     * @param targets the targets of the enchant command.
     * @param enchantment the enchantment to apply.
     * @param level1 the level of the enchantment to apply, boost levels lower than input.
     * @return the originally inputted enchantment value.
     */
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

    /**
     * Overrides the failed exception to be boost level friendly. Now prints boosted max level instead of general
     * max level of enchantment.
     *
     * @param instance -
     * @param a -
     * @param b -
     * @param source -
     * @param targets the targets of the enchantment command.
     * @param enchantment the enchantment that was unable to apply.
     * @param level the input level given.
     * @return the corrected FAILED_LEVEL_EXCEPTION.
     */
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
