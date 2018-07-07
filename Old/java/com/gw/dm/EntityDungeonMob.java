package com.gw.dm;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityDungeonMob extends EntityMob 
{
	public boolean ignoreHeight;
	//public boolean spawnChecked;
	
	public EntityDungeonMob(World par1World) 
	{
		super(par1World);
		//this.spawnChecked = !DungeonMobsHelper.getMSC();
		// TODO Auto-generated constructor stub
	}

	/*
	@Override
	public int getMaxHealth() 
	{
		return 1;
	}
	*/
	
	public void setIgnoreHeight(boolean foo)
	{
		this.ignoreHeight = foo;
	}
	
	public void onLivingUpdate()
	{
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
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        //par1NBTTagCompound.setBoolean("spawnCheck", this.spawnChecked);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        //this.spawnChecked = par1NBTTagCompound.getBoolean("spawnCheck");
    }
}
