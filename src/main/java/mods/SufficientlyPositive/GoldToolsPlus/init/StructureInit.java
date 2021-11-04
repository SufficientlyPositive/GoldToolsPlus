package mods.SufficientlyPositive.GoldToolsPlus.init;

import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import mods.SufficientlyPositive.GoldToolsPlus.game.structures.ConfiguredStructures;
import mods.SufficientlyPositive.GoldToolsPlus.game.structures.Structures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.world.biome.BiomeKeys;
import org.apache.logging.log4j.Level;

public class StructureInit {

    @SuppressWarnings("deprecation")
    public static void init() {

        Structures.setupAndRegisterStructures();
        ConfiguredStructures.registerConfiguredStructures();

        BiomeModifications.create(GoldToolsPlusHelperFunctions.newID("infuser_altar_addition"))
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.foundInTheNether().and(BiomeSelectors.excludeByKey(BiomeKeys.SOUL_SAND_VALLEY)),
                        context -> context.getGenerationSettings().addBuiltInStructure(ConfiguredStructures.CONFIGURED_INFUSER_ALTAR));

        GoldToolsPlusHelperFunctions.log(Level.INFO, "Structures registered.");
    }

}
