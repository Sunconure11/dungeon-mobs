package com.gw.dm.util;

import com.gw.dm.DungeonMobs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class AudioHandler {

	public static SoundEvent entityGhoulAmbient;
	public static SoundEvent entityGhoulHurt;
	public static SoundEvent entityGhoulDeath;

	public static SoundEvent entityAhrimanAmbient;

	public static SoundEvent entityTrollAmbient;
	public static SoundEvent entityTrollHurt;
	public static SoundEvent entityTrollDeath;
	public static SoundEvent dmbts;

	public static SoundEvent entityManticoreA1;
	public static SoundEvent entityManticoreA2;
	public static SoundEvent entityManticoreHurt;
	public static SoundEvent entityManticoreDeath;
	public static SoundEvent entityManticoreAttack;

	public static SoundEvent entityHookHorrorA1;
	public static SoundEvent entityHookHorrorA2;
	public static SoundEvent entityHookHorrorHurt;

	public static SoundEvent entityDestrachanAmbient;
	public static SoundEvent entityDestrachanHurt;
	public static SoundEvent entityDestrachanDeath;
	public static SoundEvent entityDestrachanStep;

	public static SoundEvent entityCaveFisherAmbient;
	public static SoundEvent entityCaveFisherHurt;
	public static SoundEvent entityCaveFisherDeath;

	public static SoundEvent entityNetherHoundAmbient;
	public static SoundEvent entityNetherHoundHurt;
	public static SoundEvent entityNetherHoundFire;

	public static SoundEvent entityRustMonsterAmbient;
	public static SoundEvent entityRustMonsterHurt;
	public static SoundEvent entityRustMonsterDeath;

	public static SoundEvent entityUmberHulkAmbient;
	public static SoundEvent entityUmberHulkHurt;

	public static SoundEvent entityCockatriceAmbient;
	public static SoundEvent entityCockatriceHurt;
	public static SoundEvent entityCockatriceDeath;
	public static SoundEvent entityCockatriceStone;

	public static SoundEvent entityShrieker;

	public static SoundEvent entityVescavorAmbient1q;
	public static SoundEvent entityVescavorAmbient2q;
	public static SoundEvent entityVescavorHurtq;
	public static SoundEvent entityVescavorDeathq;
	public static SoundEvent entityVescavorAmbient1l;
	public static SoundEvent entityVescavorAmbient2l;
	public static SoundEvent entityVescavorHurtl;
	public static SoundEvent entityVescavorDeathl;
	public static SoundEvent entityVescavorAmbient1;
	public static SoundEvent entityVescavorAmbient2;
	public static SoundEvent entityVescavorHurt;
	public static SoundEvent entityVescavorDeath;

	public static SoundEvent entityIllithidAmbient;
	public static SoundEvent entityIllithidPower;
	public static SoundEvent entityIllithidDeath;

	public static SoundEvent entityRakshasaAmbient;
	public static SoundEvent entityRakshasaHurt;
	public static SoundEvent entityRakshasael;

	public static SoundEvent entityFallenSing1;
	public static SoundEvent entityFallenSing2;
	public static SoundEvent entityFallenSing3;
	public static SoundEvent entityFallenSing4;

	public static SoundEvent entityLizalfosAmbient1;
	public static SoundEvent entityLizalfosAmbient2;
	public static SoundEvent entityLizalfosHurt;
	public static SoundEvent entityLizalfosDeath;
	public static SoundEvent entityLizalfosBlock;

	public static SoundEvent entityBladeTrapAmbient;
	public static SoundEvent entityBladeTrapHurt;
	public static SoundEvent entityBladeTrapDeath;
	public static SoundEvent entityBladeTrapBlade;

	public static SoundEvent entityEldermobA1;
	public static SoundEvent entityEldermobA2;
	public static SoundEvent entityEldermobA3;
	
	public static SoundEvent entityGhostAmbient1;
	public static SoundEvent entityGhostAmbient2;
	public static SoundEvent entityGhostHurt;
	public static SoundEvent entityGhostDeath;


	public static void registerSounds() {
		dmbts = registerSound("bt_s");

		entityGhoulAmbient = registerSound("g_l");
		entityGhoulHurt = registerSound("g_h");
		entityGhoulDeath = registerSound("g_d");

		entityAhrimanAmbient = registerSound("1_l");

		entityTrollAmbient = registerSound("t_l");
		entityTrollHurt = registerSound("t_h");
		entityTrollDeath = registerSound("t_d");

		entityManticoreA1 = registerSound("ma_l");
		entityManticoreA2 = registerSound("ma_l2");
		entityManticoreHurt = registerSound("ma_h");
		entityManticoreDeath = registerSound("ma_d");
		entityManticoreAttack = registerSound("ma_s");

		entityHookHorrorA1 = registerSound("hh_l");
		entityHookHorrorA2 = registerSound("hh_l2");
		entityHookHorrorHurt = registerSound("hh_h");

		entityDestrachanAmbient = registerSound("d_l");
		entityDestrachanHurt = registerSound("d_h");
		entityDestrachanDeath = registerSound("d_d");

		entityCaveFisherAmbient = registerSound("cf_l");
		entityCaveFisherHurt = registerSound("cf_h");
		entityCaveFisherDeath = registerSound("cf_d");

		entityNetherHoundAmbient = registerSound("nh_l");
		entityNetherHoundHurt = registerSound("nh_h");
		entityNetherHoundFire = registerSound("nh_i");

		entityRustMonsterAmbient = registerSound("rm_l");
		entityRustMonsterHurt = registerSound("rm_h");
		entityRustMonsterDeath = registerSound("rm_d");

		entityUmberHulkAmbient = registerSound("uh_l");
		entityUmberHulkHurt = registerSound("uh_h");

		entityCockatriceAmbient = registerSound("co_l");
		entityCockatriceHurt = registerSound("co_h");
		entityCockatriceDeath = registerSound("co_d");
		entityCockatriceStone = registerSound("co_s");

		entityShrieker = registerSound("s_s");
		
		entityVescavorAmbient1l = registerSound("v_lo");
		entityVescavorAmbient2l = registerSound("v_lo2");			
		entityVescavorHurtl = registerSound("v_ho");
		entityVescavorDeathl = registerSound("v_do");			
		entityVescavorAmbient1q = registerSound("v_l");
		entityVescavorAmbient1q = registerSound("v_l2");
		entityVescavorHurtq = registerSound("v_h");
		entityVescavorDeathq = registerSound("v_d");		
		if(ConfigHandler.loudVescavor) {
			entityVescavorAmbient1 = entityVescavorAmbient2l; 
			entityVescavorAmbient2 = entityVescavorAmbient2l;			
			entityVescavorHurt = entityVescavorHurtl;
			entityVescavorDeath = entityVescavorDeathl;			
		} else {
			entityVescavorAmbient1 = entityVescavorAmbient1q; 
			entityVescavorAmbient2 = entityVescavorAmbient2q;			
			entityVescavorHurt = entityVescavorHurtq;
			entityVescavorDeath = entityVescavorDeathq;
		}

		entityIllithidAmbient = registerSound("i_l");
		entityIllithidPower = registerSound("i_p");
		entityIllithidDeath = registerSound("i_d");

		entityRakshasaAmbient = registerSound("ra_l");
		entityRakshasaHurt = registerSound("ra_h");
		entityRakshasael = registerSound("ra_el");

		entityFallenSing1 = registerSound("fa_l1");
		entityFallenSing2 = registerSound("fa_l2");
		entityFallenSing3 = registerSound("fa_l3");
		entityFallenSing4 = registerSound("fa_l4");

		entityLizalfosAmbient1 = registerSound("li_l");
		entityLizalfosAmbient2 = registerSound("li_l2");
		entityLizalfosHurt = registerSound("li_h");
		entityLizalfosDeath = registerSound("li_d");
		entityLizalfosBlock = registerSound("li_a");

		entityBladeTrapAmbient = registerSound("bl_l");
		entityBladeTrapHurt = registerSound("bl_sw");
		entityBladeTrapDeath = registerSound("bl_d");
		entityBladeTrapBlade = registerSound("bl_b");

		entityEldermobA1 = registerSound("em_l1");
		entityEldermobA2 = registerSound("em_l2");
		entityEldermobA3 = registerSound("em_l3");
		
		entityGhostAmbient1 = registerSound("g2_l");
		entityGhostAmbient2 = registerSound("g2_l2");
		entityGhostHurt = registerSound("g2_h");
		entityGhostDeath = registerSound("g2_d");
	}


	private static SoundEvent registerSound(String name) {
		SoundEvent evt = new SoundEvent(new ResourceLocation(DungeonMobs.MODID, name));
		evt.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(evt);
		return evt;
	}

}
