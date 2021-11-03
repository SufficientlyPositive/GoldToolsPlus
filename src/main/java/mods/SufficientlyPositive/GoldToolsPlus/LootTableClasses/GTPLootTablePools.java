package mods.SufficientlyPositive.GoldToolsPlus.LootTableClasses;

import mods.SufficientlyPositive.GoldToolsPlus.init.GTPItemsInit;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;

public class GTPLootTablePools {

    public static final FabricLootPoolBuilder NETHER_GOLD_ORE_ADDITIONAL = FabricLootPoolBuilder.builder()
            .rolls(BinomialLootNumberProvider.create(2, 0.02F))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_SHARD))
            .conditionally(
                    MatchToolLootCondition.builder(
                            ItemPredicate.Builder.create().enchantment(
                                    GTPLootTableConstants.FORTUNE_5_OR_HIGHER)));

    // Adding a tool to 1 in 20 bridge chests
    public static final FabricLootPoolBuilder BASTION_BRIDGE_CHESTS_TOOLS = FabricLootPoolBuilder.builder()
            .rolls(BinomialLootNumberProvider.create(1, 0.05F))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_SWORD).weight(5))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_SHOVEL).weight(3))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_PICKAXE).weight(5))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_AXE).weight(5))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_HOE).weight(1));

    // Adding a tool to 3 in 20 other (housing?) chests
    public static final FabricLootPoolBuilder BASTION_OTHER_CHESTS_TOOLS = FabricLootPoolBuilder.builder()
            .rolls(BinomialLootNumberProvider.create(1, 0.15F))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_SWORD).weight(2))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_SHOVEL).weight(5))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_PICKAXE).weight(5))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_AXE).weight(5))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_HOE).weight(2));

    // Adding an axe/sword/pickaxe to slightly over 1 in 10 Treasure chests
    public static final FabricLootPoolBuilder BASTION_TREASURE_CHESTS_TOOLS = FabricLootPoolBuilder.builder()
            .rolls(BinomialLootNumberProvider.create(2, 0.2F))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_SWORD).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_AXE).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_PICKAXE).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_HELMET).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_CHESTPLATE).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_LEGGINGS).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_BOOTS).apply(EnchantRandomlyLootFunction.builder()));

    // Adding tools to slightly over 1 in 8 End City chests
    public static final FabricLootPoolBuilder END_CITY_CHESTS_TOOLS = FabricLootPoolBuilder.builder()
            .rolls(BinomialLootNumberProvider.create(3, 0.125F))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_SWORD).weight(3).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_SHOVEL).weight(6).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_PICKAXE).weight(6).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_AXE).weight(6).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_HELMET).weight(2).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_CHESTPLATE).weight(2).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_LEGGINGS).weight(2).apply(EnchantRandomlyLootFunction.builder()))
            .with(ItemEntry.builder(GTPItemsInit.VENERABLE_GOLD_BOOTS).weight(2).apply(EnchantRandomlyLootFunction.builder()));
}

