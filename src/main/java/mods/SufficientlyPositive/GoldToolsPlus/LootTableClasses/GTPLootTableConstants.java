package mods.SufficientlyPositive.GoldToolsPlus.LootTableClasses;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.util.Identifier;

public class GTPLootTableConstants {

        // Identifiers
        public static final Identifier NETHER_GOLD_ORE_LOOT_TABLE_ID = Blocks.NETHER_GOLD_ORE.getLootTableId();

        public static final Identifier PIGLIN_LOOT_TABLE_ID = EntityType.PIGLIN.getLootTableId();
        public static final Identifier PIGLIN_BRUTE_LOOT_TABLE_ID = EntityType.PIGLIN_BRUTE.getLootTableId();

        // Enchantment Predicate
        public static final EnchantmentPredicate FORTUNE_5_OR_HIGHER = new EnchantmentPredicate(Enchantments.FORTUNE, NumberRange.IntRange.atLeast(5));
}
