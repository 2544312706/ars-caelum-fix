package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.config.CaelumConfig;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class StarterIslandRitual extends StructureRitual {
    public StarterIslandRitual() {
        super(null, BlockPos.ZERO, 0, null);
    }


    @Override
    public void setup() {
        if(getWorld() != null && !getWorld().isClientSide) {
            this.structure = ResourceLocation.parse(CaelumConfig.getStarterIslandRL());
            List<Integer> offset = CaelumConfig.getStarterRitualOffset();
            this.offset = new BlockPos(offset.get(0), offset.get(1), offset.get(2));
            this.sourceRequired = CaelumConfig.getStarterRitualSource();
            String biomeStr = CaelumConfig.getStarterRitualBiome();
            if (!biomeStr.isEmpty()) {
                getWorld().registryAccess().registry(Registries.BIOME).ifPresent(biomes -> {
                    Biome biome1 = biomes.get(ResourceLocation.parse(biomeStr));
                    if (biome1 != null) {
                        Optional<ResourceKey<Biome>> biomeKey = biomes.getResourceKey(biome1);
                        biomeKey.ifPresent(biomeResourceKey -> this.biome = biomeResourceKey);
                    }
                });
            }
        }
        super.setup();
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.STARTER);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Starter";
    }
}
