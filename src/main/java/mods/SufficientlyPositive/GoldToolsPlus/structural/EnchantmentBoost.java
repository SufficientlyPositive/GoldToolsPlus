package mods.SufficientlyPositive.GoldToolsPlus.structural;

import net.minecraft.item.Item;

public class EnchantmentBoost {

    private final int levelBoost;
    private final Item[] boostedItems;

    public EnchantmentBoost(int levelBoost, Item[] boostedItems) {
        this.levelBoost = levelBoost;
        this.boostedItems = boostedItems;
    }

    public int getLevelBoost() {
        return this.levelBoost;
    }

    public Item[] getBoostedItems() {
        return this.boostedItems;
    }
}
