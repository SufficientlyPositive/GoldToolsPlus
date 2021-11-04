package mods.SufficientlyPositive.GoldToolsPlus.mixins.enchantments;

import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlusConfig;
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

@Mixin(SmithingRecipe.class)
public abstract class SmithingRecipeMixin {

    @Inject(method = "craft",
            at = @At(value = "TAIL"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void boostOnSmithing(Inventory inventory, CallbackInfoReturnable<ItemStack> cir, ItemStack itemStack) {
        if(GoldToolsPlusConfig.getBoostedList(itemStack) == GoldToolsPlusConfig.venerableGoldItems) {
            int boostAmount = GoldToolsPlusConfig.getBoostedList(itemStack).getLevelBoost() - GoldToolsPlusConfig.getBoostedList(inventory.getStack(0)).getLevelBoost();
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(itemStack);

            for(Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
                if (GoldToolsPlusConfig.enchantmentBoostable(enchantment.getKey())) {
                    enchantments.put(enchantment.getKey(), enchantment.getValue() + boostAmount);
                }
            }

            EnchantmentHelper.set(enchantments, itemStack);
        }
    }
}
