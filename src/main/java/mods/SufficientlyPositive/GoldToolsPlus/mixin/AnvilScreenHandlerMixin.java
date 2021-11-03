package mods.SufficientlyPositive.GoldToolsPlus.mixin;

import mods.SufficientlyPositive.GoldToolsPlus.init.GTPConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.Map;


// ANVIL FUNCTIONALITY COMPLETE
// *celebration ensues*

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {

    // when map fails to find a key it returns null, we want an integer for 0 enchant level
    private int nullHandler(Integer i) {
        return (i == null) ? -1 : i;
    }

    // if you have 2 of the same item, what does combining their enchantments get you?
    private int combineSame(int i, int j, int max) {
        return (i == j && i < max) ?
                i + 1 : Math.max(i, j);
    }

    // if you are adding a book, how does it combine?
    private int combineWithBook(int i, int j, int max, int levelBoost) {
        if(i == j) {
            j++;
        }
        return (j + levelBoost > i) ? Math.min(j + levelBoost, max) : i;
    }



    // after the normal map.put inside AnvilScreenHandler, check if the enchantment needs to be buffed
    @Inject(method = "updateResult",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void combineEnchants(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map<Enchantment, Integer> map, boolean bl, Map<Enchantment, Integer> map2, boolean bl2, boolean bl3, Iterator var12, Enchantment enchantment, int u) {
        if(GTPConfig.enchantmentBoostable(enchantment)) {
            int valueOnItem = nullHandler(EnchantmentHelper.get(itemStack2).get(enchantment));
            int valueOnItem2 = nullHandler(EnchantmentHelper.get(itemStack3).get(enchantment));

            if (itemStack2.isOf(itemStack3.getItem())) {
                map.put(enchantment, combineSame(valueOnItem, valueOnItem2, enchantment.getMaxLevel() + GTPConfig.getBoostedList(itemStack).getLevelBoost()));
            } else {
                int levelBoost = GTPConfig.getBoostedList(itemStack).getLevelBoost();
                map.put(enchantment, combineWithBook(valueOnItem, valueOnItem2, enchantment.getMaxLevel() + levelBoost, levelBoost));
            }
        }
    }
}
