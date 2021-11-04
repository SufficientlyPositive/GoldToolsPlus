package mods.SufficientlyPositive.GoldToolsPlus.mixin;

import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import mods.SufficientlyPositive.GoldToolsPlus.init.GTPConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(EnchantRandomlyLootFunction.class)
public abstract class EnchantRandomlyLootFunctionMixin {

    @ModifyVariable(method = "addEnchantmentToStack",
                    at = @At(value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
                    )
    )
    private static int improveRandomEnchants(int i, ItemStack stack, Enchantment enchantment, Random random) {
        if(GTPConfig.enchantmentBoostable(enchantment)) {
            return i + GTPConfig.getBoostedList(stack).getLevelBoost();
        }
        return i;
    }
}
