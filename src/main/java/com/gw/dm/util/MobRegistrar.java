package com.gw.dm.util;

import com.gw.dm.entity.*;
import com.gw.dm.projectile.EntityEldermobBall;
import com.gw.dm.projectile.EntityEyeRay;
import com.gw.dm.projectile.EntityLightball;
import com.gw.dm.projectile.EntityMagicMissile;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.ArrayList;
import java.util.List;

import static com.gw.dm.DungeonMobs.MODID;
import static com.gw.dm.DungeonMobs.instance;
import static com.gw.dm.util.ConfigHandler.*;

public class MobRegistrar {
	public static final int revenantID = 0;
	public static final int rustMonsterID = 1;
	public static final int ghoulID = 2;
	public static final int shriekerID = 3;
	public static final int umberHulkID = 4;
	public static final int hookHorrorID = 5;
	public static final int ahrimanID = 6;
	public static final int eyerayID = 7;
	public static final int trollID = 8;
	public static final int caveFisherID = 9;
	public static final int destrachanID = 10;
	public static final int sonicBoomID = 11;
	public static final int illithidID = 12;
	public static final int netherHoundID = 13;
	public static final int rakshasaID = 14;
	public static final int rakshasaImageID = 15;
	public static final int magicMissileID = 16;
	public static final int lizalfosID = 17;
	public static final int cockatriceID = 18;
	public static final int petrifiedID = 19;
	public static final int manticoreID = 22;
	public static final int bladeTrapID = 23;
	public static final int thoqquaID = 24;
	public static final int vescavorID = 25;
	public static final int beamosID = 26;
	public static final int beamosBeamID = 27;
	public static final int vampireID = 28;
	public static final int lightballID = 29;
	public static final int fallenAngelID = 30;
	public static final int firecloudID = 31;
	public static final int poisoncloudID = 32;
	public static final int darkballID = 33;
	public static final int eldermobID = 34;
	public static final int fireparticloidID = 35;

	// FIXME: All identifiers should now be all lower case!

	/**
	 * Register all mobs.  This should be called during pre-init,
	 * just before registering the mob renderers.
	 */
	public static void registerMobs() {
		// GHOUL
		if (spawnGhoul) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMGhoul"),
					EntityGhoul.class, MODID + ".DMGhoul", ghoulID, instance, 80, 3,
					true, 0x5F3E67, 0x362C1A);
		}
		// BEHOLDER
		if (spawnBeholder) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMBeholder"),
					EntityAhriman.class, MODID + ".DMBeholder", ahrimanID,
					instance, 80, 3, true, 0x720000, 0xFFF99A);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMEyeRay"),
					EntityEyeRay.class, MODID + "DMEyeRay", eyerayID, instance, 80, 1, true);
		}
		// TROLL
		if (spawnTroll) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMTroll"),
					EntityTroll.class, MODID + ".DMTroll", trollID, instance, 80, 3, true,
					0x164300, 0x292929);
		}
		// MANTICORE
		if (spawnManticore) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMManticore"),
					EntityManticore.class, MODID + ".DMManticore", manticoreID, instance, 80, 3, true,
					0xd28247, 0x201f1d);
		}
		// REVENANT
		if (spawnRevenant) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMRevenant"),
					EntityRevenant.class, MODID + ".DMRevenant", revenantID, instance, 80, 3, true,
					0x004400, 0x777777);
		}
		// VAMPIRE
		if (spawnVampire) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMVampire"),
					EntityVampire.class, MODID + ".DMVampire", vampireID, instance, 80, 2, true,
					0x000000, 0x550000);
		}
		// HOOK HORROR
		if (spawnHookHorror) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMHookHorror"),
					EntityHookHorror.class, MODID + ".DMHookHorror", hookHorrorID, instance, 80, 3, true,
					0x4372AA, 0xD9D9F3);
		}
		// DESTRACHAN
		if (spawnDestrachan) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMDestrachan"),
					EntityDestrachan.class, MODID + ".DMDestrachan", destrachanID, instance, 80, 3, true,
					0xc99918, 0x161616);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMSonicBoom"),
					EntityEyeRay.class, MODID + "DMSonicBoom", sonicBoomID, instance, 80, 1, true);
		}
		// CAVE FISHER
		if (spawnCaveFisher) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMCaveFisher"),
					EntityCaveFisher.class, MODID + ".DMCaveFisher", caveFisherID, instance, 80, 3, true,
					0xf6e7cc, 0x29a0b2);
		}
		// HELL HOUND
		if (spawnHellHound) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMNetherHound"),
					EntityNetherHound.class, MODID + ".DMNetherHound", netherHoundID, instance, 80, 3, true,
					0xf9390d, 0x775532);
		}
		// RUST MONSTER
		if (spawnRustMonster) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMRustMonster"),
					EntityRustMonster.class, MODID + ".DMRustMonster", rustMonsterID, instance, 80, 3, true,
					0x643200, 0x7C0F0F);
		}
		// UMBER HULK
		if (spawnUmberHulk) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMUmberHulk"),
					EntityUmberHulk.class, MODID + ".DMUmberHulk", umberHulkID, instance, 80, 3, true,
					0x734A12, 0x8B795E);
		}
		// COCKATRICE
		if (spawnUmberHulk) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMPetrified"),
					EntityPetrified.class, MODID + ".DMPetrified", petrifiedID, instance, 80, 3, true);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMCockatrice"),
					EntityCockatrice.class, MODID + ".DMCockatrice", cockatriceID, instance, 80, 3, true,
					0x8acf92, 0x93ab96);
		}
		// SHRIEKER
		if (spawnShrieker) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMShrieker"),
					EntityShrieker.class, MODID + ".DMShrieker", shriekerID, instance, 80, 3, true,
					0xBBA786, 0xE4DCB0);
		}
		// THOQQUA
		if (spawnThoqqua) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMThoqqua"),
					EntityThoqqua.class, MODID + ".DMThoqqua", thoqquaID, instance, 80, 3, true,
					0xf1c33a, 0xb08968);
		}
		// VESCAVOR
		if (spawnVescavor) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMVescavor"),
					EntityVescavor.class, MODID + ".DMVescavor", vescavorID, instance, 80, 3, true,
					0x170007, 0xa05155);
		}
		// MIND FLAYER
		if (spawnIllithid) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMIllithid"),
					EntityIllithid.class, MODID + ".DMIllithid", illithidID, instance, 80, 3, true,
					0x9932CD, 0xf6f4ba);
		}
		// RAKSHASA
		if (spawnRakshasa) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMRakshasa"),
					EntityRakshasa.class, MODID + ".DMRakshasa", rakshasaID, instance, 80, 3, true,
					0xa06c3e, 0xdec580);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMRakshasaImage"),
					EntityRakshasaImage.class, MODID + ".DMRakshasaImage", rakshasaImageID,
					instance, 80, 3, true);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMMagicMissile"),
					EntityMagicMissile.class, MODID + ".DMMagicMissle", magicMissileID,
					instance, 80, 1, true);
		}
		// FALLEN ANGEL
		if (spawnFallenAngel) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMFallenAngel"),
					EntityFallenAngel.class, MODID + ".DMFallenAngel", fallenAngelID,
					instance, 80, 3, true, 0x000000, 0x998800);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMLightBall"),
					EntityLightball.class, MODID + ".DMLightBall", lightballID, instance,
					80, 1, true);
		}
		// LIZALFOS
		if (spawnLizalfos) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMLizalfos"),
					EntityLizalfos.class, MODID + ".DMLizalfos", lizalfosID, instance, 80, 3, true,
					0x257a36, 0xe5d067);
		}
		// OUTER THING
		if (spawnOuterThing) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMEldermob"),
					EntityEldermob.class, MODID + ".DMEldermob", eldermobID, instance, 80, 3, true,
					0x003300, 0x004422);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "DMDarkball"),
					EntityEldermobBall.class, MODID + ".DMDarkball", darkballID,
					instance, 80, 1, true);
		}
	}


	/**
	 * Registers all natural spawns.
	 * <p>
	 * This should be called during post-init (when all modded
	 * biomes have hopefully been registered).
	 */
	public static void registerSpawns() {
		Biome[] biomes = getBiomeArray();
		List<Biome> tmp1 = new ArrayList<>(),
				tmp2 = new ArrayList<>(),
				tmp3 = new ArrayList<>();
		for (Biome biome : biomes) {
			if (BiomeDictionary.hasType(biome, Type.NETHER)) {
				tmp1.add(biome);
			} else if (BiomeDictionary.hasType(biome, Type.END)) {
				tmp2.add(biome);
			} else {
				tmp3.add(biome);
			}
		}

		Biome[] overworld = new Biome[tmp3.size()];
		tmp3.toArray(overworld);
		Biome[] nether = new Biome[tmp1.size()];
		tmp1.toArray(nether);
		Biome[] end = new Biome[tmp2.size()];
		tmp2.toArray(end);

		// FIXME: This should be re-organized to simplified application of a config file.  
		if (spawnGhoul) EntityRegistry.addSpawn(EntityGhoul.class, 5, 2, 4,
				EnumCreatureType.MONSTER, overworld);
		if (spawnBeholder) EntityRegistry.addSpawn(EntityAhriman.class, 2, 1, 1,
				EnumCreatureType.MONSTER, overworld);
		if (spawnRustMonster) EntityRegistry.addSpawn(EntityRustMonster.class, 8, 1, 4,
				EnumCreatureType.MONSTER, overworld);
		if (spawnShrieker) EntityRegistry.addSpawn(EntityShrieker.class, 5, 2, 4,
				EnumCreatureType.MONSTER, overworld);
		if (spawnUmberHulk) EntityRegistry.addSpawn(EntityUmberHulk.class, 2, 1, 1,
				EnumCreatureType.MONSTER, overworld);
		if (spawnHookHorror) EntityRegistry.addSpawn(EntityHookHorror.class, 4, 1, 4,
				EnumCreatureType.MONSTER, overworld);
		if (spawnTroll) EntityRegistry.addSpawn(EntityTroll.class, 6, 1, 4,
				EnumCreatureType.MONSTER, overworld);
		if (spawnCaveFisher) EntityRegistry.addSpawn(EntityCaveFisher.class, 4, 1, 2,
				EnumCreatureType.MONSTER, overworld);
		if (spawnDestrachan) EntityRegistry.addSpawn(EntityDestrachan.class, 5, 1, 3,
				EnumCreatureType.MONSTER, overworld);
		if (spawnIllithid) EntityRegistry.addSpawn(EntityIllithid.class, 3, 1, 1,
				EnumCreatureType.MONSTER, overworld);
		if (spawnHellHound) {
			EntityRegistry.addSpawn(EntityNetherHound.class, 4, 1, 2,
					EnumCreatureType.MONSTER, overworld);
			EntityRegistry.addSpawn(EntityNetherHound.class, 10, 4, 4,
					EnumCreatureType.MONSTER, nether);
		}
		if (spawnRakshasa) EntityRegistry.addSpawn(EntityRakshasa.class, 3, 1, 1,
				EnumCreatureType.MONSTER, overworld);
		if (spawnLizalfos) EntityRegistry.addSpawn(EntityLizalfos.class, 2, 2, 2,
				EnumCreatureType.MONSTER, overworld);
		if (spawnCockatrice) EntityRegistry.addSpawn(EntityCockatrice.class, 5, 1, 3,
				EnumCreatureType.MONSTER, overworld);
		if (spawnManticore) EntityRegistry.addSpawn(EntityManticore.class, 4, 1, 2,
				EnumCreatureType.MONSTER, overworld);
		if (spawnThoqqua) {
			EntityRegistry.addSpawn(EntityThoqqua.class, 3, 1, 1,
					EnumCreatureType.MONSTER, overworld);
			EntityRegistry.addSpawn(EntityThoqqua.class, 6, 1, 2,
					EnumCreatureType.MONSTER, nether);
		}
		if (spawnVescavor) EntityRegistry.addSpawn(EntityVescavor.class, 6, 2, 8,
				EnumCreatureType.MONSTER, overworld);
		if (spawnRevenant) EntityRegistry.addSpawn(EntityRevenant.class, 3, 1, 2,
				EnumCreatureType.MONSTER, overworld);
		if (spawnVampire) EntityRegistry.addSpawn(EntityVampire.class, 2, 1, 2,
				EnumCreatureType.MONSTER, overworld);
		if (spawnFallenAngel) {
			EntityRegistry.addSpawn(EntityFallenAngel.class, 1, 1, 1, EnumCreatureType.MONSTER, overworld);
			EntityRegistry.addSpawn(EntityFallenAngel.class, 2, 1, 1, EnumCreatureType.MONSTER, nether);
		}
		if (spawnOuterThing) {
			EntityRegistry.addSpawn(EntityEldermob.class, 1, 1, 1, EnumCreatureType.MONSTER, overworld);
			EntityRegistry.addSpawn(EntityEldermob.class, 1, 1, 1, EnumCreatureType.MONSTER, end);
		}
	}


	/**
	 * This is a convenience / code-organizing method to get
	 * all the biomes currently registered.
	 *
	 * @return An array of all biomes in existance at the time.
	 */
	private static Biome[] getBiomeArray() {
		int i = 0;
		for (Biome biome : Biome.REGISTRY) {
			i++;
		}
		Biome[] biomes = new Biome[i];
		i = 0;
		for (Biome biome : Biome.REGISTRY) {
			biomes[i++] = biome;
		}
		return biomes;
	}


	public static void addToVanillaDungeons() {
		if (spawnGhoul) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMGhoul"), 1);
		if (spawnBeholder) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMBeholder"), 1);
		if (spawnTroll) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMTroll"), 1);
		if (spawnManticore) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMManticore"), 1);
		if (spawnRevenant) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMRevenant"), 1);
		if (spawnVampire) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMVampire"), 1);
		if (spawnHookHorror) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMHookHorror"), 1);
		if (spawnDestrachan) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMDestrachan"), 1);
		if (spawnCaveFisher) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMCaveFisher"), 1);
		if (spawnHellHound) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMHellHound"), 1);
		if (spawnRustMonster) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMRustMonster"), 1);
		if (spawnUmberHulk) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMUmberHulk"), 1);
		if (spawnCockatrice) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMCockatrice"), 1);
		if (spawnThoqqua) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMThoqqua"), 1);
		if (spawnVescavor) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMVescavor"), 1);
		if (spawnIllithid) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMIllithid"), 1);
		if (spawnRakshasa) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMRakshasa"), 1);
		if (spawnLizalfos) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "DMLizalfos"), 1);
	}

}
