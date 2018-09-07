package com.gw.dm;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityDungeonMob extends EntityMob {
	public boolean ignoreHeight;
	public boolean spawnChecked;


	public EntityDungeonMob(World worldIn) {
		super(worldIn);
		//this.spawnChecked = !DungeonMobsHelper.getMSC();
		// TODO Auto-generated constructor stub
	}


	// FIXME: I don't know what this is for, so I don't know if I
	// should fix this or delete it...?
	/*
	@Override
	public int getMaxHealth() 
	{
		return 1;
	}
	*/


	public void setIgnoreHeight(boolean in) {
		ignoreHeight = in;
	}

	public void onLivingUpdate() {
		// TODO: Implement the use of these variable in config and gametime code.
		/*
		if(!this.spawnChecked && DungeonMobsHelper.getMSC())
		{
			if(!this.getCanSpawnHere())
				this.setDead();
			else
				this.spawnChecked = true;
		}
		*/
		super.onLivingUpdate();
	}


	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setBoolean("spawnCheck", this.spawnChecked);
		super.writeEntityToNBT(par1NBTTagCompound);
	}


	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		spawnChecked = par1NBTTagCompound.getBoolean("spawnCheck");
	}


}
