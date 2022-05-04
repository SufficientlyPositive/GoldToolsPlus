package mods.SufficientlyPositive.GoldToolsPlus.structural;

import net.minecraft.item.Item;

/**
 * Data class containing the enchantment boost object
 * for recording different lists of items.
 */
public class EnchantmentBoost {

    /**
     * The number of levels to boost any viable enchantments.
     */
    private final int levelBoost;

    /**
     * The items to boost enchantments on by the aforementioned level.
     */
    private final Item[] boostedItems;

    /**
     * Constructor setting object up like you'd expect.
     * @param levelBoost the number of levels to boost any viable enchantments.
     * @param boostedItems the items to boost enchantments on by the aforementioned level.
     */
    public EnchantmentBoost(int levelBoost, Item[] boostedItems) {
        this.levelBoost = levelBoost;
        this.boostedItems = boostedItems;
    }

    /**
     * Getter for the levelBoost parameter.
     */
    public int getLevelBoost() {
        return this.levelBoost;
    }

    /**
     * Getter for the boostedItems array.
     */
    public Item[] getBoostedItems() {
        return this.boostedItems;
    }
}
