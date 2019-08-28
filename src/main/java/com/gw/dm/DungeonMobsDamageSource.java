package com.gw.dm;

import net.minecraft.util.DamageSource;

public class DungeonMobsDamageSource extends DamageSource {
	public static DamageSource bladeTrap   = (new DamageSource("bladeTrap"));
	public static DamageSource petrified   = (new DamageSource("petrified")).setDamageBypassesArmor();
	public static DamageSource energyDrain = (new DamageSource("energydrain")).setDamageBypassesArmor();

	public DungeonMobsDamageSource(String name) {
		super(name);
	}


	@Override
	public boolean isUnblockable() {
		return true;
	}
}
