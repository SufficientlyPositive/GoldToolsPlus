package mods.SufficientlyPositive.GoldToolsPlus;

import mods.SufficientlyPositive.GoldToolsPlus.structural.EnchantmentBoost;
import mods.SufficientlyPositive.GoldToolsPlus.init.ItemsInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class GoldToolsPlusConfig {

    public static final Enchantment[] enchantmentsNotToBoost = {
            Enchantments.MENDING,
            Enchantments.SILK_TOUCH,
            Enchantments.BINDING_CURSE,
            Enchantments.VANISHING_CURSE,
            Enchantments.AQUA_AFFINITY
    };

    private static final EnchantmentBoost empty = new EnchantmentBoost(0, new Item[]{});

    public static final EnchantmentBoost goldItems = new EnchantmentBoost(
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

    public static final EnchantmentBoost venerableGoldItems = new EnchantmentBoost(
            2,
            new Item[] {
                    ItemsInit.VENERABLE_GOLD_SWORD,
                    ItemsInit.VENERABLE_GOLD_SHOVEL,
                    ItemsInit.VENERABLE_GOLD_PICKAXE,
                    ItemsInit.VENERABLE_GOLD_AXE,
                    ItemsInit.VENERABLE_GOLD_HOE,
                    ItemsInit.VENERABLE_GOLD_HELMET,
                    ItemsInit.VENERABLE_GOLD_CHESTPLATE,
                    ItemsInit.VENERABLE_GOLD_LEGGINGS,
                    ItemsInit.VENERABLE_GOLD_BOOTS
            }
    );

    private static final EnchantmentBoost[] boostList = new EnchantmentBoost[]{goldItems, venerableGoldItems};

    public static EnchantmentBoost getBoostedList(ItemStack itemStack) {
        for(EnchantmentBoost list : boostList) {
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
