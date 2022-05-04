package mods.SufficientlyPositive.GoldToolsPlus.mixins.enchantments;

import mods.SufficientlyPositive.GoldToolsPlus.functions.EnchantmentBoostFunctions;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;

/**
 * Mixin class to boost enchantments on randomly generated loot.
 */
@Mixin(EnchantRandomlyLootFunction.class)
public abstract class EnchantRandomlyLootFunctionMixin {

    /**
     * Modifies the enchantment level to add to the item to include the boost level.
     *
     * @param i the enchantment level
     * @param stack the itemstack to enchant
     * @param enchantment the enchantment to add
     * @param random -
     * @return the new enchantment level that includes the boost amount.
     */
    @ModifyVariable(method = "addEnchantmentToStack",
                    at = @At(value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
                    )
    )
    private static int improveRandomEnchants(int i, ItemStack stack, Enchantment enchantment, Random random) {
        return i + EnchantmentBoostFunctions.fetchBoostAmount(stack, enchantment);
    }
}
