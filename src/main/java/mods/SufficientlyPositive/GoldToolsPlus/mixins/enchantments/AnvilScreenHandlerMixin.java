package mods.SufficientlyPositive.GoldToolsPlus.mixins.enchantments;

import mods.SufficientlyPositive.GoldToolsPlus.functions.EnchantmentBoostFunctions;
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

/**
 * Mixin for editing the AnvilScreenHandler class to for enchantment boosting functionality.
 */
@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {

    /**
     * Handles any null outputs from fetching values out of the integer/enchantment map.
     * Converts null (e.g. no enchantment value) to -1
     *
     * @param i the Integer object returned from whatever the NBT spits out.
     * @return an int primitive value of the Integer object, -1 if i was null.
     */
    private int nullHandler(Integer i) {
        return (i == null) ? -1 : i;
    }

    /**
     * Used when 2 items are to be combined, calculates the final level of the enchantment.
     * Either level should be the maximum of the two values, or if both are the same, then
     * the enchantment should increase a single level (unless it would go beyond max).
     *
     * @param i enchantment level on item 1.
     * @param j enchantment level on item 2.
     * @param max maximum enchantment level that can be reached.
     * @return the combined enchantment level.
     */
    private int combineSame(int i, int j, int max) {
        return (i == j && i < max) ?
                i + 1 : Math.max(i, j);
    }

    /**
     * Used when an enchantment is added by a book onto an item. Calculates the final level of the
     * enchantment, considering the tool's material. If enchantment on book + the tool's enchantboost
     * level is beyond the current item's enchantment, then it gets applied, else the current item's
     * level stays.
     *
     * @param i the enchantment level on the item.
     * @param j the enchantment level on the book.
     * @param max the maximum enchantment level to reach.
     * @param levelBoost the size of the enchantment boost (min 0).
     * @return the level of the newly combined enchantment.
     */
    private int combineWithBook(int i, int j, int max, int levelBoost) {
        if(i == j) {
            j++;
        }
        return (j + levelBoost > i) ? Math.min(j + levelBoost, max) : i;
    }

    /**
     * After the first map.put() call that places a specific enchantment on the map, overrides the
     * value in the map with the new boosted (or not) value.
     * @param ci callback info, required for mixin.
     * @param itemStack item in the first slot of the anvil
     * @param i -
     * @param j -
     * @param k -
     * @param itemStack2 item in the output slot of the anvil
     * @param itemStack3 item in the second slot of the anvil
     * @param map map of enchantments to place on the output of the anvil.
     * @param bl -
     * @param map2 map of enchantments on itemStack3
     * @param bl2 -
     * @param bl3 -
     * @param var12 -
     * @param enchantment the specific enchantment that was just added to map, that may need to be boosted.
     * @param u -
     */
    @Inject(method = "updateResult",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void combineEnchants(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map<Enchantment, Integer> map, boolean bl, Map<Enchantment, Integer> map2, boolean bl2, boolean bl3, Iterator var12, Enchantment enchantment, int u) {
        int valueOnItem = nullHandler(EnchantmentHelper.get(itemStack2).get(enchantment));
        int valueOnItem2 = nullHandler(EnchantmentHelper.get(itemStack3).get(enchantment));

        if (itemStack2.isOf(itemStack3.getItem())) {
            map.put(enchantment, combineSame(valueOnItem, valueOnItem2, enchantment.getMaxLevel() + EnchantmentBoostFunctions.fetchBoostAmount(itemStack, enchantment)));
        } else {
            int levelBoost = EnchantmentBoostFunctions.fetchBoostAmount(itemStack, enchantment);
            map.put(enchantment, combineWithBook(valueOnItem, valueOnItem2, enchantment.getMaxLevel() + levelBoost, levelBoost));
        }
    }
}
