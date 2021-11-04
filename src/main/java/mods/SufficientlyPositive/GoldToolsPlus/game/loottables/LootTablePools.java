package mods.SufficientlyPositive.GoldToolsPlus.game.loottables;

import mods.SufficientlyPositive.GoldToolsPlus.functions.LootTableFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.init.ItemsInit;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;

public class LootTablePools {

    /**
     * The pool that adds white gold shards to the nether gold ore lootpool.
     * Drops are predicated on the pickaxe in use having fortune V or higher.
     * Distribution of drops: binom(2, 0.02)
     */
    public static final FabricLootPoolBuilder NETHER_GOLD_ORE_ADDITIONAL;

    /**
     * The pool that adds white gold shards to the piglin lootpool.
     * Distribution of drops: binom(1, 0.01).
     * Affected by looting.
     */
    public static final FabricLootPoolBuilder PIGLIN_LOOT_ADDITIONAL;

    /**
     * The pool that adds white gold shards to the piglin brute lootpool.
     * Distribution of drops: binom(1, 0.75).
     * Affected by looting.
     */
    public static final FabricLootPoolBuilder PIGLIN_BRUTE_LOOT_ADDITIONAL;

    /**
     * The pool that adds white gold shards to the ruined portal chest lootpool.
     * Distribution of slots: binom(2, 0.5).
     * Distribution of items: binom(1, 0.05).
     */
    public static final FabricLootPoolBuilder RUINED_PORTAL_SHARDS;

    /**
     * The pool that adds white gold shards to the bastion bridge chest lootpool.
     * Distribution of slots: uniform(0, 2).
     * Distribution of items: binom(2, 0.2).
     */
    public static final FabricLootPoolBuilder BASTION_BRIDGE_SHARDS;

    /**
     * The pool that adds white gold shards to the bastion stable chest lootpool.
     * Distribution of slots: uniform(0, 1).
     * Distribution of items: binom(2, 0.2).
     */
    public static final FabricLootPoolBuilder BASTION_STABLE_SHARDS;

    /**
     * The pool that adds white gold shards to the bastion other chest lootpool.
     * Distribution of slots: constant(1).
     * Distribution of items: binom(2, 0.15).
     */
    public static final FabricLootPoolBuilder BASTION_OTHER_SHARDS;

    /**
     * The pool that adds white gold shards to the bastion treasure chest lootpool.
     * Distribution of slots: constant(1).
     * Distribution of items: binom(3, 0.3).
     */
    public static final FabricLootPoolBuilder BASTION_TREASURE_SHARDS;

    /**
     * The pool that adds white gold ingots to the bastion treasure chest lootpool.
     * Distribution of slots: constant(1).
     * Distribution of items: binom(2, 0.2).
     */
    public static final FabricLootPoolBuilder BASTION_TREASURE_INGOTS;

    /**
     * The pool that adds white gold ingots to the end city chest lootpool.
     * Distribution of slots: binom(2, 0.1).
     * Distribution of items: binom(2, 0.7).
     */
    public static final FabricLootPoolBuilder END_CITY_INGOTS;

    /**
     * The pool that adds white gold tools to the bastion bridge chest lootpool.
     */
    public static final FabricLootPoolBuilder BASTION_BRIDGE_TOOLS;

    /**
     * The loot pool that adds white gold tools to the bastion other/bastion stable chest lootpool.
     */
    public static final FabricLootPoolBuilder BASTION_OTHER_TOOLS;

    /**
     * The loot pool that adds enchanted white gold tools to the bastion treasure chest lootpool.
     */
    public static final FabricLootPoolBuilder BASTION_TREASURE_TOOLS;

    /**
     * The loot pool that adds enchanted white gold tools to the end city chest lootpool.
     */
    public static final FabricLootPoolBuilder END_CITY_TOOLS;

    static {
        NETHER_GOLD_ORE_ADDITIONAL = FabricLootPoolBuilder.builder()
                .rolls(BinomialLootNumberProvider.create(2, 0.02F))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_SHARD))
                .conditionally(
                        MatchToolLootCondition.builder(
                                ItemPredicate.Builder.create().enchantment(
                                        LootTableConstants.FORTUNE_5_OR_HIGHER)));

        PIGLIN_LOOT_ADDITIONAL = LootTableFunctions.createMobItemPool(
                ConstantLootNumberProvider.create(1.0F),
                BinomialLootNumberProvider.create(1, 0.01F),
                ItemsInit.VENERABLE_GOLD_SHARD
        );

        PIGLIN_BRUTE_LOOT_ADDITIONAL = LootTableFunctions.createMobItemPool(
                ConstantLootNumberProvider.create(1.0F),
                BinomialLootNumberProvider.create(1, 0.75F),
                ItemsInit.VENERABLE_GOLD_SHARD
        );

        RUINED_PORTAL_SHARDS = LootTableFunctions.createItemPool(
                BinomialLootNumberProvider.create(2, 0.5F),
                BinomialLootNumberProvider.create(1, 0.05F),
                ItemsInit.VENERABLE_GOLD_SHARD);

        BASTION_BRIDGE_SHARDS = LootTableFunctions.createItemPool(
                UniformLootNumberProvider.create(0, 2),
                BinomialLootNumberProvider.create(2, 0.2F),
                ItemsInit.VENERABLE_GOLD_SHARD);

        BASTION_STABLE_SHARDS = LootTableFunctions.createItemPool(
                UniformLootNumberProvider.create(0, 1),
                BinomialLootNumberProvider.create(2, 0.2F),
                ItemsInit.VENERABLE_GOLD_SHARD);

        BASTION_OTHER_SHARDS = LootTableFunctions.createItemPool(
                ConstantLootNumberProvider.create(1),
                BinomialLootNumberProvider.create(2, 0.15F),
                ItemsInit.VENERABLE_GOLD_SHARD);

        BASTION_TREASURE_SHARDS = LootTableFunctions.createItemPool(
                ConstantLootNumberProvider.create(1),
                BinomialLootNumberProvider.create(3, 0.3F),
                ItemsInit.VENERABLE_GOLD_SHARD);

        BASTION_TREASURE_INGOTS = LootTableFunctions.createItemPool(
                ConstantLootNumberProvider.create(1.0F),
                BinomialLootNumberProvider.create(2, 0.2F),
                ItemsInit.VENERABLE_GOLD_INGOT);

        END_CITY_INGOTS = LootTableFunctions.createItemPool(
                BinomialLootNumberProvider.create(2, 0.1F),
                BinomialLootNumberProvider.create(6, 0.7F),
                ItemsInit.VENERABLE_GOLD_INGOT);

        BASTION_BRIDGE_TOOLS = FabricLootPoolBuilder.builder()
                .rolls(BinomialLootNumberProvider.create(1, 0.05F))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_SWORD).weight(5))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_SHOVEL).weight(3))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_PICKAXE).weight(5))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_AXE).weight(5))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_HOE).weight(1));

        BASTION_OTHER_TOOLS = FabricLootPoolBuilder.builder()
                .rolls(BinomialLootNumberProvider.create(1, 0.15F))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_SWORD).weight(2))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_SHOVEL).weight(5))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_PICKAXE).weight(5))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_AXE).weight(5))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_HOE).weight(2));

        BASTION_TREASURE_TOOLS = FabricLootPoolBuilder.builder()
                .rolls(BinomialLootNumberProvider.create(2, 0.2F))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_SWORD).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_AXE).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_PICKAXE).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_HELMET).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_CHESTPLATE).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_LEGGINGS).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_BOOTS).apply(EnchantRandomlyLootFunction.builder()));

        END_CITY_TOOLS = FabricLootPoolBuilder.builder()
                .rolls(BinomialLootNumberProvider.create(2, 0.125F))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_SWORD).weight(3).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_SHOVEL).weight(6).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_PICKAXE).weight(6).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_AXE).weight(6).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_HELMET).weight(2).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_CHESTPLATE).weight(2).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_LEGGINGS).weight(2).apply(EnchantRandomlyLootFunction.builder()))
                .with(ItemEntry.builder(ItemsInit.VENERABLE_GOLD_BOOTS).weight(2).apply(EnchantRandomlyLootFunction.builder()));
    }
}

