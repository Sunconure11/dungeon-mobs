package com.gw.dm.util;

import static com.gw.dm.DungeonMobs.MODID;
import static com.gw.dm.DungeonMobs.instance;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.gw.dm.entity.EntityGhoul;

public class MobRegistrar {
	public static final int ghoulID			= 202;
	
	public static void registerMobs() {	    
		// GHOUL
		if(true/*spawnGhoul*/) {
		 	EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMGhoul"), 
		 			EntityGhoul.class, MODID + ".DMGhoul", ghoulID, instance, 80, 3, 
		 				true, 0x5F3E67, 0x362C1A);
		 }
	}
	
	
	public static void registerSpawns() {
		Biome[] biomes = getBiomeArray();
		EntityRegistry.addSpawn(EntityGhoul.class, 5, 2, 4, EnumCreatureType.MONSTER, biomes);
	}
	
	
	private static Biome[] getBiomeArray() {
		int i = 0;
		for(Biome biome: Biome.REGISTRY) {
			i++;
		}
		Biome[] biomes = new Biome[i];
		i = 0;
		for(Biome biome: Biome.REGISTRY) {
			biomes[i++] = biome;
		}
		return biomes;
	}

}
