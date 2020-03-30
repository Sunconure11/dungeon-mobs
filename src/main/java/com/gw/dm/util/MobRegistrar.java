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
	public static final int ghostID = 36;
	
	// FIXME: All identifiers should now be all lower case!
	
	/**
	 * Register all mobs.  This should be called during pre-init,
	 * just before registering the mob renderers.
	 */
	public static void registerMobs() {
		// GHOUL
		if (spawnGhoul) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmghoul"), EntityGhoul.class, MODID + ".dmghoul", ghoulID, instance, 80, 3, true, 0x5F3E67, 0x362C1A);
		}
		// BEHOLDER
		if (spawnBeholder) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmbeholder"), EntityAhriman.class, MODID + ".dmbeholder", ahrimanID, instance, 80, 3, true, 0x720000, 0xFFF99A);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmeyeray"), EntityEyeRay.class, MODID + ".dmeyeray", eyerayID, instance, 80, 1, true);
		}
		// TROLL
		if (spawnTroll) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmtroll"), EntityTroll.class, MODID + ".dmtroll", trollID, instance, 80, 3, true, 0x164300, 0x292929);
		}
		// MANTICORE
		if (spawnManticore) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmmanticore"), EntityManticore.class, MODID + ".dmmanticore", manticoreID, instance, 80, 3, true, 0xd28247, 0x201f1d);
		}
		// REVENANT
		if (spawnRevenant) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmrevenant"), EntityRevenant.class, MODID + ".dmrevenant", revenantID, instance, 80, 2, true, 0x004400, 0x777777);
		}
		// VAMPIRE
		if (spawnVampire) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmvampire"), EntityVampire.class, MODID + ".dmvampire", vampireID, instance, 80, 2, true, 0x000000, 0x550000);
		}
		// HOOK HORROR
		if (spawnHookHorror) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmhookhorror"), EntityHookHorror.class, MODID + ".dmhookhorror", hookHorrorID, instance, 80, 3, true, 0x4372AA, 0xD9D9F3);
		}
		// DESTRACHAN
		if (spawnDestrachan) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmdestrachan"), EntityDestrachan.class, MODID + ".dmdestrachan", destrachanID, instance, 80, 3, true, 0xc99918, 0x161616);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmsonicboom"), EntityEyeRay.class, MODID + "dmsonicboom", sonicBoomID, instance, 80, 1, true);
		}
		// CAVE FISHER
		if (spawnCaveFisher) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmcavefisher"), EntityCaveFisher.class, MODID + ".dmcavefisher", caveFisherID, instance, 80, 3, true, 0xf6e7cc, 0x29a0b2);
		}
		// HELL HOUND
		if (spawnHellHound) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmnetherhound"), EntityNetherHound.class, MODID + ".dmnetherhound", netherHoundID, instance, 80, 3, true, 0xf9390d, 0x775532);
		}
		// RUST MONSTER
		if (spawnRustMonster) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmrustmonster"), EntityRustMonster.class, MODID + ".dmrustmonster", rustMonsterID, instance, 80, 3, true, 0x643200, 0x7C0F0F);
		}
		// UMBER HULK
		if (spawnUmberHulk) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmumberhulk"), EntityUmberHulk.class, MODID + ".dmumberhulk", umberHulkID, instance, 80, 3, true, 0x734A12, 0x8B795E);
		}
		// COCKATRICE
		if (spawnCockatrice) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmpetrified"), EntityPetrified.class, MODID + ".dmpetrified", petrifiedID, instance, 80, 3, true);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmcockatrice"), EntityCockatrice.class, MODID + ".dmcockatrice", cockatriceID, instance, 80, 3, true, 0x8acf92, 0x93ab96);
		}
		// SHRIEKER
		if (spawnShrieker) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmshrieker"), EntityShrieker.class, MODID + ".dmshrieker", shriekerID, instance, 80, 3, true, 0xBBA786, 0xE4DCB0);
		}
		// THOQQUA
		if (spawnThoqqua) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmthoqqua"), EntityThoqqua.class, MODID + ".dmthoqqua", thoqquaID, instance, 80, 3, true, 0xf1c33a, 0xb08968);
		}
		// VESCAVOR
		if (spawnVescavor) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmvescavor"), EntityVescavor.class, MODID + ".dmvescavor", vescavorID, instance, 80, 3, true, 0x170007, 0xa05155);
		}
		// MIND FLAYER
		if (spawnIllithid) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmillithid"), EntityIllithid.class, MODID + ".dmillithid", illithidID, instance, 80, 3, true, 0x9932CD, 0xf6f4ba);
		}
		// RAKSHASA
		if (spawnRakshasa) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmrakshasa"), EntityRakshasa.class, MODID + ".dmrakshasa", rakshasaID, instance, 80, 3, true, 0xa06c3e, 0xdec580);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmrakshasaImage"), EntityRakshasaImage.class, MODID + ".dmrakshasaImage", rakshasaImageID, instance, 80, 3, true);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmmagicmissile"), EntityMagicMissile.class, MODID + ".dmmagicmissle", magicMissileID, instance, 80, 1, true);
		}
		// FALLEN ANGEL
		if (spawnFallenAngel) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmfallenangel"), EntityFallenAngel.class, MODID + ".dmfallenangel", fallenAngelID, instance, 80, 3, true, 0x000000, 0x998800);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmlightball"), EntityLightball.class, MODID + ".dmlightball", lightballID, instance, 80, 1, true);
		}
		// LIZALFOS
		if (spawnLizalfos) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmlizalfos"), EntityLizalfos.class, MODID + ".dmlizalfos", lizalfosID, instance, 80, 3, true, 0x257a36, 0xe5d067);
		}
		// OUTER THING
		if (spawnOuterThing) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmeldermob"), EntityEldermob.class, MODID + ".dmeldermob", eldermobID, instance, 80, 3, true, 0x003300, 0x004422);
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmdarkball"), EntityEldermobBall.class, MODID + ".dmdarkball", darkballID, instance, 80, 1, true);
		}
		// BLADE TRAP
		if (spawnBladeTrap) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmbladetrap"), EntityBladeTrap.class, MODID + ".dmbladetrap", bladeTrapID, instance, 80, 3, true, 0x464646, 0xb70202);
		}
		// GHOST
		if (spawnGhost) {
			EntityRegistry.registerModEntity(new ResourceLocation(MODID, "dmghost"), EntityGhost.class, MODID + ".dmghost", ghostID, instance, 80, 3, true, 0xffffff, 0xe0e0e8);
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
		List<Biome> tmp1 = new ArrayList<>(), tmp2 = new ArrayList<>(), tmp3 = new ArrayList<>();
		for (Biome biome : biomes) {
			if (BiomeDictionary.hasType(biome, Type.NETHER)) {
				tmp1.add(biome);
			}
			else if (BiomeDictionary.hasType(biome, Type.END)) {
				tmp2.add(biome);
			}
			else {
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
		if (spawnGhoul && (ghoulP > 0)) EntityRegistry.addSpawn(EntityGhoul.class, ghoulP, ghoulMn, ghoulMx, EnumCreatureType.MONSTER, overworld);
		if (spawnBeholder && (beholderP > 0)) EntityRegistry.addSpawn(EntityAhriman.class, beholderP, beholderMn, beholderMx, EnumCreatureType.MONSTER, overworld);
		if (spawnRustMonster && (rustMonsterP > 0)) EntityRegistry.addSpawn(EntityRustMonster.class, rustMonsterP, rustMonsterMn, rustMonsterMx, EnumCreatureType.MONSTER, overworld);
		if (spawnShrieker && (shriekerP > 0)) EntityRegistry.addSpawn(EntityShrieker.class, shriekerP, shriekerMn, shriekerMx, EnumCreatureType.MONSTER, overworld);
		if (spawnUmberHulk && (umberHulkP > 0)) EntityRegistry.addSpawn(EntityUmberHulk.class, umberHulkP, umberHulkMn, umberHulkMx, EnumCreatureType.MONSTER, overworld);
		if (spawnHookHorror && (hookHorrorP > 0)) EntityRegistry.addSpawn(EntityHookHorror.class, hookHorrorP, hookHorrorMn, hookHorrorMx, EnumCreatureType.MONSTER, overworld);
		if (spawnTroll && (trollP > 0)) EntityRegistry.addSpawn(EntityTroll.class, trollP, trollMn, trollMx, EnumCreatureType.MONSTER, overworld);
		if (spawnCaveFisher && (caveFisherP > 0)) EntityRegistry.addSpawn(EntityCaveFisher.class, caveFisherP, caveFisherMn, caveFisherMx, EnumCreatureType.MONSTER, overworld);
		if (spawnDestrachan && (destrachanP > 0)) EntityRegistry.addSpawn(EntityDestrachan.class, destrachanP, destrachanMn, destrachanMx, EnumCreatureType.MONSTER, overworld);
		if (spawnIllithid && (illithidP > 0)) EntityRegistry.addSpawn(EntityIllithid.class, illithidP, illithidMn, illithidMx, EnumCreatureType.MONSTER, overworld);
		if (spawnHellHound) {
			if (hellHoundP > 0) EntityRegistry.addSpawn(EntityNetherHound.class, hellHoundP, hellHoundMn, hellHoundMx, EnumCreatureType.MONSTER, overworld);
			if (hellHoundNP > 0) EntityRegistry.addSpawn(EntityNetherHound.class, hellHoundNP, hellHoundNMn, hellHoundMx, EnumCreatureType.MONSTER, nether);
		}
		if (spawnRakshasa && (rakshasaP > 0)) EntityRegistry.addSpawn(EntityRakshasa.class, rakshasaP, rakshasaMn, rakshasaMx, EnumCreatureType.MONSTER, overworld);
		if (spawnLizalfos && (lizalfosP > 0)) EntityRegistry.addSpawn(EntityLizalfos.class, lizalfosP, lizalfosMn, lizalfosMx, EnumCreatureType.MONSTER, overworld);
		if (spawnCockatrice && (cockatriceP > 0)) EntityRegistry.addSpawn(EntityCockatrice.class, cockatriceP, cockatriceMn, cockatriceMx, EnumCreatureType.MONSTER, overworld);
		if (spawnManticore && (manticoreP > 0)) EntityRegistry.addSpawn(EntityManticore.class, manticoreP, manticoreMn, manticoreMx, EnumCreatureType.MONSTER, overworld);
		if (spawnThoqqua) {
			if (thoqquaP > 0) EntityRegistry.addSpawn(EntityThoqqua.class, thoqquaP, thoqquaMn, thoqquaMx, EnumCreatureType.MONSTER, overworld);
			if (thoqquaNP > 0) EntityRegistry.addSpawn(EntityThoqqua.class, thoqquaNP, thoqquaNMn, thoqquaNMx, EnumCreatureType.MONSTER, nether);
		}
		if (spawnVescavor && (vescavorP > 0)) EntityRegistry.addSpawn(EntityVescavor.class, vescavorP, vescavorMn, vescavorMx, EnumCreatureType.MONSTER, overworld);
		if (spawnRevenant && (revenantP > 0)) EntityRegistry.addSpawn(EntityRevenant.class, revenantP, revenantMn, revenantMx, EnumCreatureType.MONSTER, overworld);
		if (spawnVampire && (vampireP > 0)) EntityRegistry.addSpawn(EntityVampire.class, vampireP, vampireMn, vampireMx, EnumCreatureType.MONSTER, overworld);
		if (spawnFallenAngel && (fallenAngelP > 0)) {
			EntityRegistry.addSpawn(EntityFallenAngel.class, fallenAngelP, fallenAngelMn, fallenAngelMx, EnumCreatureType.MONSTER, overworld);
			EntityRegistry.addSpawn(EntityFallenAngel.class, fallenAngelP, fallenAngelMn, fallenAngelMx, EnumCreatureType.MONSTER, nether);
		}
		if (spawnOuterThing) {
			if (outerThingP > 0) EntityRegistry.addSpawn(EntityEldermob.class, outerThingP, outerThingMn, outerThingMx, EnumCreatureType.MONSTER, overworld);
			if (outerThingEP > 0) EntityRegistry.addSpawn(EntityEldermob.class, outerThingEP, outerThingEMn, outerThingEMx, EnumCreatureType.MONSTER, end);
		}
		if (spawnGhost && (ghostP > 0)) EntityRegistry.addSpawn(EntityGhost.class, ghostP, ghostMn, ghostMx, EnumCreatureType.MONSTER, overworld);
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
		if (spawnGhoul) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmghoul"), 1);
		if (spawnBeholder) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmbeholder"), 1);
		if (spawnTroll) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmtroll"), 1);
		if (spawnManticore) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmmanticore"), 1);
		if (spawnRevenant) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmrevenant"), 1);
		if (spawnVampire) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmvampire"), 1);
		if (spawnHookHorror) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmhookhorror"), 1);
		if (spawnDestrachan) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmdestrachan"), 1);
		if (spawnCaveFisher) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmcavefisher"), 1);
		if (spawnHellHound) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmhellhound"), 1);
		if (spawnRustMonster) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmrustmonster"), 1);
		if (spawnUmberHulk) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmumberhulk"), 1);
		if (spawnCockatrice) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmcockatrice"), 1);
		if (spawnThoqqua) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmthoqqua"), 1);
		if (spawnVescavor) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmvescavor"), 1);
		if (spawnIllithid) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmillithid"), 1);
		if (spawnRakshasa) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmrakshasa"), 1);
		if (spawnLizalfos) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmlizalfos"), 1);
		if (spawnGhost) DungeonHooks.addDungeonMob(new ResourceLocation(MODID, "dmghost"), 1);
	}
}
