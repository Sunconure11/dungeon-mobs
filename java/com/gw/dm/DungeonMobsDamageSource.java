package com.gw.dm;

import net.minecraft.util.DamageSource;

public class DungeonMobsDamageSource extends DamageSource 
{
	public static DamageSource bladeTrap = (new DamageSource("bladeTrap"));
	public static DamageSource petrified = (new DamageSource("petrified")).setDamageBypassesArmor();

	public DungeonMobsDamageSource(String p_i1566_1_) 
	{
		super(p_i1566_1_);
	}

	public boolean isUnblockable()
    {
        return true;
    }
}
