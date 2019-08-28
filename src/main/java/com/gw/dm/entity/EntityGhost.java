package com.gw.dm.entity;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.gw.dm.EntityDungeonMob;

public class EntityGhost extends EntityDungeonMob {

	public EntityGhost(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}


	@SideOnly(Side.CLIENT)
	@Override
	public int getBrightnessForRender() {
		return 0xf000f0;
	}
	

}
