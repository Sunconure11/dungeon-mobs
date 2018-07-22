package com.gw.dm.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityRustMonster;
import com.gw.dm.entity.EntityShrieker;

public class ConfigHandler {

	public static File mainConfig;
	public static File configDir;
	
	public static boolean devMode;

	public static boolean spawnNaturally;
	public static boolean addToVanillaDungeons;
	//public static boolean addToDoomlikeDungeons;

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
	//public static boolean spawnBeamos;
	public static boolean spawnBladeTrap;
	
	public static boolean replaceRMFoods;
	public static List<String> rustMonFoodsList;
	
	public static List<String> shriekerMobs;


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
		if(!rustMonFoodsList.isEmpty()) {
			if(replaceRMFoods) {
				EntityRustMonster.setFoods(rustMonFoodsList);
			} else {
				EntityRustMonster.appendToFoods(rustMonFoodsList);
			}
		}
		
		// Shrieker Summons
		config.addCustomCategoryComment("Shrieker Summons", "Mobs that can be summoned by shriekers");
		shriekerMobs = new ArrayList<>();
		array = config.get("Shrieker Summons", "ShriekerSummons", new String[]{}, 
				"Mobs the that may be spawned when shriekers shriek (will be added to list); \r\n"
						+ "format \"modid:resource_location\"")
				.getStringList();
		shriekerMobs.addAll(Arrays.asList(array));
		if(!shriekerMobs.isEmpty()) {
			EntityShrieker.appendToSummonList(shriekerMobs);
		}
		
		
		// Save It!!!
		config.save();
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

}
