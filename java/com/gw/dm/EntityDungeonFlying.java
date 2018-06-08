package com.gw.dm;

import net.minecraft.entity.EntityFlying;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityDungeonFlying extends EntityFlying 
{
	public boolean ignoreHeight;
	public boolean spawnChecked;
	
	public EntityDungeonFlying(World par1World) 
	{
		super(par1World);
		//this.spawnChecked = !DungeonMobsHelper.getMSC();
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
	
	protected boolean isValidLightLevel()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.boundingBox.minY);
        int var3 = MathHelper.floor_double(this.posZ);

        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > this.rand.nextInt(32))
            return false;
        else
        {
            int var4 = this.worldObj.getBlockLightValue(var1, var2, var3);

            if (this.worldObj.isThundering())
            {
                int var5 = this.worldObj.skylightSubtracted;
                this.worldObj.skylightSubtracted = 10;
                var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
                this.worldObj.skylightSubtracted = var5;
            }

            return var4 <= this.rand.nextInt(8);
        }
    }
	
	public boolean getCanSpawnHere()
	{
		if(!this.isValidLightLevel())
			return false;
		
		return super.getCanSpawnHere();
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
