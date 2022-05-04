package mods.SufficientlyPositive.GoldToolsPlus.game.materials;

import mods.SufficientlyPositive.GoldToolsPlus.init.ItemsInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/**
 * Implements the armour made from white gold.
 */
public class WhiteGoldArmourMaterial implements ArmorMaterial {

    /**
     * The instance of white gold armour material for other functions to call.
     */
    public static final WhiteGoldArmourMaterial INSTANCE = new WhiteGoldArmourMaterial();

    /**
     * The base durability and protection values of {Boots, Leggings, Chestplate, Helmet}
     */
    private static final int[] BASE_DURABILITY = new int[]{3, 5, 6, 3};
    private static final int[] PROTECTION_VALUES = new int[] {2, 4, 5, 2};

    /**
     * Durability multiplier and enchantability of the armour pieces
     */
    private static final int durabilityMultiplier = 18;
    private static final int enchantability = 37;

    /**
     * Fetches the durability for the specific armour piece given a slot.
     *
     * @param slot the slot the armour piece is equipped to, entity slot ids expected:
     *             0 - boots, 1 - leggings, 2 - chest, 3 - helmet.
     * @return the durability of the armour piece within that slot.
     */
    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * durabilityMultiplier;
    }

    /**
     * Fetches the protection values for the specific armour piece given a slot.
     *
     * @param slot the slot the armour piece is equipped to, entity slot ids expected:
     *             0 - boots, 1 - leggings, 2 - chest, 3 - helmet.
     * @return how many "half-armour" slots the armour piece gives.
     */
    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    /**
     * Getter for enchantability value.
     */
    @Override
    public int getEnchantability() {
        return enchantability;
    }

    /**
     * What sound should the armour piece make on equip.
     * @return the same equip sound as the gold armour equip sound.
     */
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
    }

    /**
     * Fetches what ingredient should be used within the crafting table/anvil
     * to repain 1/3 of the pickaxe's durability
     * @return WHITE_GOLD_INGOT instance.
     */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ItemsInit.WHITE_GOLD_INGOT);
    }

    /**
     * What is the material called?
     * @return "white_gold".
     */
    @Override
    public String getName() {
        return "white_gold";
    }

    /**
     * Return the armour's toughness, providing additional protection.
     * Diamond: 2.0F, Netherrite: 3.0F
     */
    @Override
    public float getToughness() {
        return 1.0F;
    }

    /**
     * Knockback resistance of the armour, Netherrite: 0.1F
     */
    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
