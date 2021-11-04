package mods.SufficientlyPositive.GoldToolsPlus.mixins.monsters;

import mods.SufficientlyPositive.GoldToolsPlus.game.structures.Structures;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseChunkGenerator.class)
public abstract class NoiseChunkGeneratorMixin {

    @Inject(
            method = "getEntitySpawnList",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void getStructureSpawnList(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos, CallbackInfoReturnable<Pool<SpawnSettings.SpawnEntry>> cir) {
        Pool<SpawnSettings.SpawnEntry> pool = null;

        if (accessor.getStructureAt(pos, false, Structures.INFUSER_ALTAR).hasChildren()) {
            if (group == SpawnGroup.MONSTER) {
                pool = Structures.INFUSER_ALTAR.getMonsterSpawns();
            }
        }

        if(pool != null) cir.setReturnValue(pool);
    }
}
