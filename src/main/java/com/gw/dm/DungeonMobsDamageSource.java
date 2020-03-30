package com.gw.dm;

import net.minecraft.util.DamageSource;

public class DungeonMobsDamageSource extends DamageSource {
	public static final DamageSource BLADE_TRAP = (new DamageSource("bladetrap"));
	public static final DamageSource PETRIFIED = (new DamageSource("petrified")).setDamageBypassesArmor();
	public static final DamageSource ENERGY_DRAIN = (new DamageSource("energydrain")).setDamageBypassesArmor();
	public static final DamageSource LIGHT_BALL = new DamageSource("light_ball").setProjectile();//.setDamageBypassesArmor();  // Now partial, works differently
	
	public DungeonMobsDamageSource(String name) {
		super(name);
	}
	
	
	@Override
	public boolean isUnblockable() {
		return true;
	}
}
