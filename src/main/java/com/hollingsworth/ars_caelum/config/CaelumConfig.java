package com.hollingsworth.ars_caelum.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CaelumConfig {
    private static final Logger LOGGER = LogManager.getLogger();
    public static ModConfigSpec SERVER_CONFIG;
    public static ModConfigSpec.ConfigValue<String> STARTER_ISLAND_RL;
    public static ModConfigSpec.ConfigValue<List<? extends Number>> STARTER_RITUAL_OFFSET;
    public static ModConfigSpec.IntValue STARTER_RITUAL_SOURCE;
    public static ModConfigSpec.ConfigValue<String> STARTER_RITUAL_BIOME;

    static {
        ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
        SERVER_BUILDER.push("starter_ritual");
        STARTER_ISLAND_RL = SERVER_BUILDER.comment("The ResourceLocation of the structure to use for the starter island. Default: ars_caelum:starter_island").define("starter_island_rl", "ars_caelum:starter_island");
        STARTER_RITUAL_OFFSET = SERVER_BUILDER.comment("The offset of the structure position for the starting island ritual").defineList("ritual_offset", List.of(-7, -5, -9), value -> value instanceof Number);
        STARTER_RITUAL_SOURCE = SERVER_BUILDER.comment("The amount of source required to perform the starting island ritual").defineInRange("ritual_source", 0, 0, 10000);
        STARTER_RITUAL_BIOME = SERVER_BUILDER.comment("The biome to use for the starting island ritual, like minecraft:plains. Providing nothing will not change the biome.").define("ritual_biome", "");
        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    public static String getStarterIslandRL() {
        try {
            String value = STARTER_ISLAND_RL.get();
            return !value.isEmpty() ? value : "ars_caelum:starter_island";
        } catch (Exception e) {
            LOGGER.debug("Failed to get starter_island_rl config value, using default: ars_caelum:starter_island", e);
            return "ars_caelum:starter_island";
        }
    }

    public static List<Integer> getStarterRitualOffset() {
        try {
            List<? extends Number> value = STARTER_RITUAL_OFFSET.get();
            if (value.size() != 3) {
                return List.of(-7, -5, -9);
            }

            List<Integer> converted = new ArrayList<>(3);
            for (Number number : value) {
                converted.add(number.intValue());
            }
            return converted;
        } catch (Exception e) {
            LOGGER.debug("Failed to get ritual_offset config value, using default: [-7, -5, -9]", e);
            return List.of(-7, -5, -9);
        }
    }

    public static int getStarterRitualSource() {
        try {
            return STARTER_RITUAL_SOURCE.get();
        } catch (Exception e) {
            LOGGER.debug("Failed to get ritual_source config value, using default: 0", e);
            return 0;
        }
    }

    public static String getStarterRitualBiome() {
        try {
            String value = STARTER_RITUAL_BIOME.get();
            return !value.isEmpty() ? value : "";
        } catch (Exception e) {
            LOGGER.debug("Failed to get ritual_biome config value, using default: ''", e);
            return "";
        }
    }
}

