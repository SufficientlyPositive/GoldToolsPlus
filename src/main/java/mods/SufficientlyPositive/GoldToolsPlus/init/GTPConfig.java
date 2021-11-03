package mods.SufficientlyPositive.GoldToolsPlus.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class GTPConfig {

    public static final Enchantment[] enchantmentsNotToBoost = {
            Enchantments.MENDING,
            Enchantments.SILK_TOUCH,
            Enchantments.BINDING_CURSE,
            Enchantments.VANISHING_CURSE,
            Enchantments.AQUA_AFFINITY
    };

    private static final GTPEnchantmentBoost empty = new GTPEnchantmentBoost(0, new Item[]{});

    public static final GTPEnchantmentBoost goldItems = new GTPEnchantmentBoost(
            1,
            new Item[] {Items.GOLDEN_SWORD,
                    Items.GOLDEN_SHOVEL,
                    Items.GOLDEN_PICKAXE,
                    Items.GOLDEN_AXE,
                    Items.GOLDEN_HOE,
                    Items.GOLDEN_HELMET,
                    Items.GOLDEN_CHESTPLATE,
                    Items.GOLDEN_LEGGINGS,
                    Items.GOLDEN_BOOTS
            }
    );

    public static final GTPEnchantmentBoost venerableGoldItems = new GTPEnchantmentBoost(
            2,
            new Item[] {
                    GTPItemsInit.VENERABLE_GOLD_SWORD,
                    GTPItemsInit.VENERABLE_GOLD_SHOVEL,
                    GTPItemsInit.VENERABLE_GOLD_PICKAXE,
                    GTPItemsInit.VENERABLE_GOLD_AXE,
                    GTPItemsInit.VENERABLE_GOLD_HOE,
                    GTPItemsInit.VENERABLE_GOLD_HELMET,
                    GTPItemsInit.VENERABLE_GOLD_CHESTPLATE,
                    GTPItemsInit.VENERABLE_GOLD_LEGGINGS,
                    GTPItemsInit.VENERABLE_GOLD_BOOTS
            }
    );

    private static final GTPEnchantmentBoost[] boostList = new GTPEnchantmentBoost[]{goldItems, venerableGoldItems};

    public static GTPEnchantmentBoost getBoostedList(ItemStack itemStack) {
        for(GTPEnchantmentBoost list : boostList) {
            if (list.inBoostedItemList(itemStack)) {
                return list;
            }
        }
        return empty;
    }

    public static boolean enchantmentBoostable(Enchantment enchantment) {
        for(Enchantment enchantment1 : enchantmentsNotToBoost) {
            if(enchantment == enchantment1) {
                return false;
            }
        }
        return true;
    }
}
