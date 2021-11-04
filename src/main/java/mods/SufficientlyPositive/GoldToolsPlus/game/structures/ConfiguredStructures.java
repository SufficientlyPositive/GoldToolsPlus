package mods.SufficientlyPositive.GoldToolsPlus.game.structures;

import mods.SufficientlyPositive.GoldToolsPlus.functions.GoldToolsPlusHelperFunctions;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;


public class ConfiguredStructures {
    public static ConfiguredStructureFeature<?, ?> CONFIGURED_INFUSER_ALTAR;

    static {
        CONFIGURED_INFUSER_ALTAR = Structures.INFUSER_ALTAR.configure(DefaultFeatureConfig.DEFAULT);
    }

    /**
     * Registers the configured structure which is what gets added to the biomes.
     * You can use the same identifier for the configured structure as the regular structure
     * because the two fo them are registered to different registries.
     *
     * We can register configured structures at any time before a world is clicked on and made.
     * But the best time to register configured features by code is honestly to do it in onInitialize.
     */
    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;

        Registry.register(registry, GoldToolsPlusHelperFunctions.newID("configured_infuser_altar"), CONFIGURED_INFUSER_ALTAR);
        // additional configured structures would be registered here
    }
}