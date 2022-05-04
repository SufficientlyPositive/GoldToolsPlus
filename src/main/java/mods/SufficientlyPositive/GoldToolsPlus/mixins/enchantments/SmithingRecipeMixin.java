package mods.SufficientlyPositive.GoldToolsPlus.mixins.enchantments;

import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlusConfig;
import mods.SufficientlyPositive.GoldToolsPlus.functions.EnchantmentBoostFunctions;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.SmithingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

/**
 * Mixin class to apply any boosting in smithing table recipes (e.g. gold -> white_gold tools)
 * Should apply the difference between the two tools boosting levels.
 */
@Mixin(SmithingRecipe.class)
public abstract class SmithingRecipeMixin {

    /**
     * Any result of smithing table crafting that changes tool material will also
     * upgrade the enchantment levels to account for changing levels in enchantmentboost
     * properties.
     *
     * @param inventory the inventory of the smithing table
     * @param cir -
     * @param itemStack the item to be crafted into another.
     */
    @Inject(method = "craft",
            at = @At(value = "TAIL"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void boostOnSmithing(Inventory inventory, CallbackInfoReturnable<ItemStack> cir, ItemStack itemStack) {
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(itemStack);

            ItemStack stackPrev = inventory.getStack(0);
            enchantments.replaceAll((k, v) -> v + EnchantmentBoostFunctions.fetchBoostAmount(itemStack, k) - EnchantmentBoostFunctions.fetchBoostAmount(stackPrev, k));

            EnchantmentHelper.set(enchantments, itemStack);
    }
}
