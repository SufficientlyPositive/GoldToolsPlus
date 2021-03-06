package mods.SufficientlyPositive.GoldToolsPlus.mixins.enchantments;

import com.google.common.collect.Lists;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlusConfig;
import mods.SufficientlyPositive.GoldToolsPlus.functions.EnchantmentBoostFunctions;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Random;

/**
 * Mixin class that boosts enchantments in the enchantment table.
 */
@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

    // probably could add a check if itemstack boost level is 0, to skip all of this idk.
    /**
     * After the enchantments have been applied, remove them and re-add them
     * in their boosted state.
     *
     * @param random -
     * @param stack the item to be enchanted
     * @param level -
     * @param treasureAllowed -
     * @param cir -
     * @param list the list of enchantments to place on the item.
     */
    @Inject(method = "generateEnchantments",
            at = @At("TAIL"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private static void fixList(Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir, List<EnchantmentLevelEntry> list) {
        List<EnchantmentLevelEntry> newItems = Lists.newArrayList();
        for (EnchantmentLevelEntry ench : list) {
            newItems.add(new EnchantmentLevelEntry(ench.enchantment, ench.level + EnchantmentBoostFunctions.fetchBoostAmount(stack, ench.enchantment)));
        }
        list.clear();
        for (EnchantmentLevelEntry ench : newItems) {
            list.add(new EnchantmentLevelEntry(ench.enchantment, ench.level));
        }
    }
}
