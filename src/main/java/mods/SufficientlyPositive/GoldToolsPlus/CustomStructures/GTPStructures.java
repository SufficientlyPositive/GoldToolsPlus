package mods.SufficientlyPositive.GoldToolsPlus.CustomStructures;

import mods.SufficientlyPositive.GoldToolsPlus.CustomStructures.StructureInfo.InfuserAltarStructure;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class GTPStructures {

    public static final String INFUSER_ALTAR_NAME = "infuser-altar";
    public static StructureFeature<DefaultFeatureConfig> INFUSER_ALTAR;

    static {
        INFUSER_ALTAR = new InfuserAltarStructure(DefaultFeatureConfig.CODEC);
    }

    public static void setupAndRegisterStructures() {

        // register infuser altar
        FabricStructureBuilder.create(GoldToolsPlus.newID(INFUSER_ALTAR_NAME), INFUSER_ALTAR)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(
                        16,
                        5,
                        576426189))
                .superflatFeature(INFUSER_ALTAR.configure(FeatureConfig.DEFAULT))
                .adjustsSurface()
                .register();
    }
}