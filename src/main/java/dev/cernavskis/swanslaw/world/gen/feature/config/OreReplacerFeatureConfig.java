package dev.cernavskis.swanslaw.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.feature.IFeatureConfig;

public class OreReplacerFeatureConfig implements IFeatureConfig {
  public static final Codec<OreReplacerFeatureConfig> CODEC =
    RecordCodecBuilder.create((builder) -> 
      builder.group(
        Codec.INT.fieldOf("ore_depth").forGetter((config) -> config.oreDepth),
        Codec.STRING.fieldOf("replace_with").forGetter((config) -> config.replaceWith),
        Codec.INT.fieldOf("max_height").forGetter((config) -> config.maxHeight)
      ).apply(builder, OreReplacerFeatureConfig::new)
    );

  public final int oreDepth;
  public final String replaceWith;
  public final int maxHeight;

  public OreReplacerFeatureConfig(int oreDepth, String replaceWith, int maxHeight) {
    this.oreDepth = oreDepth;
    this.replaceWith = replaceWith;
    this.maxHeight = maxHeight;
  }
}
