package mods.SufficientlyPositive.GoldToolsPlus.init;

import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlusConfig;
import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.structural.EnchantmentBoost;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;

/**
 * Static class containing containers of items to boost and enchantments not to boost
 * Initialised at runtime.
 */
public class EnchantmentBoostInit {

    /**
     * Map of items to boost.
     */
    public static final Map<Item, Integer> boostMap;

    /**
     * List of enchantments not to boost.
     */
    public static final Enchantment[] enchantmentsNotToBoostList;

    static {
        boostMap = new HashMap<>();
        enchantmentsNotToBoostList = GoldToolsPlusConfig.enchantmentsNotToBoost;
    }

    /**
     * Initialisation of boostMap and enchantmentsNotToBoostList at runtime.
     * Values currently taken from GoldToolsPlusConfig.
     */
    public static void init() {
        // when changing over to config file, have something that parses a list of EnchantmentBoosts and search through that list instead
        // for now we will use boostList
        for(EnchantmentBoost boost : GoldToolsPlusConfig.boostList) {
            int level = boost.getLevelBoost();
            for(Item item : boost.getBoostedItems()) {
                boostMap.put(item, level);
            }
        }

        GoldToolsPlusHelperFunctions.log(Level.INFO, "Enchantment boost masterlist initialised.");
    }
}
