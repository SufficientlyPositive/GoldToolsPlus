package mods.SufficientlyPositive.GoldToolsPlus.functions;

import mods.SufficientlyPositive.GoldToolsPlus.init.EnchantmentBoostInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

/**
 * Class containing utility functions pertaining to the enchantment boosting functionality.
 */
public class EnchantmentBoostFunctions {

    /**
     * Function that is inserted before final enchantment levels are decided for any item
     * Checks the boostMap for how many levels to boost the enchantment. Does not boost
     * if the enchantment is in the enchantmentsNotToBoost list.
     *
     * @param stack the item that is being enchanted.
     * @param enchantment the enchantment that is looking to be applied to the item.
     * @return the integer value of how many levels to increase the enchantment level by.
     */
    public static int fetchBoostAmount(ItemStack stack, Enchantment enchantment) {
        if(enchantmentBoostable(enchantment)) {
            return EnchantmentBoostInit.boostMap.getOrDefault(stack.getItem(), 0);
        }
        return 0;
    }

    /**
     * Checks if an enchantment is in enchantmentsNotToBoostList,
     * if so, returns False, as the enchantment is not boost-able.
     *
     * @param enchantment the enchantment to check.
     * @return whether the enchantment is legal to be boosted.
     */
    private static boolean enchantmentBoostable(Enchantment enchantment) {
        for(Enchantment enchantment1 : EnchantmentBoostInit.enchantmentsNotToBoostList) {
            if(enchantment == enchantment1) {
                return false;
            }
        }
        return true;
    }
}
