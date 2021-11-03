package mods.SufficientlyPositive.GoldToolsPlus;

import mods.SufficientlyPositive.GoldToolsPlus.LootTableClasses.GTPLootTableFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.LootTableClasses.GTPLootTablePools;
import mods.SufficientlyPositive.GoldToolsPlus.LootTableClasses.GTPLootTableConstants;
import mods.SufficientlyPositive.GoldToolsPlus.init.GTPInfuserInit;
import mods.SufficientlyPositive.GoldToolsPlus.init.GTPItemsInit;
import mods.SufficientlyPositive.GoldToolsPlus.init.GTPStructureInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Pre-release todo list

// Before refactoring, create github repository of the mod [x]

// Refactor code to follow standard: "_" instead of "-" for word separation [x]
// Refactor enchantment mixins to use ItemStack.addEnchantment [ ]
// Refactor sub-packages so they make sense [ ]
// Refactor lootpools so they make more sense, remove the "createpool" functions and just keep the pools static [ ]
// Refactor various helper functions into some form of helper function class (log and newID should not be here) [ ]

// Add some form of config file [ ]
// -> Allow config file to take json objects matching GTPEnchantmentBoost [ ]
// -> Throw errors if items listed are not registered/there [ ]
// -> Add some checking for 0 or negative enchantment levels just in case [ ]

// Reduce durability to 48 (1.5x gold) [x]
// -> reduce durability [x]

// Reduce venerable gold shard droprate to ~1/5th of current [x]

// Decide whether to name to white_gold instead of venerable_gold [ ]

// Create smithing table recipes for the armour [x]

// Somehow boost gold mining material to Stone level [ ]

// How to mine the infuser [x]
// -> decide on tool level [x]
//    - diamond [x]

// Make the infuser structure part of the nether fortress instead [ ]
// -> Check for framework mods to let you add to vanilla structures [ ]
//    - yes [ ]
//    - no [ ]
// -> Change loot table of chest to more closely resemble nether fortress loot [ ]
//    - keep gilded blackstone in the chest loot [ ]
// -> If not automatic, make the room spawn wither skeletons and zombified piglins [ ]
// -> Try to make frequency of spawning ~ 2 in 3 nether fortresses [ ]

// Edit infuser screen [ ]
// -> Reduce darkness of icon shapes to smithing table levels [ ]
// -> Make sure shift-clicking an item only works if it would go in the correct slot [ ]
// -> Check if possible to animate [ ]
//    - not possible [ ]
//    - possible [ ]
//      - change the screen whenever a correct recipe item is inserted into a specific slot [ ]

public class GoldToolsPlus implements ModInitializer {

    public static final String MOD_ID = "goldtoolsplus";
    public static final String MOD_NAME = "Gold Tools Plus";
    public static final Logger LOGGER = LogManager.getLogger("GoldToolsPlus");

    public static final ItemGroup GTP_ITEM_GROUP = FabricItemGroupBuilder
            .create(newID("all"))
            .icon(() -> new ItemStack(GTPItemsInit.VENERABLE_GOLD_PICKAXE))
            .build();

    @Override
    public void onInitialize() {

        GTPItemsInit.init();
        log(Level.INFO, "Items registered");

        GTPStructureInit.init();
        log(Level.INFO, "Structures registered");

        GTPInfuserInit.init();
        log(Level.INFO, "Infuser recipes/screen registered");

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {

            if (GTPLootTableConstants.PIGLIN_LOOT_TABLE_ID.equals(id)) {
                table.pool(GTPLootTableFunctions.createMobItemPool(
                        ConstantLootNumberProvider.create(1.0F),
                        BinomialLootNumberProvider.create(1, 0.01F),
                        GTPItemsInit.VENERABLE_GOLD_SHARD
                ));
            }

            if (GTPLootTableConstants.PIGLIN_BRUTE_LOOT_TABLE_ID.equals(id)) {
                table.pool(GTPLootTableFunctions.createMobItemPool(
                        ConstantLootNumberProvider.create(1.0F),
                        BinomialLootNumberProvider.create(1, 0.75F),
                        GTPItemsInit.VENERABLE_GOLD_SHARD
                ));
            }

            if (GTPLootTableConstants.NETHER_GOLD_ORE_LOOT_TABLE_ID.equals(id)) {
                table.pool(GTPLootTablePools.NETHER_GOLD_ORE_ADDITIONAL);
            }

            if (LootTables.RUINED_PORTAL_CHEST.equals(id)) {
                table.pool(GTPLootTableFunctions.createItemPool(
                        BinomialLootNumberProvider.create(2, 0.5F),
                        BinomialLootNumberProvider.create(1, 0.05F),
                        GTPItemsInit.VENERABLE_GOLD_SHARD));
            }

            if (LootTables.BASTION_BRIDGE_CHEST.equals(id)) {
                table.pool(GTPLootTablePools.BASTION_BRIDGE_CHESTS_TOOLS);
                table.pool(GTPLootTableFunctions.createItemPool(
                        UniformLootNumberProvider.create(0, 2),
                        BinomialLootNumberProvider.create(2, 0.2F),
                        GTPItemsInit.VENERABLE_GOLD_SHARD));
            }

            if (LootTables.BASTION_HOGLIN_STABLE_CHEST.equals(id)) {
                table.pool(GTPLootTableFunctions.createItemPool(
                        UniformLootNumberProvider.create(0, 1),
                        BinomialLootNumberProvider.create(2, 0.2F),
                        GTPItemsInit.VENERABLE_GOLD_SHARD));
            }

            if (LootTables.BASTION_OTHER_CHEST.equals(id)) {
                table.pool(GTPLootTablePools.BASTION_OTHER_CHESTS_TOOLS);
                table.pool(GTPLootTableFunctions.createItemPool(
                        UniformLootNumberProvider.create(1, 3),
                        BinomialLootNumberProvider.create(5, 0.2F),
                        GTPItemsInit.VENERABLE_GOLD_SHARD));
            }

            if (LootTables.BASTION_TREASURE_CHEST.equals(id)) {
                table.pool(GTPLootTablePools.BASTION_TREASURE_CHESTS_TOOLS);
                table.pool(GTPLootTableFunctions.createItemPool(
                        UniformLootNumberProvider.create(1, 3),
                        BinomialLootNumberProvider.create(3, 0.3F),
                        GTPItemsInit.VENERABLE_GOLD_SHARD));
                table.pool(GTPLootTableFunctions.createItemPool(
                        ConstantLootNumberProvider.create(1.0F),
                        BinomialLootNumberProvider.create(2, 0.2F),
                        GTPItemsInit.VENERABLE_GOLD_INGOT));
            }

            if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                table.pool(GTPLootTablePools.END_CITY_CHESTS_TOOLS);
                table.pool(GTPLootTableFunctions.createItemPool(
                    BinomialLootNumberProvider.create(2, 0.1F),
                    BinomialLootNumberProvider.create(6, 0.7F),
                    GTPItemsInit.VENERABLE_GOLD_INGOT));
            }
        });
        log(Level.INFO, "Loot tables changed!");
        log(Level.INFO, "Finished initialisation!");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    /**
     * returns a new identifier registered under the mod id
     *
     * @param path replaces the path variable in the identifier constructor
     * @return a new identifier tied to GoldToolsPlus
     */
    public static Identifier newID(String path) {
        return new Identifier(MOD_ID, path);
    }
}
