package mods.SufficientlyPositive.GoldToolsPlus.init;

import mods.SufficientlyPositive.GoldToolsPlus.CustomStructures.GTPConfiguredStructures;
import mods.SufficientlyPositive.GoldToolsPlus.CustomStructures.GTPStructures;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.world.biome.BiomeKeys;

public class GTPStructureInit {

    @SuppressWarnings("deprecation")
    public static void init() {

        GTPStructures.setupAndRegisterStructures();
        GTPConfiguredStructures.registerConfiguredStructures();

        BiomeModifications.create(GoldToolsPlus.newID("infuser_altar_addition"))
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.foundInTheNether().and(BiomeSelectors.excludeByKey(BiomeKeys.SOUL_SAND_VALLEY)),
                        context -> context.getGenerationSettings().addBuiltInStructure(GTPConfiguredStructures.CONFIGURED_INFUSER_ALTAR));
    }

}
