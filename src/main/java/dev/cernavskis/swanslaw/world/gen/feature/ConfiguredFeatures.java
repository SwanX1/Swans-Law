package dev.cernavskis.swanslaw.world.gen.feature;

import java.util.ArrayList;
import java.util.List;

import dev.cernavskis.swanslaw.SwansLaw;
import dev.cernavskis.swanslaw.config.Config;
import dev.cernavskis.swanslaw.world.gen.feature.config.OreReplacerFeatureConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features.Placements;

public class ConfiguredFeatures {
  public static final List<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = new ArrayList<>();

  public static final ConfiguredFeature<?, ?> ORE_REPLACER = register("ore_replacer",
    Features.ORE_REPLACER.configured(
      new OreReplacerFeatureConfig(
        Config.CONFIG.oreDepth.get(),
        Config.CONFIG.replaceWith.get(),
        Config.CONFIG.maxHeight.get(),
        (List<String>) Config.CONFIG.replaceBlocks.get()
      )
    )
  ).decorated(Placements.HEIGHTMAP);

  private static final ConfiguredFeature<?, ?> register(String name, ConfiguredFeature<?, ?> configuredFeature) {
		ResourceLocation resourceLocation = new ResourceLocation(SwansLaw.MODID, name);
    return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, resourceLocation, configuredFeature);
  }
}
