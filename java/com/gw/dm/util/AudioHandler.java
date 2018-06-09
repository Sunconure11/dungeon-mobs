package com.gw.dm.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import com.gw.dm.DungeonMobs;

public class AudioHandler {
	
	public static SoundEvent entityGhoulAmbient;
	public static SoundEvent entityGhoulHurt;
	public static SoundEvent entityGhoulDeath;
	
	
	public static void registerSounds() {
		entityGhoulAmbient = registerSound("g_l");
		entityGhoulHurt    = registerSound("g_h");
		entityGhoulDeath   = registerSound("g_d");
	}
	
	
	private static SoundEvent registerSound(String name) {
		SoundEvent evt = new SoundEvent(new ResourceLocation(DungeonMobs.MODID, name));
		evt.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(evt);
		return evt;
	}

}
