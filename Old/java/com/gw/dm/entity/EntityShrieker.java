package com.gw.dm.entity;


import com.gw.dm.EntityDungeonMob;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityShrieker extends EntityMob
{
	private int screamTicks;
	private boolean hasScreamed;
	private static int[] entityIDs = {50,51,52,54,59,60};
	private int maxSummoned;
	private static String[] entityNames = 
		{
			"Zombie","Skeleton","Spider","CaveSpider","Creeper","Witch","Enderman"
		};

	public EntityShrieker(World par1World) 
	{
		super(par1World);
		this.experienceValue = 15;
		this.setSize(1.0F, 1.0F);
		this.screamTicks = 0;
		this.maxSummoned = 5;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
    }
	
	protected boolean isAIEnabled()
	{
		return false;
	}
	
	public static void appendToSummonList(int id)
	{
		int[] foo = new int[entityIDs.length + 1];
		int ctr;
		
		for(ctr = 0; ctr < entityIDs.length; ctr++)
		{
			foo[ctr] = entityIDs[ctr];
		}
		
		foo[ctr] = id;
		
		entityIDs = null;
		entityIDs = foo;
	}
	
	public static void appendToSummonList(String name)
	{
		String[] foo = new String[entityNames.length + 1];
		int ctr;
		
		for(ctr = 0; ctr < entityNames.length; ctr++)
		{
			foo[ctr] = entityNames[ctr];
		}
		
		foo[ctr] = name;
		
		entityNames = null;
		entityNames = foo;
	}

	public int getTotalArmorValue()
	{
		return 0;
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}
	
	@Override
	protected String getLivingSound()
	{
		return null;
	}
	
	@Override
	protected String getHurtSound()
	{
		return null;
	}
	
	@Override
	protected String getDeathSound()
	{
		return null;
	}
	
	@Override
	public void onLivingUpdate()
    {
		if(this.worldObj.getBlock((int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.air)
		{
			this.setDead();
		}
		else
		{
			float light = this.getBrightness(1.0F);
			this.hasScreamed = false;
			
			if(light > 0.08F)
			{
				this.screamTicks++;
				
				if(this.screamTicks % 70 == 0)
				{
					this.worldObj.playSoundAtEntity(this, "dungeonmobs:s_s", 1.0F, 1.0F);
					this.hasScreamed = true;
				}
				
				if(this.screamTicks % 150 == 0)
				{
					if(this.worldObj.getClosestPlayerToEntity(this, 32.0D) != null)
						this.summonMobs();
					
					this.screamTicks = 0;
				}
			}
		}
		
		super.onLivingUpdate();
    }
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	 {
		 if(par1DamageSource.getEntity() instanceof EntityPlayer)
		 {
			 EntityPlayer foo = (EntityPlayer)par1DamageSource.getEntity();
			 
			 if(!foo.capabilities.isCreativeMode)
			 {
				 this.worldObj.playSoundAtEntity(this, "dungeonmobs:s_s", 1.5F, 1.0F);
				 
				 int var = 0;
				 
				 if(this.worldObj.difficultySetting == EnumDifficulty.EASY)
					 var = 1;
				 if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
					 var = 2;
				 if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
					 var = 3;
				 
				 if(this.rand.nextInt(4 - var) == 0)
					 this.summonMobs();
			 }
		 }
		 
		 return super.attackEntityFrom(par1DamageSource, par2);
	 }
	
	@Override
	protected void collideWithEntity(Entity par1Entity)
    {
		// Nothin', damnit!
    }
	
	@Override
	public void onCollideWithPlayer(EntityPlayer ent)
	{
		if(!ent.capabilities.isCreativeMode)
		{
			this.worldObj.playSoundAtEntity(this, "dungeonmobs:s_s", 1.5F, 1.0F);
			this.summonMobs();
		}
	}
	
	@Override
	public void faceEntity(Entity par1Entity, float par2, float par3)
    {
        // No!
    }
	
	protected int getDropItemId()
	{
		return Item.getIdFromItem(Items.nether_wart);
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		
		var3 = this.rand.nextInt(2 + par2);

        for (var4 = 0; var4 < var3; var4++)
        {
            this.dropItem(Items.nether_wart, 1);
        }
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 50.0D)
			return false;
		
		if(this.worldObj.getBlockLightValue((int)this.posX, (int)this.posY, (int)this.posZ) > 4)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	protected void summonMobs()
	{
		int numMobs = this.rand.nextInt(4) + 1;
		
		if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
			numMobs++;
		if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
			numMobs += 3;
		
		if(!this.worldObj.isRemote)
		{
			for(int i = 0; i < numMobs; i++)
			{
				/*
				int curSummonedGuess = this.worldObj.getEntitiesWithinAABB(EntityMob.class, this.boundingBox.expand(8.0D, 2.0D, 8.0D)).size();
				
				if(curSummonedGuess > maxSummoned)
					break;
				*/
				
				int fubar = this.rand.nextInt(entityNames.length);
				
				//EntityLiving foo = (EntityLiving)EntityList.createEntityByID(entityIDs[this.rand.nextInt(entityIDs.length)], this.worldObj);
				EntityLiving foo = (EntityLiving)EntityList.createEntityByName(entityNames[fubar], this.worldObj);
				
				double par2 = this.posX + (this.rand.nextDouble() * 4.0D) - (this.rand.nextDouble() * 4.0D);
				double par4 = this.posY;// + this.rand.nextDouble();
				double par6 = this.posZ + (this.rand.nextDouble() * 4.0D) - (this.rand.nextDouble() * 4.0D);
				
				if(Math.abs(Math.abs(par2) - Math.abs(this.posX)) < 2)
				{
					if(par2 < 0)
						par2 -= 2.0D;
					else
						par2 += 2.0D;
				}
				
				if(Math.abs(Math.abs(par6) - Math.abs(this.posZ)) < 2)
				{
					if(par6 < 0)
						par6 -= 2.0D;
					else
						par6 += 2.0D;
				}
				
				this.makeSummonIgnoreHeight(foo);
				
				if (foo != null)
	            {
	                foo.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(this.worldObj.rand.nextFloat() * 360.0F), 0.0F);
	                foo.rotationYawHead = foo.rotationYaw;
	                foo.renderYawOffset = foo.rotationYaw;
	                
	                if(foo.getCanSpawnHere() && !this.worldObj.isRemote)
	                {
	                	//foo.entityInit();
	                	this.worldObj.spawnEntityInWorld(foo);
	                	
	                	if(foo instanceof EntityLizalfos)
	                		((EntityLizalfos)foo).forceTwinSpawn();
	                }
	            }
			}
		}
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 0;
	}
	
	public boolean canAttackClass(Class par1Class)
    {
        return false;
    }
	
	private void makeSummonIgnoreHeight(EntityLiving ent)
    {
		if(ent instanceof EntityDungeonMob)
		{
			EntityDungeonMob foo = (EntityDungeonMob)ent;
			foo.setIgnoreHeight(true);
		}
		/*
		else if(ent instanceof EntityDungeonFlying)
        {
        	EntityDungeonFlying foo = (EntityDungeonFlying)ent;
        	foo.setIgnoreHeight(true);
        }
        */
        else if(ent instanceof EntityGhoul)
        {
        	EntityGhoul foo = (EntityGhoul)ent;
        	foo.setIgnoreHeight(true);
        }
    }
}
