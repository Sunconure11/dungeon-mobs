package com.gw.dm.util;

import static com.gw.dm.DungeonMobs.MODID;
import static com.gw.dm.DungeonMobs.instance;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.gw.dm.entity.EntityGhoul;

public class MobRegistrar {
	// Fixme: Now that we aren't using vanilla's namespace, should 
	// these be changed to start with 0?	
	public static final int  rustMonsterID		= 201;
	public static final int  ghoulID			= 202;
	public static final int  shriekerID			= 203;
	public static final int  umberHulkID		= 204;
	public static final int  hookHorrorID		= 205;
	public static final int  ahrimanID			= 206;
	public static final int  eyerayID			= 207;
	public static final int  trollID			= 208;
	public static final int  caveFisherID		= 209;
	public static final int  destrachanID		= 210;
	public static final int  sonicBoomID		= 211;
	public static final int  illithidID			= 212;
	public static final int  netherHoundID		= 213;
	public static final int  rakshasaID			= 214;
	public static final int  rakshasaImageID	= 215;
	public static final int  magicMissileID		= 216;
	public static final int  lizalfosID			= 217;
	public static final int  cockatriceID		= 218;
	public static final int  petrifiedID		= 219;
	public static final int  manticoreID		= 222;
	public static final int  bladeTrapID		= 223;
	public static final int  thoqquaID			= 224;
	public static final int  vescavorID			= 225;
	public static final int  beamosID			= 226;
	public static final int  beamosBeamID	= 227;
	
	
	/**
	 * Register all mobs.  This should be called during pre-init, 
	 * just before registering the mob renderers.
	 */
	public static void registerMobs() {	    
		// GHOUL
		if(true/*spawnGhoul*/) { // TDOD: Make spawns configurable
		 	EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMGhoul"), 
		 			EntityGhoul.class, MODID + ".DMGhoul", ghoulID, instance, 80, 3, 
		 				true, 0x5F3E67, 0x362C1A);
		 }
	}
	
	
	/**
	 * Registers all natural spawns.
	 * 
	 * This should be called during post-init (when all modded 
	 * biomes have hopefully been registered).
	 */
	public static void registerSpawns() {
		Biome[] biomes = getBiomeArray();
		EntityRegistry.addSpawn(EntityGhoul.class, 5, 2, 4, EnumCreatureType.MONSTER, biomes);
	}
	
	
	/**
	 * This is a convenience / code-organizing method to get 
	 * all the biomes currently registered.
	 * 
	 * @return An array of all biomes in existance at the time.
	 */
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
