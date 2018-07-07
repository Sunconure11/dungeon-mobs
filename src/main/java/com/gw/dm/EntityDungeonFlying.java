package com.gw.dm;

import net.minecraft.entity.EntityFlying;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityDungeonFlying extends EntityFlying {
	public boolean ignoreHeight;
	public boolean spawnChecked;


	public EntityDungeonFlying(World worldIn) {
		super(worldIn);
	}


	public void setIgnoreHeight(boolean foo) {
		ignoreHeight = foo;
	}


	protected boolean isValidLightLevel() {
		int var1 = MathHelper.floor(posX);
		int var2 = MathHelper.floor(getEntityBoundingBox().minY);
		int var3 = MathHelper.floor(posZ);
		BlockPos bp = new BlockPos(var1, var2, var3);

		if (world.getLightFor(EnumSkyBlock.SKY, bp)
				> rand.nextInt(32)) {
			return false;
		} else {
			int var4 = world.getLightFromNeighbors(bp);

			if (this.world.isThundering()) {
				int var5 = world.getSkylightSubtracted();
				world.setSkylightSubtracted(10);
				var4 = world.getLightFromNeighbors(bp);
				world.setSkylightSubtracted(var5);
			}

			return var4 <= this.rand.nextInt(8);
		}
	}


	public boolean getCanSpawnHere() {
		if (!this.isValidLightLevel())
			return false;

		return super.getCanSpawnHere();
	}


	public void onLivingUpdate() {
		// TODO: Implement these config options.
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
