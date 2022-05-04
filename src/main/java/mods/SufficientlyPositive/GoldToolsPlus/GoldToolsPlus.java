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

// Make piglins not agro at white gold armour [ ]

// Pixel art for armour [ ]

// Finally:
// Make sure FabricMod.json and everything is correct, should be V1.0 [ ]


// Post Release todo list:

// Add some form of config file [ ]
// -> Allow config file to take json objects matching EnchantmentBoost [ ]
// -> Throw errors if items listed are not registered with the minecraft registries [ ]
// -> Add some checking for 0 or negative enchantment levels just in case [ ]

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
