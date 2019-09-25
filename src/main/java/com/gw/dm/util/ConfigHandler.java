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
	public static boolean spawnGhost;

	public static boolean lightBallExplode;
	public static boolean loudVescavor;
	public static boolean hardcoreVampire;
	public static boolean hardcoreThoqqua;
	public static boolean fireClouds;
	
	public static boolean replaceRMFoods;
	public static List<String> rustMonFoodsList;
	public static List<String> shriekerMobs;
	
	/*-------------------------------------------------------spawnGhoul---*
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
	public static int hellHoundNP;
	public static int hellHoundNMn;
	public static int hellHoundNMx;
	
	public static boolean rakshasaIg;
	public static int rakshasaP;
	public static int rakshasaMn;
	public static int rakshasaMx;
	
	public static boolean thoqquaIg;
	public static int thoqquaP;
	public static int thoqquaMn;
	public static int thoqquaMx;
	public static int thoqquaNP;
	public static int thoqquaNMn;
	public static int thoqquaNMx;
	
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
	
	public static boolean ghostIg;
	public static int ghostP;
	public static int ghostMn;
	public static int ghostMx;
	
	public static boolean fallenAngelIg;
	public static int fallenAngelP;
	public static int fallenAngelMn;
	public static int fallenAngelMx;
	public static int fallenAngelNP;
	public static int fallenAngelNMn;
	public static int fallenAngelNMx;
	public static boolean outerThingIg;
	public static boolean outerThingEI;
	public static int outerThingP;
	public static int outerThingMn;
	public static int outerThingMx;
	public static int outerThingEP;
	public static int outerThingEMn;
	public static int outerThingEMx;

	
	public static float healthx;
	public static float damagex;
	public static float damageplus; 
	
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
		addToDoomlikeDungeons = config.get("General", "AddToDoomlikeDungeons", true,
				"Determines dungeons mobs will be added to spawners in doomlike dungeons").getBoolean()
				&& (net.minecraftforge.fml.common.Loader.isModLoaded("DLDungeonsJBG")
						// Yes, I botch some of this and Doomlike Dungeons and have to
						// accomodate for it here... :(
						|| net.minecraftforge.fml.common.Loader.isModLoaded("dldungeonsjbg")
						|| net.minecraftforge.fml.common.Loader.isModLoaded("dldungeonjbg")
						|| net.minecraftforge.fml.common.Loader.isModLoaded("DLDungeonJBG"));
		//addToDoomlikeDungeons = false;

		// Mob Existence
		config.addCustomCategoryComment("Mobs", "Which mobs exist in the word \r\n "
				+ "(if false it does not exist and can never spawn)");
		spawnRustMonster = config.get("Mobs", "RustMonster", true).getBoolean();
		spawnGhoul = config.get("Mobs", "Ghoul", true).getBoolean();
		spawnGhost = config.get("Mobs", "Ghost", true).getBoolean();
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
		
		// Special mob configurations
		config.addCustomCategoryComment("Special Mob Traits", 
				"Some other special characteristics of some mobs");
		loudVescavor = config.get("Special Mob Traits", "LoudVescavor", false, 
				"True: Use old, LOUD vescavor sound\nFalse: Use new, quieter vescavor sound")
				.getBoolean();
		hardcoreVampire = config.get("Special Mob Traits", "HardcoreVampire", false, 
				"If true vampires will drain levels from the player.")
				.getBoolean();
		hardcoreThoqqua = config.get("Special Mob Traits", "HardcoreThoqqua", true, 
				"If true thoqquas will turn stone into actual lava (temporarily).")
				.getBoolean();
		fireClouds = config.get("Special Mob Traits", "Outerthing Fireclouds", true, 
				"If false outerthings will not use the fire cloud attack.")
				.getBoolean();
		
		// Power-Up
		config.addCustomCategoryComment("Power Boost", 
				"Incase you don't think these mobs are tough enough for your "
				+ "OP modpack / gear");		
		healthx = config.getFloat("Health Multiplier", "Power Boost", 1.0f, 1.0f, 1000f, 
					"Multiply every mob's base health by this");
		damagex = config.getFloat("Damage Multiplier", "Power Boost", 1.0f, 1.0f, 1000f, 
				"Multiply every mob's base damage by this");;
		damageplus  = config.getFloat("Damage Additive", "Power Boost", 0.0f, 0.0f, 1000f, 
				"Add this to every mob's base damage "
				+ "(usually less extreme than multiplying"); 
		
		

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
		
		config.addCustomCategoryComment("Ghoul", "Spawn configurations for ghoul");		
		ghoulIg = config.get("Ghoul", "GhoulSpawnAnywhere", false).getBoolean();;
		ghoulP  = config.get("Ghoul", "GhoulSpawnChance", 5).getInt();
		ghoulMn = config.get("Ghoul", "GhoulSpawnMin", 2).getInt();
		ghoulMx = config.get("Ghoul", "GhoulSpawnMax", 4).getInt();	
		
		config.addCustomCategoryComment("Ghost", "Spawn configurations for ghostl");		
		ghostIg = config.get("Ghost", "GhostSpawnAnywhere", false).getBoolean();;
		ghostP  = config.get("Ghost", "GhostSpawnChance", 2).getInt();
		ghostMn = config.get("Ghost", "GhostSpawnMin", 1).getInt();
		ghostMx = config.get("Ghost", "GhostSpawnMax", 3).getInt();  	
		
		config.addCustomCategoryComment("Shrieker", "Spawn configurations for shrieker");		
		shriekerIg = config.get("Shrieker", "ShriekerSpawnAnywhere", false).getBoolean();;
		shriekerP  = config.get("Shrieker", "ShriekerSpawnChance", 5).getInt();
		shriekerMn = config.get("Shrieker", "ShriekerSpawnMin", 2).getInt();
		shriekerMx = config.get("Shrieker", "ShriekerSpawnMax", 4).getInt();  	
		
		config.addCustomCategoryComment("Hook Horror", "Spawn configurations for hook horror");		
		hookHorrorIg = config.get("Hook Horror", "HHSpawnAnywhere", false).getBoolean();;
		hookHorrorP  = config.get("Hook Horror", "HHSpawnChance", 4).getInt();
		hookHorrorMn = config.get("Hook Horror", "HHSpawnMin", 1).getInt();
		hookHorrorMx = config.get("Hook Horror", "HHSpawnMax", 4).getInt();  	
		
		config.addCustomCategoryComment("Umber Hulk", "Spawn configurations for umber hulk");		
		umberHulkIg = config.get("Umber Hulk", "UHSpawnAnywhere", false).getBoolean();;
		umberHulkP  = config.get("Umber Hulk", "UHSpawnChance", 2).getInt();
		umberHulkMn = config.get("Umber Hulk", "UHSpawnMin", 1).getInt();
		umberHulkMx = config.get("Umber Hulk", "UHSpawnMax", 1).getInt();  	
		
		config.addCustomCategoryComment("Beholder", "Spawn configurations for beholder");		
		beholderIg = config.get("Beholder", "BeholderSpawnAnywhere", false).getBoolean();;
		beholderP  = config.get("Beholder", "BeholderSpawnChance", 2).getInt();
		beholderMn = config.get("Beholder", "BeholderSpawnMin", 1).getInt();
		beholderMx = config.get("Beholder", "BeholderSpawnMax", 2).getInt();  	
		
		config.addCustomCategoryComment("Cave Fisher", "Spawn configurations for cave fisher");		
		caveFisherIg = config.get("Cave Fisher", "CFSpawnAnywhere", false).getBoolean();;
		caveFisherP  = config.get("Cave Fisher", "CFSpawnChance", 4).getInt();
		caveFisherMn = config.get("Cave Fisher", "CFSpawnMin", 1).getInt();
		caveFisherMx = config.get("Cave Fisher", "CFSpawnMax", 2).getInt();  	
		
		config.addCustomCategoryComment("Cockatrice", "Spawn configurations for cockatrice");		
		cockatriceIg = config.get("Cockatrice", "CockatriceSpawnAnywhere", false).getBoolean();;
		cockatriceP  = config.get("Cockatrice", "CockatriceSpawnChance", 5).getInt();
		cockatriceMn = config.get("Cockatrice", "CockatriceSpawnMin", 1).getInt();
		cockatriceMx = config.get("Cockatrice", "CockatriceSpawnMax", 3).getInt();  	
		
		config.addCustomCategoryComment("Destrachan", "Spawn configurations for destrachanr");		
		destrachanIg = config.get("Destrachan", "DestrachanSpawnAnywhere", false).getBoolean();;
		destrachanP  = config.get("Destrachan", "DestrachanSpawnChance", 5).getInt();
		destrachanMn = config.get("Destrachan", "DestrachanSpawnMin", 1).getInt();
		destrachanMx = config.get("Destrachan", "DestrachanSpawnMax", 3).getInt();  	
		
		config.addCustomCategoryComment("Illithid", "Spawn configurations for illithid");		
		illithidIg = config.get("Illithid", "IllithidSpawnAnywhere", false).getBoolean();;
		illithidP  = config.get("Illithid", "IllithidSpawnChance", 3).getInt();
		illithidMn = config.get("Illithid", "IllithidSpawnMin", 1).getInt();
		illithidMx = config.get("Illithid", "IllithidSpawnMax", 1).getInt();  	
		
		config.addCustomCategoryComment("Manticore", "Spawn configurations for manticore");		
		manticoreIg = config.get("Manticore", "ManticoreSpawnAnywhere", false).getBoolean();;
		manticoreP  = config.get("Manticore", "ManticoreSpawnChance", 5).getInt();
		manticoreMn = config.get("Manticore", "ManticoreSpawnMin", 2).getInt();
		manticoreMx = config.get("Manticore", "ManticoreSpawnMax", 4).getInt(); 
		
		config.addCustomCategoryComment("Hell Hound", "Spawn configurations for hell hound");		
		hellHoundIg = config.get("Hell Hound", "HHSpawnAnywhere", false).getBoolean();;
		hellHoundP  = config.get("Hell Hound", "HHSpawnChance", 4).getInt();
		hellHoundMn = config.get("Hell Hound", "HHSpawnMin", 1).getInt();
		hellHoundMx = config.get("Hell Hound", "HHSpawnMax", 2).getInt();
		hellHoundNP  = config.get("Hell Hound", "HHNetherChance", 10).getInt();
		hellHoundNMn = config.get("Hell Hound", "HHNetherMin", 4).getInt();
		hellHoundNMx = config.get("Hell Hound", "HHNetherMax", 4).getInt(); 
		
		config.addCustomCategoryComment("Rakshasa", "Spawn configurations for rakshasa");		
		rakshasaIg = config.get("Rakshasa", "RakshasaSpawnAnywhere", false).getBoolean();;
		rakshasaP  = config.get("Rakshasa", "RakshasaSpawnChance", 3).getInt();
		rakshasaMn = config.get("Rakshasa", "RakshasaSpawnMin", 1).getInt();
		rakshasaMx = config.get("Rakshasa", "RakshasaSpawnMax", 1).getInt(); 
		
		config.addCustomCategoryComment("Manticore", "Spawn configurations for manticore");		
		manticoreIg = config.get("Manticore", "ManticoreSpawnAnywhere", false).getBoolean();;
		manticoreP  = config.get("Manticore", "ManticoreSpawnChance", 4).getInt();
		manticoreMn = config.get("Manticore", "ManticoreSpawnMin", 1).getInt();
		manticoreMx = config.get("Manticore", "ManticoreSpawnMax", 2).getInt(); 
		
		config.addCustomCategoryComment("Thoqqua", "Spawn configurations for thoqqua");		
		thoqquaIg = config.get("Thoqqua", "ThoqquaSpawnAnywhere", false).getBoolean();
		thoqquaP  = config.get("Thoqqua", "ThoqquaSpawnChance", 3).getInt();
		thoqquaMn = config.get("Thoqqua", "ThoqquaSpawnMin", 1).getInt();
		thoqquaMx = config.get("Thoqqua", "ThoqquaSpawnMax", 1).getInt();
		thoqquaNP  = config.get("Thoqqua", "ThoqquaNetherChance", 6).getInt();
		thoqquaNMn = config.get("Thoqqua", "ThoqquaNetherMin", 1).getInt();
		thoqquaNMx = config.get("Thoqqua", "ThoqquaNetherMax", 2).getInt(); 
		
		config.addCustomCategoryComment("Troll", "Spawn configurations for troll");		
		trollIg = config.get("Troll", "TrollSpawnAnywhere", false).getBoolean();;
		trollP  = config.get("Troll", "TrollSpawnChance", 6).getInt();
		trollMn = config.get("Troll", "TrollSpawnMin", 1).getInt();
		trollMx = config.get("Troll", "TrollSpawnMax", 4).getInt();
		
		config.addCustomCategoryComment("Vescavor", "Spawn configurations for vescavor");		
		vescavorIg = config.get("Vescavor", "VescavorSpawnAnywhere", false).getBoolean();;
		vescavorP  = config.get("Vescavor", "VescavorSpawnChance", 6).getInt();
		vescavorMn = config.get("Vescavor", "VescavorSpawnMin", 2).getInt();
		vescavorMx = config.get("Vescavor", "VescavorSpawnMax", 8).getInt();  
		
		config.addCustomCategoryComment("Revenant", "Spawn configurations for revenant");		
		revenantIg = config.get("Revenant", "RevenantSpawnAnywhere", false).getBoolean();;
		revenantP  = config.get("Revenant", "RevenantSpawnChance", 3).getInt();
		revenantMn = config.get("Revenant", "RevenantSpawnMin", 1).getInt();
		revenantMx = config.get("Revenant", "RevenantSpawnMax", 2).getInt(); 
		
		config.addCustomCategoryComment("Vampire", "Spawn configurations for vampire");		
		vampireIg = config.get("Vampire", "VampireSpawnAnywhere", false).getBoolean();;
		vampireP  = config.get("Vampire", "VampireSpawnChance", 2).getInt();
		vampireMn = config.get("Vampire", "VampireSpawnMin", 1).getInt();
		vampireMx = config.get("Vampire", "VampireSpawnMax", 2).getInt(); 
		
		config.addCustomCategoryComment("Fallen Angel", "Spawn configurations for fallen angel");		
		fallenAngelIg = config.get("Fallen Angel", "FASpawnAnywhere", false).getBoolean();
		fallenAngelP  = config.get("Fallen Angel", "FASpawnChance", 1).getInt();
		fallenAngelMn = config.get("Fallen Angel", "FASpawnMin", 1).getInt();
		fallenAngelMx = config.get("Fallen Angel", "FASpawnMax", 1).getInt(); 
		fallenAngelNP  = config.get("Fallen Angel", "FANetherChance", 1).getInt();
		fallenAngelNMn = config.get("Fallen Angel", "FANetherMin", 1).getInt();
		fallenAngelNMx = config.get("Fallen Angel", "FANetherMax", 1).getInt(); 
		
		config.addCustomCategoryComment("Outer Thing", "Spawn configurations for other thing");		
		outerThingIg = config.get("Outer Thing", "OTSpawnAnywhere", false).getBoolean();	
		outerThingEI = config.get("Outer Thing", "OTMainEndIsland", false).getBoolean();
		outerThingP  = config.get("Outer Thing", "OTSpawnChance", 1).getInt();
		outerThingMn = config.get("Outer Thing", "OTSpawnMin", 1).getInt();
		outerThingMx = config.get("Outer Thing", "OTSpawnMax", 1).getInt();
		outerThingEP  = config.get("Outer Thing", "OTEndChance", 1).getInt();
		outerThingEMn = config.get("Outer Thing", "OTEndMin", 1).getInt();
		outerThingEMx = config.get("Outer Thing", "OTEndMax", 1).getInt();  
		
		config.addCustomCategoryComment("Lizalfos", "Spawn configurations for lizalfos");		
		lizalfosIg = config.get("Lizalfos", "LizalfosSpawnAnywhere", false).getBoolean();;
		lizalfosP  = config.get("Lizalfos", "LizalfosSpawnChance", 2).getInt();
		lizalfosMn = config.get("Lizalfos", "LizalfosSpawnMin", 2).getInt();
		lizalfosMx = config.get("Lizalfos", "LizalfosSpawnMax", 2).getInt(); 

		// Save It!!!
		config.save();
	}

}
