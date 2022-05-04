package mods.SufficientlyPositive.GoldToolsPlus.mixins.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlusConfig;

import java.util.Iterator;

/**
 * Mixin for getting piglins to recognise White Gold armour as "gold".
 * Using Piglib's mixin for wearsGoldArmour.
 */
@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    @Inject(
            method = "wearsGoldArmor(Lnet/minecraft/entity/LivingEntity;)Z",
            at = @At(value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    private static void wearsGoldArmorInject(LivingEntity entity, CallbackInfoReturnable<Boolean> cir, Iterable<ItemStack> iterable, Iterator iterator, ItemStack stack, Item item) {
        if (GoldToolsPlusConfig.piglinSafeArmour.contains(stack.getItem())) {
            cir.setReturnValue(true);
        }
    }
}
