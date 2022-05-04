package mods.SufficientlyPositive.GoldToolsPlus.functions;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

/**
 * Class containing helper functions helping with setting new loot pools
 */
public class LootTableFunctions {
    /**
     * Used for creating a pool for any specific item.
     *
     * @param stacks is the generator that gives the number of rolls or "stacks" of the item dropped.
     * @param size   is the generator deciding how large the size of the stack (if generated) is.
     * @param item   is the item to generate stacks with.
     */
    public static FabricLootPoolBuilder createItemPool(LootNumberProvider stacks, LootNumberProvider size, Item item) {
        return FabricLootPoolBuilder.builder()
                .rolls(stacks)
                .with(ItemEntry.builder(item))
                .apply(SetCountLootFunction.builder(size));
    }

    /**
     * Used for creating a pool designed to be dropped by a mob, looting applied!
     *
     * @param stacks is the generator that gives the number of rolls or "stacks" of the item dropped.
     * @param size   is the generator deciding how large the size of the stack (if generated) is.
     * @param item   is the item to generate stacks with.
     */
    public static FabricLootPoolBuilder createMobItemPool(LootNumberProvider stacks, LootNumberProvider size, Item item) {
        return FabricLootPoolBuilder.builder()
                .rolls(stacks)
                .with(ItemEntry.builder(item)
                        .apply(SetCountLootFunction.builder(size))
                        .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))));
    }
}
