package dev.cernavskis.swanslaw;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.cernavskis.swanslaw.config.Config;
import dev.cernavskis.swanslaw.world.gen.feature.ConfiguredFeatures;
import dev.cernavskis.swanslaw.world.gen.feature.Features;

@Mod(SwansLaw.MODID)
public class SwansLaw {
  public static final String MODID = "swanslaw";
  public static final Logger LOGGER = LogManager.getLogger();
  
  public SwansLaw() {
    MinecraftForge.EVENT_BUS.register(this);
    SwansLaw.LOGGER.debug("Registering Swan's Law config");
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
  }

  @SubscribeEvent
  public void onRegisterFeature(RegistryEvent.Register<Feature<?>> event) {
    SwansLaw.LOGGER.info("Registering generation features.");
    Features.FEATURES.forEach(feature -> event.getRegistry().register(feature));
  }

  @SubscribeEvent
  public void onBiomeLoading(BiomeLoadingEvent event) {
    SwansLaw.LOGGER.debug("Adding ore replacing feature to biome {}", event.getName().toString());
    event.getGeneration().addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ConfiguredFeatures.ORE_REPLACER);
  }
}
