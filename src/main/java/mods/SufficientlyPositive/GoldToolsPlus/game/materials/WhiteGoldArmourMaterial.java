package mods.SufficientlyPositive.GoldToolsPlus.game.materials;

import mods.SufficientlyPositive.GoldToolsPlus.init.ItemsInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class WhiteGoldArmourMaterial implements ArmorMaterial {

    public static final WhiteGoldArmourMaterial INSTANCE = new WhiteGoldArmourMaterial();

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {2, 4, 5, 2};

    private static final int durabilityMultiplier = 12;
    private static final int enchantability = 37;

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * durabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ItemsInit.WHITE_GOLD_INGOT);
    }

    @Override
    public String getName() {
        return "white_gold";
    }

    @Override
    public float getToughness() {
        return 0.5F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
