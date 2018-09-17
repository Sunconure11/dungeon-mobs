package com.gw.dm.util;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityRustMonster;
import com.gw.dm.entity.EntityShrieker;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigHandler {

	public static File mainConfig;
	public static File configDir;

	public static boolean devMode;

	public static boolean spawnNaturally;
	public static boolean addToVanillaDungeons;
	public static boolean addToDoomlikeDungeons;

	public static boolean spawnRustMonster;
	public static boolean spawnGhoul;
	public static boolean spawnShrieker;
	public static boolean spawnHookHorror;
	public static boolean spawnUmberHulk;
	public static boolean spawnBeholder;
	public static boolean spawnCaveFisher;
	public static boolean spawnCockatrice;
	public static boolean spawnDestrachan;
	public static boolean spawnIllithid;
	public static boolean spawnLizalfos;
	public static boolean spawnManticore;
	public static boolean spawnHellHound;
	public static boolean spawnRakshasa;
	public static boolean spawnThoqqua;
	public static boolean spawnTroll;
	public static boolean spawnVescavor;
	public static boolean spawnRevenant;
	public static boolean spawnVampire;
	public static boolean spawnFallenAngel;
	public static boolean spawnOuterThing;
	public static boolean spawnBeamos;
	public static boolean spawnBladeTrap;

	public static boolean replaceRMFoods;
	public static List<String> rustMonFoodsList;

	public static List<String> shriekerMobs;
	
	/*----------------------------------------------------------*
	 *                VARIABLE FOR SPAWN CONTROL                *
	 *----------------------------------------------------------*/
	
	// For the following these variables, the name goes starts with the 
	// mob name, and is follow by one of the following:
	// Ig (ignore basic dungeon mobs spawn rules), P (chance of spawn), 
	// Mn (minimum number to spawn), or Mx (maximum number to spawn).
	
	
	public static boolean rustMonsterIg;
	public static int rustMonsterP;
	public static int rustMonsterMn;
	public static int rustMonsterMx; 
	
	public static boolean ghoulIg;
	public static int ghoulP;
	public static int ghoulMn;
	public static int ghoulMx;
	
	public static boolean shriekerIg;
	public static int shriekerP;
	public static int shriekerMn;
	public static int shriekerMx;
	
	public static boolean hookHorrorIg;
	public static int hookHorrorP;
	public static int hookHorrorMn;
	public static int hookHorrorMx;
	
	public static boolean umberHulkIg;
	public static int umberHulkP;
	public static int umberHulkMn;
	public static int umberHulkMx;
	
	public static boolean beholderIg;
	public static int beholderP;
	public static int beholderMn;
	public static int beholderMx;
	
	public static boolean caveFisherIg;
	public static int caveFisherP;
	public static int caveFisherMn;
	public static int caveFisherMx;
	
	public static boolean cockatriceIg;
	public static int cockatriceP;
	public static int cockatriceMn;
	public static int cockatriceMx;
	
	public static boolean destrachanIg;
	public static int destrachanP;
	public static int destrachanMn;
	public static int destrachanMx;
	
	public static boolean illithidIg;
	public static int illithidP;
	public static int illithidMn;
	public static int illithidMx;
	
	public static boolean lizalfosIg;
	public static int lizalfosP;
	public static int lizalfosMn;
	public static int lizalfosMx;
	
	public static boolean manticoreIg;
	public static int manticoreP;
	public static int manticoreMn;
	public static int manticoreMx;
	
	public static boolean hellHoundIg;
	public static int hellHoundP;
	public static int hellHoundMn;
	public static int hellHoundMx;
	
	public static boolean rakshasaIg;
	public static int rakshasaP;
	public static int rakshasaMn;
	public static int rakshasaMx;
	
	public static boolean thoqquaIg;
	public static int thoqquaP;
	public static int thoqquaMn;
	public static int thoqquaMx;
	
	public static boolean trollIg;
	public static int trollP;
	public static int trollMn;
	public static int trollMx;
	
	public static boolean vescavorIg;
	public static int vescavorP;
	public static int vescavorMn;
	public static int vescavorMx;
	
	public static boolean revenantIg;
	public static int revenantP;
	public static int revenantMn;
	public static int revenantMx;
	
	public static boolean vampireIg;
	public static int vampireP;
	public static int vampireMn;
	public static int vampireMx;
	
	public static boolean fallenAngelIg;
	public static int fallenAngelP;
	public static int fallenAngelMn;
	public static int fallenAngelMx;
	
	public static boolean outerThingIg;
	public static int outerThingP;
	public static int outerThingMn;
	public static int outerThingMx;

	
	/*----------------------------------------------------------*
	 *                     WORKING CODE                         *
	 *----------------------------------------------------------*/


	public static void init() {
		File file = new File(ConfigHandler.configDir.toString()
				+ File.separator + DungeonMobs.MODID + ".cfg");
		Configuration config = new Configuration(file);
		config.load();

		boolean client = FMLCommonHandler.instance().getSide() == Side.CLIENT;

		// Dev and Debugging
		config.addCustomCategoryComment("DevNDebug", "Setting for debugging a development");
		devMode = config.get("DevNDebug", "DevMode", false, "Allow WIP mobs / features to appear; "
				+ "these mobs may not have AI or even textures and done.  \r\n "
				+ "Keep this false (unless you're actually trying to code new mobs).").getBoolean();

		// General Configuration
		config.addCustomCategoryComment("General", "General setting effecting all mobs");
		spawnNaturally = config.get("General", "SpawnNaturally", true,
				"Determines if dungeon mobs can spawn naturally in the world (\"dark spawn\")").getBoolean();
		addToVanillaDungeons = config.get("General", "AddToVanillaDungeons", true,
				"Determines dungeons mobs will be added to spawners in vanilla dungeons").getBoolean();
		//addToDoomlikeDungeons = config.get("General", "AddToDoomlikeDungeons", true,
		//		"Determines dungeons mobs will be added to spawners in vanilla dungeons").getBoolean();
		addToDoomlikeDungeons = false; // FIXME: Make adding these mobs work

		// Mob Existence
		config.addCustomCategoryComment("Mobs", "Which mobs exist in the word \r\n "
				+ "(if false it does not exist and can never spawn)");
		spawnRustMonster = config.get("Mobs", "RustMonster", true).getBoolean();
		spawnGhoul = config.get("Mobs", "Ghoul", true).getBoolean();
		spawnShrieker = config.get("Mobs", "Shrieker", true).getBoolean();
		spawnHookHorror = config.get("Mobs", "HookHorror", true).getBoolean();
		spawnUmberHulk = config.get("Mobs", "UmberHulk", true).getBoolean();
		spawnBeholder = config.get("Mobs", "Beholder", true).getBoolean();

		spawnCaveFisher = (config.get("Mobs", "CaveFisher", true).getBoolean() && client);

		spawnCockatrice = config.get("Mobs", "Cockatrice", true).getBoolean();
		spawnDestrachan = config.get("Mobs", "Destrachan", true).getBoolean();
		spawnIllithid = config.get("Mobs", "Illithid", true).getBoolean();
		spawnLizalfos = config.get("Mobs", "Lizalfos", true).getBoolean();
		spawnManticore = config.get("Mobs", "Manticore", true).getBoolean();
		spawnHellHound = config.get("Mobs", "HellHound", true).getBoolean();
		spawnRakshasa = config.get("Mobs", "Rakshasa", true).getBoolean();
		spawnThoqqua = config.get("Mobs", "Thoqqua", true).getBoolean();
		spawnTroll = config.get("Mobs", "Troll", true).getBoolean();
		spawnVescavor = config.get("Mobs", "Vescavor", true).getBoolean();
		spawnRevenant = config.get("Mobs", "Revenant", true).getBoolean();
		spawnVampire = config.get("Mobs", "Vampire", true).getBoolean();
		spawnFallenAngel = config.get("Mobs", "FallenAngel", true).getBoolean();
		spawnOuterThing = config.get("Mobs", "OuterThing", true).getBoolean();
		spawnBladeTrap = config.get("Mobs", "BladeTrap", true).getBoolean();

		// Rust Monster Foods
		config.addCustomCategoryComment("Rust Monster Foods", "Items eaten / wanted by "
				+ "rust monsters)");
		replaceRMFoods = config.get("Rust Monster Foods", "Replace", false,
				"If true the food list for rust monster will be replaced, "
						+ "\r\notherwise it will be kept and added to.").getBoolean();
		rustMonFoodsList = new ArrayList<String>();
		String[] array = config.get("Rust Monster Foods",
				"FoodList",
				new String[]{},
				"Items the rust monsters want to eat (put metal here); \r\n"
						+ "format \"modid:reistry_name\"")
				.getStringList();
		rustMonFoodsList.addAll(Arrays.asList(array));

		// Shrieker Summons
		config.addCustomCategoryComment("Shrieker Summons", "Mobs that can be summoned by shriekers");
		shriekerMobs = new ArrayList<>();
		array = config.get("Shrieker Summons", "ShriekerSummons", new String[]{},
				"Mobs the that may be spawned when shriekers shriek (will be added to list); \r\n"
						+ "format \"modid:resource_location\"")
				.getStringList();
		shriekerMobs.addAll(Arrays.asList(array));
		if (!shriekerMobs.isEmpty()) {
			EntityShrieker.appendToSummonList(shriekerMobs);
		}

		// Save It!!!
		config.save();
		mobDat();
	}


	/**
	 * This looks for the mods config directory, and attempts to
	 * create it if it does not exist.  It will them set this as
	 * the config directory and return it as a File.
	 *
	 * @param fd
	 * @return the config directory / folder
	 */
	public static File findConfigDir(File fd) {
		File out = new File(fd.toString() + File.separator + DungeonMobs.MODID);
		if (!out.exists()) out.mkdir();
		Logger logger = Logger.getGlobal();

		if (!out.exists()) {
			logger.log(Level.SEVERE, "[DUNGEON MOBS] ERROR: Could not create config directory");
		} else if (!out.isDirectory()) {
			logger.log(Level.SEVERE, "[DUNGEON MOBS] ERROR: Config directory is not a directory!");
		} else {
			configDir = out;
		}
		return out;
	}


	public static void mobSpecialSetup() {
		if (!rustMonFoodsList.isEmpty()) {
			if (replaceRMFoods) {
				EntityRustMonster.setFoods(rustMonFoodsList);
			} else {
				EntityRustMonster.appendToFoods(rustMonFoodsList);
			}
		}
	}
	
	
	public static void mobDat() {
		File file = new File(ConfigHandler.configDir.toString()
				+ File.separator + "spawn_rules.cfg");
		Configuration config = new Configuration(file);
		config.load();	
		
		config.addCustomCategoryComment("Rust Monster", "Spawn configurations for rust monster");		
		rustMonsterIg = config.get("Rust Monster", "RMSpawnAnywhere", false).getBoolean();;
		rustMonsterP  = config.get("Rust Monster", "RMSpawnChance", 8).getInt();
		rustMonsterMn = config.get("Rust Monster", "RMSpawnMin", 1).getInt();
		rustMonsterMx = config.get("Rust Monster", "RMSpawnMax", 4).getInt(); 	
		
		config.addCustomCategoryComment("Ghoul", "Spawn configurations for rust monster");		
		ghoulIg = config.get("Ghoul", "GhoulSpawnAnywhere", false).getBoolean();;
		ghoulP  = config.get("Ghoul", "GhoulSpawnChance", 5).getInt();
		ghoulMn = config.get("Ghoul", "GhoulSpawnMin", 2).getInt();
		ghoulMx = config.get("Ghoul", "GhoulSpawnMax", 4).getInt();  	
		
		config.addCustomCategoryComment("Shrieker", "Spawn configurations for rust monster");		
		shriekerIg = config.get("Shrieker", "ShriekerSpawnAnywhere", false).getBoolean();;
		shriekerP  = config.get("Shrieker", "ShriekerSpawnChance", 5).getInt();
		shriekerMn = config.get("Shrieker", "ShriekerSpawnMin", 2).getInt();
		shriekerMx = config.get("Shrieker", "ShriekerSpawnMax", 4).getInt();  	
		
		config.addCustomCategoryComment("Hook Horror", "Spawn configurations for rust monster");		
		hookHorrorIg = config.get("Hook Horror", "HHSpawnAnywhere", false).getBoolean();;
		hookHorrorP  = config.get("Hook Horror", "HHSpawnChance", 5).getInt();
		hookHorrorMn = config.get("Hook Horror", "HHSpawnMin", 2).getInt();
		hookHorrorMx = config.get("Hook Horror", "HHSpawnMax", 4).getInt(); 
		
		/*
		public static boolean umberHulkIg;
		public static boolean umberHulkP;
		public static boolean umberHulkMn;
		public static boolean umberHulkMx;
		
		public static boolean beholderIg;
		public static boolean beholderP;
		public static boolean beholderMn;
		public static boolean beholderMx;
		
		public static boolean caveFisherIg;
		public static boolean caveFisherP;
		public static boolean caveFisherMn;
		public static boolean caveFisherMx;
		
		public static boolean cockatriceIg;
		public static boolean cockatriceP;
		public static boolean cockatriceMn;
		public static boolean cockatriceMx;
		
		public static boolean destrachanIg;
		public static boolean destrachanP;
		public static boolean destrachanMn;
		public static boolean destrachanMx;
		
		public static boolean illithidIg;
		public static boolean illithidP;
		public static boolean illithidMn;
		public static boolean illithidMx;
		
		public static boolean lizalfosIg;
		public static boolean lizalfosP;
		public static boolean lizalfosMn;
		public static boolean lizalfosMx;
		
		public static boolean manticoreIg;
		public static boolean manticoreP;
		public static boolean manticoreMn;
		public static boolean manticoreMx;
		
		public static boolean hellHoundIg;
		public static boolean hellHoundP;
		public static boolean hellHoundMn;
		public static boolean hellHoundMx;
		
		public static boolean rakshasaIg;
		public static boolean rakshasaP;
		public static boolean rakshasaMn;
		public static boolean rakshasaMx;
		
		public static boolean thoqquaIg;
		public static boolean thoqquaP;
		public static boolean thoqquaMn;
		public static boolean thoqquaMx;
		
		public static boolean trollIg;
		public static boolean trollP;
		public static boolean trollMn;
		public static boolean trollMx;
		
		public static boolean vescavorIg;
		public static boolean vescavorP;
		public static boolean vescavorMn;
		public static boolean vescavorMx;
		
		public static boolean revenantIg;
		public static boolean revenantP;
		public static boolean revenantMn;
		public static boolean revenantMx;
		
		public static boolean vampireIg;
		public static boolean vampireP;
		public static boolean vampireMn;
		public static boolean vampireMx;
		
		public static boolean fallenAngelIg;
		public static boolean fallenAngelP;
		public static boolean fallenAngelMn;
		public static boolean fallenAngelMx;
		
		public static boolean outerThingIg;
		public static boolean outerThingP;
		public static boolean outerThingMn;
		*/

		// Save It!!!
		config.save();
	}

}
