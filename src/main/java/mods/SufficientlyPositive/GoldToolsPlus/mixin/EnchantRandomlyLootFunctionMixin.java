package mods.SufficientlyPositive.GoldToolsPlus.mixin;

import mods.SufficientlyPositive.GoldToolsPlus.init.GTPConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;

@Mixin(EnchantRandomlyLootFunction.class)
public abstract class EnchantRandomlyLootFunctionMixin {

    @ModifyVariable(method = "addEnchantmentToStack",
                    at = @At(value = "INVOKE",
                            target = "Lnet/minecraft/item/ItemStack;addEnchantment(Lnet/minecraft/enchantment/Enchantment;I)V"
                    )
    )
    private static int improveRandomEnchants(int i, ItemStack stack, Enchantment enchantment, Random random) {
        if(GTPConfig.enchantmentBoostable(enchantment)) {
            return i + GTPConfig.getBoostedList(stack).getLevelBoost();
        }
        return i;
    }
}
