package com.gw.dm.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobsDamageSource;
import com.gw.dm.DungeonMobs;
import com.gw.dm.blocks.BlockBladeTrap;

public class EntityBladeTrap extends EntityLiving
{
	private int[] dir = {0, 0, 0};
	
	public int moveTime = 0;
	public int attackTimer = 0;
	
	public EntityBladeTrap(World par1World) 
	{
		super(par1World);
		this.setSize(0.98F, 0.98F);
	}

	public EntityBladeTrap(World w, double x, double y, double z) 
	{
		super(w);
		this.setSize(0.98F, 0.98F);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
    }
	
	public void setDirection(int[] d)
	{
		this.dir = d;
	}
	
    protected boolean canTriggerWalking()
    {
        return false;
    }
    
    public boolean canBeCollidedWith()
    {
        return false;
    }	
    
    public void onUpdate()
    {
    	this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        ++this.moveTime;
        ++this.attackTimer;
        
        if(this.attackTimer > 11)
        	this.attackTimer = 11;
        
        this.motionX += (0.12D * dir[0]);
        this.motionY += (0.12D * dir[1]);
        this.motionZ += (0.12D * dir[2]);
        
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;
        
        if (!this.worldObj.isRemote)
        {
            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY);
            int k = MathHelper.floor_double(this.posZ);
        
            if(!BlockBladeTrap.canMoveInto(this.worldObj, i + this.dir[0], j + this.dir[1], k + this.dir[2]))
	        {
	        	this.worldObj.setBlock(i, j, k, DungeonMobs.bladeTrap, 0, 3);
	        	
	        	this.setDead();
	        }
            else if(this.moveTime > 40)
            {
            	this.worldObj.setBlock(i, j, k, DungeonMobs.bladeTrap, 0, 3);
	        	
	        	this.setDead();
            }
        }
        
        if(this.attackTimer > 10)
        {
        	boolean flag = false;
        	
        	List<Entity> foo = null;
        	
        	/*
        	try
        	{
        		foo = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox().expand(0.05F, 0.05F, 0.05F));
        	}
        	catch(NullPointerException e)
        	{
        		flag = true;
        	}
        	*/
        	
        	try
        	{
        		foo = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox());
        	}
        	catch(NullPointerException e)
        	{
        		flag = true;
        	}
        	
	        if(!flag && !(foo == null) && !foo.isEmpty())
	        {
		        Iterator iter = foo.iterator();
		        
		        while(iter.hasNext())
		        {
		        	Entity bar = (Entity)iter.next();
		        	this.worldObj.playSoundAtEntity(bar, "dungeonmobs:bl_b", 1.0F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F + 1.0F);
		        	
		        	int dmgValue = 1;
		    		
		    		if(this.worldObj.difficultySetting == EnumDifficulty.EASY)
		    			dmgValue = 2;
		    		else if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
		    			dmgValue = 5;
		    		else if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
		    			dmgValue = 8;
		        	
			        //bar.attackEntityFrom(DamageSource.cactus, dmgValue);
		    		bar.attackEntityFrom(DungeonMobsDamageSource.bladeTrap, dmgValue);
		        }
		        
		        this.attackTimer = 0;
	        }
        }
    }
    
	@Override
	public void jump()
	{
	}    
	
	@Override
	protected void fall(float par1)
    {
        // don't fall, mothafucka!
    }
	
	public boolean canRenderOnFire()
    {
        return false;
    }
    
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) 
    {
    	int dmgValue = 4;
		
		if(this.worldObj.difficultySetting == EnumDifficulty.EASY)
			dmgValue += 2;
		else if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
			dmgValue += 5;
		else if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
			dmgValue += 8;
    	
    	//par1EntityPlayer.attackEntityFrom(DamageSource.cactus, dmgValue);
		par1EntityPlayer.attackEntityFrom(DungeonMobsDamageSource.bladeTrap, dmgValue);
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		// nope!
    	
    	return false;
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
    
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("moveTime", this.moveTime);
        par1NBTTagCompound.setInteger("atkTime", this.attackTimer);
        
        par1NBTTagCompound.setInteger("d0", this.dir[0]);
        par1NBTTagCompound.setInteger("d1", this.dir[1]);
        par1NBTTagCompound.setInteger("d2", this.dir[2]);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.moveTime = par1NBTTagCompound.getInteger("moveTime");
        this.attackTimer = par1NBTTagCompound.getInteger("atkTime");
        
        this.dir[0] = par1NBTTagCompound.getInteger("d0");
        this.dir[1] = par1NBTTagCompound.getInteger("d1");
        this.dir[2] = par1NBTTagCompound.getInteger("d2");
    }
	
	protected String getLivingSound()
	{
		return "dungeonmobs:bl_l";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:be_sw";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:bl_d";
	}
	
	public int getTalkInterval()
    {
        return 20;
    }
	
	public boolean getCanSpawnHere()
	{
		return false;
		
		/*
		System.out.println("DEBUG: Blade Trap attempting to spawn. [X: " + this.posX + ", Y: " + this.posY + ", Z: " + this.posZ + "]");
		
		//ChunkPosition foo = this.worldObj.findClosestStructure("Stronghold", 8, 8, 8);
		//ChunkPosition bar = this.worldObj.findClosestStructure("Dungeon", 8, 8, 8);
		
		ChunkPosition foo = this.worldObj.findClosestStructure("Stronghold", (int)this.posX, (int)this.posY, (int)this.posZ);
		
		if(foo == null)
			foo = this.worldObj.findClosestStructure("Dungeon", 8, 8, 8);
		
		if(foo != null)
			System.out.println("DEBUG: ChunkPosition foo. [X: " + foo.x + ", Y: " + foo.y + ", Z: " + foo.z + "]");
		
		boolean spawned = false;
		
		if(foo.x < (this.posX - 32) || foo.x > (this.posX + 32))
			return false;
		if(foo.z < (this.posZ - 32) || foo.z > (this.posZ + 32))
			return false;
		
		if(foo != null)
		{
			System.out.println("DEBUG: Blade Trap found a place to spawn. [X: " + this.posX + ", Y: " + this.posY + ", Z: " + this.posZ + "]");
			
			int barX;
			int barY;
			int barZ;
			
			for(int i = 0; i < 10 && !spawned; i++)
			{
				barX = foo.x + (this.getRNG().nextInt(33) - 16);
				barY = foo.y + (this.getRNG().nextInt(33) - 16);
				barZ = foo.z + (this.getRNG().nextInt(33) - 16);
				
				if(barY < 1)
					barY = 1;
				
				if(barY > 50)
					barY = 50;
				
				while(this.worldObj.canBlockSeeTheSky(barX, barY, barZ))
				{
					barY--;
				}
				
				if(this.worldObj.getBlockId(barX, barY, barZ) == 0)
				{
					if(this.findNearbyMoss(barX, barY, barZ))
					{
						this.posX = barX;
						this.posY = barY;
						this.posZ = barZ;
						spawned = true;
						
						System.out.println("DEBUG: Blade Trap is spawning. [X: " + this.posX + ", Y: " + this.posY + ", Z: " + this.posZ + "]");
					}
				}
			}
		}
		*/
		/*
		else
			return false;
		*/
		
		/*
		if(this.tooManyTrapsNearby((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY + 1, (int)this.posZ))
			return false;
		
		if(!spawned)
			return false;
		else
		{
			System.out.println("DEBUG: Blade Trap has landed.");
			System.out.println("DEBUG: Blade Trap final position. [X: " + this.posX + ", Y: " + this.posY + ", Z: " + this.posZ + "]");
			this.moveTime = 90;
			return super.getCanSpawnHere();
		}
		*/
	}
	
	private boolean tooManyTrapsNearby(int x, int y, int z) 
	{
		int count = 0;
		int max = 4;
		
		for(int i = -6; i < 6; i++)
		{
			for(int j = -6; j < 6; j++)
			{
				for(int k = -6; k < 6; k++)
				{
					int foo = y + j;
					
					if(foo < 1)
						foo = 1;
						
					if(this.worldObj.getBlock(x + i, foo, z + k) == DungeonMobs.bladeTrap)
						count++;
				}
			}
		}
		
		/*
		List<EntityBladeTrap> foo = this.worldObj.getEntitiesWithinAABB(EntityBladeTrap.class, this.getBoundingBox().expand(4.0D, 4.0D, 4.0D));
		
		if(foo != null)
		{
			count += foo.size();
			max += 3;
		}
		*/
		
		if(count > max)
			return true;
		else
			return false;
	}

	public boolean findNearbyMoss(int x, int y, int z)
	{
		for(int i = -4; i < 4; i++)
		{
			for(int j = -4; j < 4; j++)
			{
				for(int k = -4; k < 4; k++)
				{
					if(this.worldObj.getBlock(x + i, y + j, z + k) == Blocks.stonebrick)
					{
						if(this.worldObj.getBlockMetadata(x + i, y + j, z + k) > 0)
							return true;
					}
				}
			}
		}
		
		return false;
	}
}