package mods.SufficientlyPositive.GoldToolsPlus.game.loottables;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.util.Identifier;

public class LootTableConstants {

        /**
         * Identifier for Nether Gold Ore loot table.
         */
        public static final Identifier NETHER_GOLD_ORE_LOOT_TABLE_ID = Blocks.NETHER_GOLD_ORE.getLootTableId();

        /**
         * Identifier for Piglin loot table.
         */
        public static final Identifier PIGLIN_LOOT_TABLE_ID = EntityType.PIGLIN.getLootTableId();

        /**
         * Identifier for Piglin Brute loot table.
         */
        public static final Identifier PIGLIN_BRUTE_LOOT_TABLE_ID = EntityType.PIGLIN_BRUTE.getLootTableId();

        /**
         * Predicate used to distinguish whether an item has at least fortune 5 on it.
         */
        public static final EnchantmentPredicate FORTUNE_5_OR_HIGHER = new EnchantmentPredicate(Enchantments.FORTUNE, NumberRange.IntRange.atLeast(5));
}
