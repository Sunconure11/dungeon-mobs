package com.gw.dm.compat;

import jaredbgreat.dldungeons.api.DLDungeonsAPI;
import static com.gw.dm.DungeonMobs.*;
import static com.gw.dm.util.ConfigHandler.*;

public class DLDCompat {
	
	
	public static void addDLD() {		
		if (spawnGhoul)
			DLDungeonsAPI.addMob(MODID + ":dmghoul", 1,
					"SWAMP", "DESERT", "DUNGEON", "URBAN", "NECRO");
		if (spawnBeholder)
			DLDungeonsAPI.addMob(MODID + ":dmbeholder", 2,
					"MAGICAL", "MOUNTAIN", "DUNGEON", "WASTELAND", "END");
		if (spawnTroll)
			DLDungeonsAPI.addMob(MODID + ":dmtroll", 2,
					"MOUNTAIN", "FOREST", "DUNGEON", "FROZEN", "URBAN");
		if (spawnManticore)
			DLDungeonsAPI.addMob(MODID + ":dmmanticore", 2,
					"MOUNTAIN", "DESERT", "DUNGEON", "WASTELAND");
		if (spawnRevenant)
			DLDungeonsAPI.addMob(MODID + ":dmrevenant", 2,
					"FOREST", "DUNGEON", "URBAN", "NECRO", "SHADOW");
		if (spawnVampire)
			DLDungeonsAPI.addMob(MODID + ":dmvampire", 3,
					"FOREST", "PLAINS", "DUNGEON", "URBAN", "NECRO", "SHADOW");
		if (spawnHookHorror)
			DLDungeonsAPI.addMob(MODID + ":dmhookhorror", 2,
					"MOUNTAIN", "FOREST", "DUNGEON", "JUNGLE", "TECH", "FROZEN");
		if (spawnDestrachan)
			DLDungeonsAPI.addMob(MODID + ":dmdestrachan", 2,
					"SWAMP", "JUNGLE", "DUNGEON", "URBAN", "MAGICAL", "TECH");
		if (spawnCaveFisher)
			DLDungeonsAPI.addMob(MODID + ":dmcavefisher", 2,
					"MAGICAL", "TECH", "DUNGEON", "URBAN", "WASTELAND", "FROZEN");
		if (spawnHellHound)
			DLDungeonsAPI.addMob(MODID + ":dmnetherhound", 2,
					"NETHER", "FIERY", "DUNGEON");
		if (spawnUmberHulk)
			DLDungeonsAPI.addMob(MODID + ":dmumberhulk", 2,
					"SWAMP", "WASTELAND", "DUNGEON", "MUSHROOM", "TECH");
		if (spawnRustMonster)
			DLDungeonsAPI.addMob(MODID + ":dmrustmonster", 2,
					"DUNGEON", "MAGICAL", "TECH", "MUSHROOM", "WASTELAND");
		if (spawnCockatrice)
			DLDungeonsAPI.addMob(MODID + ":dmcockatrice", 1,
					"FOREST", "PLAINS", "DESERT", "DUNGEON", "MAGICAL", "WASTELAND");
		if (spawnThoqqua)
			DLDungeonsAPI.addMob(MODID + ":dmthoqqua", 2,
					"DUNGEON", "MOUNTAIN", "FIERY", "NETHER");
		if (spawnVescavor)
			// Not really elite, but their power in a crowded dungeon...? ...might as well be!
			DLDungeonsAPI.addMob(MODID + ":dmvescavor", 3,
					"MAGICAL", "TECH", "DUNGEON", "WASTELAND", "JUNGLE");
		if (spawnIllithid)
			DLDungeonsAPI.addMob(MODID + ":dmillithid", 2,
					"URBAN", "MAGICAL", "DUNGEON", "TECH", "FROZEN", "END");
		if (spawnRakshasa)
			DLDungeonsAPI.addMob(MODID + ":dmrakshasa", 3,
					"URBAN", "DESERT", "JUNGLE", "MAGICAL");
		if (spawnLizalfos)
			DLDungeonsAPI.addMob(MODID + ":dmlizalfos", 2,
					"SWAMP", "FOREST", "DUNGEON", "JUNGLE");
		if (spawnOuterThing)
			DLDungeonsAPI.addMob(MODID + ":dmeldermob", 3,
					"END", "MAGICAL", "TECH", "SHADOW", "FROZEN");
		if (spawnFallenAngel)
			DLDungeonsAPI.addMob(MODID + ":dmfallenangel", 3,
					"NETHER", "MAGICAL", "PARADISE", "WASTELAND");		
		if (spawnGhost)
			DLDungeonsAPI.addMob(MODID + ":dmghost", 2,
					"SHADOW", "PARADISE", "DUNGEON", "URBAN", "NECRO");
	
	}

}
