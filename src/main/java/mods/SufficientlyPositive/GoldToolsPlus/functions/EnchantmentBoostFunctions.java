package mods.SufficientlyPositive.GoldToolsPlus.functions;

import mods.SufficientlyPositive.GoldToolsPlus.init.EnchantmentBoostInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class EnchantmentBoostFunctions {

    public static int fetchBoostAmount(ItemStack stack, Enchantment enchantment) {
        if(enchantmentBoostable(enchantment)) {
            return EnchantmentBoostInit.boostMap.getOrDefault(stack.getItem(), 0);
        }
        return 0;
    }

    private static boolean enchantmentBoostable(Enchantment enchantment) {
        for(Enchantment enchantment1 : EnchantmentBoostInit.enchantmentsNotToBoostList) {
            if(enchantment == enchantment1) {
                return false;
            }
        }
        return true;
    }
}
