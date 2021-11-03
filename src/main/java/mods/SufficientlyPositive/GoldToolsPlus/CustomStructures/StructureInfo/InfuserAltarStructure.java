package mods.SufficientlyPositive.GoldToolsPlus.CustomStructures.StructureInfo;

import com.mojang.serialization.Codec;
import mods.SufficientlyPositive.GoldToolsPlus.GoldToolsPlus;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.apache.logging.log4j.Level;

public class InfuserAltarStructure extends StructureFeature<DefaultFeatureConfig> {

    // available spawns inside structure
    private static final Pool<SpawnSettings.SpawnEntry> MONSTER_POOL = Pool.of(
            new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 10, 1, 3),
            new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 90, 3, 7)
    );

    public InfuserAltarStructure(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }



    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return InfuserAltarStructure.Start::new;
    }

    @Override
    public Pool<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return MONSTER_POOL;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator,
                                    BiomeSource biomeSource,
                                    long l,
                                    ChunkRandom chunkRandom,
                                    ChunkPos chunkPosA,
                                    Biome biome,
                                    ChunkPos chunkPosB,
                                    DefaultFeatureConfig featureConfig,
                                    HeightLimitView heightLimitView) {

        // get block position of centre of chunk
        BlockPos centerOfChunk = new BlockPos(chunkPosA.x, 0, chunkPosB.x);
        BlockPos newPos = Start.findValidBlock(centerOfChunk, chunkGenerator, heightLimitView);

        return !centerOfChunk.equals(newPos)
                && newPos.getY() > 35;
                // && newPos.getY() < 120;
    }

    public static class Start extends MarginedStructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos, int i, long l) {
            super(structureIn, chunkPos, i, l);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager,
                         ChunkGenerator chunkGenerator,
                         StructureManager structureManager,
                         ChunkPos chunkPos,
                         Biome biome,
                         DefaultFeatureConfig defaultFeatureConfig,
                         HeightLimitView heightLimitView
                         ) {

            int x = chunkPos.x * 16;
            int z = chunkPos.z * 16;

            BlockPos structureCentrePos = new BlockPos(x, 0, z);
            structureCentrePos = findValidBlock(structureCentrePos, chunkGenerator, heightLimitView);

            StructurePoolFeatureConfig structureSettingsAndStartPool = new StructurePoolFeatureConfig(
                    () -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY)
                            .get(GoldToolsPlus.newID("infuser-altar/start_pool")),
                    5);

            StructurePoolBasedGenerator.generate(
                    dynamicRegistryManager,
                    structureSettingsAndStartPool,
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    structureCentrePos,
                    this,
                    this.random,
                    false,
                    false,
                    heightLimitView);

            // no sinking into the ground!
            this.children.forEach(piece -> piece.translate(0, 1, 0));

            Vec3i structureCenter = this.children.get(0).getBoundingBox().getCenter();
            int xOffset = structureCentrePos.getX() - structureCenter.getX();
            int zOffset = structureCentrePos.getZ() - structureCenter.getZ();
            for(StructurePiece structurePiece : this.children){
                structurePiece.translate(xOffset, 0, zOffset);
            }

            this.setBoundingBoxFromChildren();

            GoldToolsPlus.log(Level.INFO, "Infuser Altar at: " +
                    this.children.get(0).getBoundingBox().getMinX() + " " +
                    this.children.get(0).getBoundingBox().getMinY() + " " +
                    this.children.get(0).getBoundingBox().getMinZ());
        }



        protected static BlockPos findValidBlock(BlockPos pos, ChunkGenerator generator, HeightLimitView heightLimitView) {
            VerticalBlockSample blockColumn = generator.getColumnSample(pos.getX(), pos.getZ(), heightLimitView);
            BlockState prevState = blockColumn.getState(pos);

            for (int i = 0; i < heightLimitView.getHeight(); i++) {
                BlockState state = blockColumn.getState(pos.up(i));

                if (state.isAir() && prevState.getFluidState().isEmpty()) {
                    return pos.up(i);
                }
                prevState = state;
            }
            return pos;
        }
    }
}
