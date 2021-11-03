package mods.SufficientlyPositive.GoldToolsPlus.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GTPEnchantmentBoost {

    private final int levelBoost;
    private final Item[] boostedItems;

    public GTPEnchantmentBoost(int levelBoost, Item[] boostedItems) {
        this.levelBoost = levelBoost;
        this.boostedItems = boostedItems;
    }

    public boolean inBoostedItemList(ItemStack itemStack) {
        for(Item item : boostedItems) {
            if (itemStack.isOf(item)) {
                return true;
            }
        }
        return false;
    }

    public int getLevelBoost() {
        return this.levelBoost;
    }
}
