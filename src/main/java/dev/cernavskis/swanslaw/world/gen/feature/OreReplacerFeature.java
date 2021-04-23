package dev.cernavskis.swanslaw.world.gen.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mojang.serialization.Codec;

import dev.cernavskis.swanslaw.world.gen.feature.config.OreReplacerFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.Tags;

public class OreReplacerFeature extends Feature<OreReplacerFeatureConfig> {
  public OreReplacerFeature(Codec<OreReplacerFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, OreReplacerFeatureConfig config) {
    final int oreDepth = config.oreDepth;
    final int maxHeight = config.maxHeight;
    final Block replaceWith = Registry.BLOCK.get(new ResourceLocation(config.replaceWith));
    
    List<BlockPos> removePositions = new ArrayList<>(); // Store 
    for (int x = 0; x < 16; x++) {
      for (int z = 0; z < 16; z++) {

        BlockPos heightPos = world.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos.offset(x, 0, z));
        if (heightPos.getY() > maxHeight) {
          heightPos = new BlockPos(heightPos.getX(), maxHeight, heightPos.getZ());
        }

        for (int y = heightPos.getY(); y > 0; y--) { //! Change the y < 0 in 1.17
          BlockPos changePos = heightPos.below(y);
          if (shouldRemove(world, changePos, oreDepth)) {
            removePositions.add(changePos);
          }
        }

      }
    }

    for (BlockPos changePos : removePositions) {
      world.setBlock(changePos, replaceWith.defaultBlockState(), 3);
    }

    return true;
  }

  private boolean shouldRemove(ISeedReader world, BlockPos pos, int oreDepth) {
    if (world.getBlockState(pos).getBlock().is(Tags.Blocks.ORES)) {
      for (int x = -oreDepth; x < oreDepth; x++) {
        for (int y = -oreDepth; y < oreDepth; y++) {
          if (pos.getY() + y < 0) continue;
          for (int z = -oreDepth; z < oreDepth; z++) {
            Vector3i offsetVec = new Vector3i(x, y, z);
            if (offsetVec.distManhattan(Vector3i.ZERO) > oreDepth) continue;
            BlockPos checkPos = pos.offset(offsetVec);
            if (isOpenBlock(world.getBlockState(checkPos).getBlock())) {
              return false;
            }
          }
        }
      }
      return true;
    } else {
      return false;
    }
  }

  private boolean isOpenBlock(Block block) {
    return 
      block == Blocks.AIR ||
      block == Blocks.CAVE_AIR ||
      block == Blocks.LAVA ||
      block == Blocks.WATER;
  }
}
