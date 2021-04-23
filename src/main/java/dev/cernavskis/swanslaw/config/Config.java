package dev.cernavskis.swanslaw.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class Config {
  public static final ForgeConfigSpec CONFIG_SPEC;
  public static final Config CONFIG;

  public final IntValue oreDepth;
  public final ConfigValue<String> replaceWith;
  public final IntValue maxHeight;

  static {
    Pair<Config, ForgeConfigSpec> specPair = new Builder().configure(Config::new);

    CONFIG_SPEC = specPair.getRight();
    CONFIG = specPair.getLeft();
  }

  public Config(Builder builder) {
    oreDepth = builder
      .comment("How far from the walls should ores generate?")
      .defineInRange("oreDepth", 5, 0, Integer.MAX_VALUE);

    replaceWith = builder
      .comment("What should the ores* be replaced with?\n* - blocks in the forge:ores tag")
      .define("replaceWith", "minecraft:stone");

    maxHeight = builder
      .comment("Starting at what height should the ores be generated normally?")
      .defineInRange("maxHeight", 48, 0, 256);
  }
}
