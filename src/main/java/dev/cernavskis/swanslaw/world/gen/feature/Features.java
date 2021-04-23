package dev.cernavskis.swanslaw.world.gen.feature;

import java.util.ArrayList;
import java.util.List;

import dev.cernavskis.swanslaw.SwansLaw;
import dev.cernavskis.swanslaw.world.gen.feature.config.OreReplacerFeatureConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class Features {
  public static final List<Feature<?>> FEATURES = new ArrayList<>();

  public static final Feature<OreReplacerFeatureConfig> ORE_REPLACER = register("ore_replacer", new OreReplacerFeature(OreReplacerFeatureConfig.CODEC));
  
  private static <C extends IFeatureConfig, F extends Feature<C>> F register(String name, F feature) {
    ResourceLocation resourceLocation = new ResourceLocation(SwansLaw.MODID, name);
    feature.setRegistryName(resourceLocation);
    FEATURES.add(feature);
    return feature;
  }
}
