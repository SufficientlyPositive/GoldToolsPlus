package mods.SufficientlyPositive.GoldToolsPlus.structural;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantmentBoost {

    private final int levelBoost;
    private final Item[] boostedItems;

    public EnchantmentBoost(int levelBoost, Item[] boostedItems) {
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

    public Item[] getBoostedItems() {
        return this.boostedItems;
    }
}
