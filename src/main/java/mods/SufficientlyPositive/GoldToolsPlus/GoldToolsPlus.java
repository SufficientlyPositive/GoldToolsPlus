package mods.SufficientlyPositive.GoldToolsPlus;

import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.game.loottables.LootTablePools;
import mods.SufficientlyPositive.GoldToolsPlus.game.loottables.LootTableConstants;
import mods.SufficientlyPositive.GoldToolsPlus.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Pre-release todo list

// Javadoc comment everything [ ]

// Before refactoring, create github repository of the mod [x]
// Refactor code to follow standard: "_" instead of "-" for word separation [x]
// Refactor enchantment mixins to use ItemStack.addEnchantment, doesn't actually work - kept as-is [x]
// Reduce durability to 48 (1.5x gold) [x]
// Reduce white gold shard droprate to ~1/5th of current [x]
// Create smithing table recipes for the armour [x]
// Check if Accessor mixin is needed or if it works fine as-is [x]
// How to mine the infuser [x]
// -> decide on tool level [x]
//    - diamond [x]
// Fix loot pools generating weirdly enchanted loot? [x]
// -> Check in EnchantWithLevelsLootFunction [x] - seems not applicable but mixin for compatibility anyways (uses enchantmenthelper mixin) [x]
// -> Check in SetEnchantmentsLootFunction [x]
// -> debug using EnchantRandomlyLootFunctionMixin [x]
// /setblock ~ ~2 ~ minecraft:chest{LootTable:"goldtoolsplus:chests/infuser_chest"}
// Refactor sub-packages so they make sense [x]
// Refactor lootpools so they make more sense [x]
// Refactor various helper functions into some form of helper function class (log and newID should not be here) [x]
// Refactor EnchantmentBoost so 1 function can be used to decide how to boost a specific enchantment based on an ItemStack, Enchantment and level [x]
// actually refactor the whole thing, so that there is a masterlist map <Item, int> that is constructed from the EnchantmentBoost objects
// probably initialise the map in EnchantmentBoostInit or something and refer to it from there, recall map.get returns null, so use
// getOrDefault(Item, 0) when finding how much to boost an enchantment on an item.
// Decide whether to name to white_gold instead of white_gold [x]
// Use white_gold [x]
// -> Refactor to rename everything [x]
// Refactor everything to use "newID" instead of "new Identifier" [x]
// Make piglins not agro at white gold armour - No, instead piglins assume you have stolen the white gold and attack you and attack
// Somehow boost gold mining material to Stone level [x]
// Edit infuser screen [x]
// -> Reduce darkness of icon shapes to smithing table levels [x]
// -> Make sure shift-clicking an item only works if it would go in the correct slot [x]
// -> Check if possible to animate [x]
//    - possible [x]
//      - change the screen whenever a correct recipe item is inserted into a specific slot [x]
// EnchantCommand Error spews incorrectly (e.g. enchant protection 7 on chestplate throws "6 > 4 so no enchant sry"
// fix [x]




// Add some form of config file [ ]
// -> Allow config file to take json objects matching EnchantmentBoost [ ]
// -> Throw errors if items listed are not registered with the minecraft registries [ ]
// -> Add some checking for 0 or negative enchantment levels just in case [ ]

// Make the infuser structure part of the nether fortress instead [ ]
// Probabaly make them part of bastion instead, seems easier [ ]
// -> Check for framework mods to let you add to vanilla structures [x]
//    - no [x]
// -> Change loot table of chest to more closely resemble nether fortress loot [ ]
//    - keep gilded blackstone in the chest loot [ ]
// -> If not automatic, make the room spawn wither skeletons and zombified piglins [ ]
// -> Try to make frequency of spawning ~ 2 in 3 nether fortresses [ ]

// Finally:
// Make sure FabricMod.json and everything is correct, should be V1.0 [ ]

public class GoldToolsPlus implements ModInitializer {

    public static final String MOD_ID = "goldtoolsplus";
    public static final String MOD_NAME = "Gold Tools Plus";
    public static final Logger LOGGER = LogManager.getLogger("GoldToolsPlus");

    public static final ItemGroup GTP_ITEM_GROUP = FabricItemGroupBuilder
            .create(GoldToolsPlusHelperFunctions.newID("all"))
            .icon(() -> new ItemStack(ItemsInit.WHITE_GOLD_PICKAXE))
            .build();

    @Override
    public void onInitialize() {

        ItemsInit.init();
        GoldToolsPlusHelperFunctions.log(Level.INFO, "Items registered");

        StructureInit.init();
        RecipeInit.init();
        ScreenHandlerInit.init();
        EnchantmentBoostInit.init();

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {

            if (LootTableConstants.PIGLIN_LOOT_TABLE_ID.equals(id)) {
                table.pool(LootTablePools.PIGLIN_LOOT_ADDITIONAL);
            }

            if (LootTableConstants.PIGLIN_BRUTE_LOOT_TABLE_ID.equals(id)) {
                table.pool(LootTablePools.PIGLIN_BRUTE_LOOT_ADDITIONAL);
            }

            if (LootTableConstants.NETHER_GOLD_ORE_LOOT_TABLE_ID.equals(id)) {
                table.pool(LootTablePools.NETHER_GOLD_ORE_ADDITIONAL);
            }

            if (LootTables.RUINED_PORTAL_CHEST.equals(id)) {
                table.pool(LootTablePools.RUINED_PORTAL_SHARDS);
            }

            if (LootTables.BASTION_BRIDGE_CHEST.equals(id)) {
                table.pool(LootTablePools.BASTION_BRIDGE_TOOLS);
                table.pool(LootTablePools.BASTION_BRIDGE_SHARDS);
            }

            if (LootTables.BASTION_HOGLIN_STABLE_CHEST.equals(id)) {
                table.pool(LootTablePools.BASTION_STABLE_SHARDS);
                table.pool(LootTablePools.BASTION_OTHER_TOOLS);
            }

            if (LootTables.BASTION_OTHER_CHEST.equals(id)) {
                table.pool(LootTablePools.BASTION_OTHER_TOOLS);
                table.pool(LootTablePools.BASTION_OTHER_SHARDS);
            }

            if (LootTables.BASTION_TREASURE_CHEST.equals(id)) {
                table.pool(LootTablePools.BASTION_TREASURE_TOOLS);
                table.pool(LootTablePools.BASTION_TREASURE_SHARDS);
                table.pool(LootTablePools.BASTION_TREASURE_INGOTS);
            }

            if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                table.pool(LootTablePools.END_CITY_TOOLS);
                table.pool(LootTablePools.END_CITY_INGOTS);
            }
        });
        GoldToolsPlusHelperFunctions.log(Level.INFO, "Loot tables changed!");
        GoldToolsPlusHelperFunctions.log(Level.INFO, "Finished initialisation!");
    }
}
