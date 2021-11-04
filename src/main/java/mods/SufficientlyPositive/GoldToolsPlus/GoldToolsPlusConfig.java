package mods.SufficientlyPositive.GoldToolsPlus;

import mods.SufficientlyPositive.GoldToolsPlus.structural.EnchantmentBoost;
import mods.SufficientlyPositive.GoldToolsPlus.init.ItemsInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class GoldToolsPlusConfig {

    public static final Enchantment[] enchantmentsNotToBoost = {
            Enchantments.MENDING,
            Enchantments.SILK_TOUCH,
            Enchantments.BINDING_CURSE,
            Enchantments.VANISHING_CURSE,
            Enchantments.AQUA_AFFINITY
    };

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

    public static final EnchantmentBoost whiteGoldItems = new EnchantmentBoost(
            2,
            new Item[] {
                    ItemsInit.WHITE_GOLD_SWORD,
                    ItemsInit.WHITE_GOLD_SHOVEL,
                    ItemsInit.WHITE_GOLD_PICKAXE,
                    ItemsInit.WHITE_GOLD_AXE,
                    ItemsInit.WHITE_GOLD_HOE,
                    ItemsInit.WHITE_GOLD_HELMET,
                    ItemsInit.WHITE_GOLD_CHESTPLATE,
                    ItemsInit.WHITE_GOLD_LEGGINGS,
                    ItemsInit.WHITE_GOLD_BOOTS
            }
    );

    public static final EnchantmentBoost[] boostList = new EnchantmentBoost[]{goldItems, whiteGoldItems};
}
