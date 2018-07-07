package com.gw.dm.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.gw.dm.DungeonMobs;
import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntityThoqqua extends EntityDungeonMob 
{
	public List<int[]> blocksConverted;
	public int lavaResetTimer;
	public int setShitOnFire;
	
	public EntityThoqqua(World par1World) 
	{
		super(par1World);
        this.setSize(1.8F, 0.5F);
        
        this.experienceValue = 30;
		this.isImmuneToFire = true;
		this.ignoreHeight = false;
		
		this.blocksConverted = new LinkedList();
		this.lavaResetTimer = 0;
		this.setShitOnFire = 0;
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.provider instanceof WorldProviderHell)
			return super.getCanSpawnHere();
		
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 32.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }
	
	public int getTotalArmorValue()
	{
		return 8;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 4;
	}
	
	protected String getLivingSound()
    {
        return "mob.silverfish.say";
    }

    protected String getHurtSound()
    {
        return "mob.silverfish.hit";
    }

    protected String getDeathSound()
    {
        return "mob.silverfish.kill";
    }
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		
		var3 = this.rand.nextInt(3);

        for (var4 = 0; var4 < var3; var4++)
        {
        	this.dropItem(Items.fire_charge, 1);
        }
	}
	
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		this.changeLavaToMagma(false);
		
		this.setShitOnFire++;
		
		int foo;
		
		if(DungeonMobsHelper.getDifficulty(this.worldObj) < 1)
			foo = 0;
		else if(DungeonMobsHelper.getDifficulty(this.worldObj) < 3)
			foo = 1;
		else
			foo = 2;
		
		if(this.setShitOnFire > 79)
		{
			for(int a = (int)this.posX - foo; a < (int)this.posX + foo; a++)
			{
				for(int b = (int)this.posY - foo; b < (int)this.posY + foo; b++)
				{
					for(int c = (int)this.posZ - foo; c < (int)this.posZ + foo; c++)
					{
						this.makeFire(a, b, c);
					}
				}
			}
			
			this.setShitOnFire = 0;
		}
	}
	
	private void makeFire(int x, int y, int z)
	{
		double foo = this.worldObj.rand.nextFloat() - 0.5F;
		double bar = this.worldObj.rand.nextFloat() - 0.5F;
		double cow = this.worldObj.rand.nextFloat() - 0.5F;
		this.worldObj.spawnParticle("flame", x + foo, y + bar, z + cow, 0.0D, 0.0D, 0.0D);
		
		foo = this.worldObj.rand.nextFloat() - 0.5F;
		bar = this.worldObj.rand.nextFloat() - 0.5F;
		cow = this.worldObj.rand.nextFloat() - 0.5F;
		this.worldObj.spawnParticle("smoke", x + foo, y + bar, z + cow, 0.0D, 0.0D, 0.0D);
		
		if(this.worldObj.getBlock(x, y, z) == Blocks.air)
			this.worldObj.setBlock(x, y, z, Blocks.fire);
		else if(this.worldObj.getBlock(x, y, z) == Blocks.water)
		{
			this.worldObj.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.fizz", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			this.worldObj.setBlock(x, y, z, Blocks.cobblestone);
		}
	}
	
	protected boolean isAIEnabled()
	{
		return false;
	}

	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
	
	protected void updateEntityActionState()
    {
        super.updateEntityActionState();

        if (!this.worldObj.isRemote)
        {
        	if (this.entityToAttack == null && !this.hasPath())
            {
        		int i;
                int j;
                int k;
                Block l;
        		
                i = MathHelper.floor_double(this.posX);
                j = MathHelper.floor_double(this.posY + 0.5D);
                k = MathHelper.floor_double(this.posZ);
                
                for(int l1 = 0; l1 < 6; l1++)
                {
	                //int l1 = this.rand.nextInt(6);
	                
	                l = this.worldObj.getBlock(i + Facing.offsetsXForSide[l1], j + Facing.offsetsYForSide[l1], k + Facing.offsetsZForSide[l1]);
	
	                if (l == Blocks.stone)
	                {
	                    this.worldObj.setBlock(i + Facing.offsetsXForSide[l1], j + Facing.offsetsYForSide[l1], k + Facing.offsetsZForSide[l1], Blocks.lava, 0, 3);
	                    int[] foo = {i + Facing.offsetsXForSide[l1], j + Facing.offsetsYForSide[l1], k + Facing.offsetsZForSide[l1]};
	                    this.blocksConverted.add(foo);
	                }
	                else
	                    this.updateWanderPath();
                }
            }
            else if (this.entityToAttack != null && !this.hasPath())
                this.entityToAttack = null;
        }
    }
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("LavaTimer", this.lavaResetTimer);
        par1NBTTagCompound.setInteger("FireTimer", this.setShitOnFire);
        
        int foo = this.blocksConverted.size();
        
        par1NBTTagCompound.setInteger("BlockCount", foo);
        
        for(int i = 0; i < foo; i++)
        {
        	par1NBTTagCompound.setIntArray("Block[" + i + "]", this.blocksConverted.get(i));
        }
        
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.lavaResetTimer  = par1NBTTagCompound.getInteger("LavaTimer");
        this.setShitOnFire   = par1NBTTagCompound.getInteger("FireTimer");
        
        int foo = par1NBTTagCompound.getInteger("BlockCount");
        
        for(int i = 0; i < foo; i++)
        {
        	this.blocksConverted.add(par1NBTTagCompound.getIntArray("Block[" + i + "]"));
        }
    }
	
	protected Entity findPlayerToAttack()
    {
        double d0 = 16.0D;
        return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
    }
	
	protected void attackEntity(Entity par1Entity, float par2)
    {
        if (this.attackTime <= 0 && par2 < 1.2F && this.boundingBox.expand(1.5D, 1.5D, 1.5D).intersectsWith(par1Entity.boundingBox))
        {
            this.attackTime = 20;
            this.attackEntityAsMob(par1Entity);
        }
    }
	
	public float getBlockPathWeight(int par1, int par2, int par3)
    {
        return this.worldObj.getBlock(par1, par2, par3) == Blocks.stone ? 10.0F : super.getBlockPathWeight(par1, par2, par3);
    }
	
	public boolean canRenderOnFire()
	{
		return false;
	}
	
	protected void despawnEntity()
    {
		this.changeLavaToMagma(true);
		
		super.despawnEntity();
    }
	
	private void changeLavaToMagma(boolean forced)
	{
		if(forced)
		{
			for(int i = 0; i < this.blocksConverted.size(); i++)
			{
				int[] foo = this.blocksConverted.get(i);
				
				if(this.worldObj.getBlock(foo[0], foo[1], foo[2]) == Blocks.flowing_lava)
					this.worldObj.setBlock(foo[0], foo[1], foo[2], DungeonMobs.lavaRock, 6, 3);
			}
			
			this.blocksConverted.clear();
		}
		else if(!this.blocksConverted.isEmpty())
		{
			this.lavaResetTimer++;
		
			if(this.lavaResetTimer > (99 + (100 * DungeonMobsHelper.getDifficulty(this.worldObj))))
			{
				int[] foo = this.blocksConverted.get(0);
				
				if(this.worldObj.getBlock(foo[0], foo[1], foo[2]) == Blocks.flowing_lava || this.worldObj.getBlock(foo[0], foo[1], foo[2]) == Blocks.lava)
					this.worldObj.setBlock(foo[0], foo[1], foo[2], DungeonMobs.lavaRock, 6, 3);
				
				this.blocksConverted.remove(0);
				
				this.lavaResetTimer = 0;
			}
		}
	}
	
	public void onDeath(DamageSource par1DamageSource)
    {
		this.changeLavaToMagma(true);
		
		super.onDeath(par1DamageSource);
    }
}
