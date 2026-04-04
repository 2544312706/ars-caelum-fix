package com.hollingsworth.ars_caelum.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class CaelumConfig {
    public static ModConfigSpec SERVER_CONFIG;
    public static ModConfigSpec.ConfigValue<String> STARTER_ISLAND_RL;
    public static ModConfigSpec.ConfigValue<List<Integer>> STARTER_RITUAL_OFFSET;
    public static ModConfigSpec.IntValue STARTER_RITUAL_SOURCE;
    public static ModConfigSpec.ConfigValue<String> STARTER_RITUAL_BIOME;

    static {
        ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
        SERVER_BUILDER.push("starter_ritual");
        STARTER_ISLAND_RL = SERVER_BUILDER.comment("The ResourceLocation of the structure to use for the starter island. Default: ars_caelum:starter_island").define("starter_island_rl", "ars_caelum:starter_island");
        STARTER_RITUAL_OFFSET = SERVER_BUILDER.comment("The offset of the structure position for the starting island ritual").define("ritual_offset", List.of(-7, -5, -9), (t) -> true);
        STARTER_RITUAL_SOURCE = SERVER_BUILDER.comment("The amount of source required to perform the starting island ritual").defineInRange("ritual_source", 0, 0, 10000);
        STARTER_RITUAL_BIOME = SERVER_BUILDER.comment("The biome to use for the starting island ritual, like minecraft:plains. Providing nothing will not change the biome.").define("ritual_biome", "");
        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    public static String getStarterIslandRL() {
        try {
            String value = STARTER_ISLAND_RL.get();
            return value != null && !value.isEmpty() ? value : "ars_caelum:starter_island";
        } catch (Exception e) {
            return "ars_caelum:starter_island";
        }
    }

    public static List<Integer> getStarterRitualOffset() {
        try {
            List<Integer> value = STARTER_RITUAL_OFFSET.get();
            return value != null && value.size() == 3 ? value : List.of(-7, -5, -9);
        } catch (Exception e) {
            return List.of(-7, -5, -9);
        }
    }

    public static int getStarterRitualSource() {
        try {
            return STARTER_RITUAL_SOURCE.get();
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getStarterRitualBiome() {
        try {
            String value = STARTER_RITUAL_BIOME.get();
            return value != null ? value : "";
        } catch (Exception e) {
            return "";
        }
    }
}

