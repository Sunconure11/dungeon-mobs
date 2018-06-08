package com.gw.dm.entity;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.ai.EntityAIFollowTwin;
import com.gw.dm.ai.EntityAIMonsterPanic;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
//import net.minecraft.block.StepSound;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityLizalfos extends EntityDungeonMob 
{
	private int twinID;
	private int twinEntityID;
	private boolean isTwin;
	private boolean ignoreHeight;
	private EntityLizalfos myTwin;
	
	private WeakReference<EntityLizalfos> myTwinWeak;
	
	private boolean isLeaping;
	public boolean myTwinIsDead;
	public boolean goodToGo;
	public boolean isRetreating;
	public boolean hasRetreated;
	
	private int runTimer;
	
	private EntityAIFollowTwin followTwin;
	private EntityAIAttackOnCollide attack;
	private EntityAIAvoidEntity retreat;
	private EntityAIMonsterPanic panic;
	
	private EntityAIHurtByTarget revenge;
	private EntityAINearestAttackableTarget target;

	public EntityLizalfos(World par1World) 
	{
		super(par1World);
		this.ignoreHeight = false;
		this.twinID = -1;
		this.isTwin = false;
		this.myTwin = null;
		this.myTwinIsDead = false;
		this.goodToGo = false;
		
		this.isRetreating = false;
		this.hasRetreated = false;
		
		this.setSize(1.4F, 2.5F);
		this.experienceValue = 30;
		
		this.runTimer = 0;
		
		this.followTwin = new EntityAIFollowTwin(this, 1.0F);
		this.attack = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0F, false);
		this.retreat = new EntityAIAvoidEntity(this, EntityPlayer.class, 24.0F, 1.0F, 0.5F);
		this.panic = new EntityAIMonsterPanic(this, 1.0F);
		
		this.revenge = new EntityAIHurtByTarget(this, false);
		this.target = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.attack);
		
		this.tasks.addTask(4, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, this.revenge);
		this.targetTasks.addTask(1, this.target);
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }
	
	protected boolean isAIEnabled()
	{
		return true;
	}

	public int getTotalArmorValue()
	{
		return 10;
	}
		
	public int getAttackStrength(Entity par1Entity)
	{
		if(!this.onGround)
			return 12;
		else
			return 7;
	}
	
	public int getTalkInterval()
    {
        return 80;
    }
	
	protected String getLivingSound()
	{
		if(this.isTwin())
			return "dungeonmobs:li_l";
		else
			return "dungeonmobs:li_l2";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:li_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:li_d";
	}
	
	public int getTwinID()
	{
		return this.twinID;
	}
	
	public boolean isTwin()
	{
		return this.isTwin;
	}
	
	public void setIsTwin(boolean foo)
	{
		this.isTwin = foo;
	}
	
	public void setTwinID(int i)
	{
		this.twinID = i;
	}
	
	public void setTwin(EntityLizalfos foo)
	{
		this.myTwinWeak = new WeakReference<EntityLizalfos>(foo);
		//this.myTwin = foo;
	}
	
	public EntityLizalfos getTwin()
	{
		return this.myTwinWeak.get();
		//return myTwin;
	}
	
	public void killTwin()
	{
		this.myTwinIsDead = true;
		this.myTwinWeak = null;
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.isTwin())
			return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
		
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 32.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	public boolean isTwinDead()
	{
		return this.myTwinIsDead;
	}
	
	public void setDead()
	{
		if(this.myTwinWeak != null && !this.isTwinDead())
			this.myTwinWeak.get().killTwin();
		
		super.setDead();
	}
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("twinID", this.twinID);
        par1NBTTagCompound.setBoolean("isTwin", this.isTwin);
        par1NBTTagCompound.setBoolean("twinDead", this.myTwinIsDead);
        par1NBTTagCompound.setBoolean("hasFled", this.hasRetreated);
        par1NBTTagCompound.setBoolean("isRunning", this.isRetreating);
        par1NBTTagCompound.setInteger("runTimer", this.runTimer);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.twinID = par1NBTTagCompound.getInteger("twinID");
        this.isTwin = par1NBTTagCompound.getBoolean("isTwin");
        this.myTwinIsDead = par1NBTTagCompound.getBoolean("twinDead");
        this.hasRetreated = par1NBTTagCompound.getBoolean("hasFled");
        this.isRetreating = par1NBTTagCompound.getBoolean("isRunning");
        this.runTimer     = par1NBTTagCompound.getInteger("runTimer");
        
        if(this.isRetreating)
        {
        	this.retreat = new EntityAIAvoidEntity(this, EntityPlayer.class, 12.0F, 1.0F, 0.4F);
        	
        	this.tasks.removeTask(this.attack);
        	this.tasks.removeTask(this.followTwin);
        	
        	this.tasks.addTask(3, this.retreat);
        }
	
        
        /*
        boolean foundFlag = false;
        
        if(!myTwinIsDead)
        {
        	System.out.println("I have a twin, looking for him...");
        	
        	List allEntities = this.worldObj.getLoadedEntityList();
        	
        	Iterator iter = allEntities.iterator();
        	
        	while(iter.hasNext() && !foundFlag)
        	{
        		System.out.println("Is this next guy my twin?");
        		
        		Entity foo = (Entity)iter.next();
        		
        		if(foo instanceof EntityLizalfos)
        		{
        			System.out.println("This guy might be my twin!");
        			
        			EntityLizalfos bar = (EntityLizalfos)foo;
        			
        			if(this.twinID == bar.getTwinID())
        			{
        				System.out.println("I found my twin.");
        				this.setTwin(bar);
        				bar.setTwin(this);
        				foundFlag = true;
        				
        				System.out.println("I think my twin's ID is " + bar.entityId);
        				System.out.println("My twin thinks my ID is " + bar.myTwin.entityId);
        				System.out.println("My actual ID is " + this.entityId);
        			}
        		}
        	}
        }
        else
        	System.out.println("My twin is dead.");
        
        */
    }
	
	protected void entityInit()
    {
		if(this.twinID == -1 && !this.worldObj.isRemote)
		{
			boolean twinSpawned = false;
			
			this.twinID = this.worldObj.rand.nextInt(16000);
			
			int spawnAttempts = 0;
			
			while(!twinSpawned && spawnAttempts < 6)
			{
				WeakReference<EntityLizalfos> bar = new WeakReference<EntityLizalfos>(new EntityLizalfos(this.worldObj));
				
				EntityLizalfos foo = bar.get();
				
				double randX = this.posX + ((this.worldObj.rand.nextFloat() - 0.5F) * 8.0F);
				double randY = this.posY + 0.5F;
				double randZ = this.posZ + ((this.worldObj.rand.nextFloat() - 0.5F) * 8.0F);
				
				foo.setLocationAndAngles(randX, randY, randZ, this.rotationYaw, this.rotationPitch);
				
				foo.setIsTwin(true);
				foo.setTwinID(this.twinID);
				
				if(foo.getCanSpawnHere())
				{
					foo.setTwin(this);
					this.setTwin(foo);
					
					this.twinEntityID = this.getTwin().getEntityId();
					this.getTwin().twinEntityID = this.getEntityId();
					
					foo.entityInit();
					this.worldObj.spawnEntityInWorld(foo);
					twinSpawned = true;
				}
				else
					spawnAttempts++;
			}
			
			if(spawnAttempts > 5)
				this.setDead();
		}
		
		super.entityInit();
    }
	
	public void forceTwinSpawn()
	{
		if(this.twinID == -1 && !this.worldObj.isRemote)
		{
			boolean twinSpawned = false;
			
			this.twinID = this.worldObj.rand.nextInt(16000);
			
			int spawnAttempts = 0;
			
			while(!twinSpawned && spawnAttempts < 6)
			{
				WeakReference<EntityLizalfos> bar = new WeakReference<EntityLizalfos>(new EntityLizalfos(this.worldObj));
				
				EntityLizalfos foo = bar.get();
				
				double randX = this.posX + ((this.worldObj.rand.nextFloat() - 0.5F) * 8.0F);
				double randY = this.posY + 0.5F;
				double randZ = this.posZ + ((this.worldObj.rand.nextFloat() - 0.5F) * 8.0F);
				
				foo.setLocationAndAngles(randX, randY, randZ, this.rotationYaw, this.rotationPitch);
				
				foo.setIsTwin(true);
				foo.setTwinID(this.twinID);
				
				if(foo.getCanSpawnHere())
				{
					foo.setTwin(this);
					this.setTwin(foo);
					
					this.twinEntityID = this.getTwin().getEntityId();
					this.getTwin().twinEntityID = this.getEntityId();
					
					foo.entityInit();
					this.worldObj.spawnEntityInWorld(foo);
					twinSpawned = true;
				}
				else
					spawnAttempts++;
			}
			
			if(spawnAttempts > 5)
				this.setDead();
		}
	}
	
	public void onLivingUpdate()
	{
		if(!this.isTwinDead() && this.myTwinWeak == null)
			this.goodToGo = false;
		
		if(!this.goodToGo && !this.isTwinDead())
		{
			this.findTwin();
			this.goodToGo = true;
		}
		
		if(this.runTimer > 0)
		{
			this.runTimer--;
			
			if(this.runTimer < 1)
				this.cancelRetreat();
			
			if(this.isTwinDead())
			{
				this.runTimer = 0;
				this.cancelRetreat();
			}
		}
		
		if(this.getAttackTarget() != null)
			this.tasks.removeTask(this.followTwin);
		else if(this.isTwin() && !this.isRetreating && !this.isTwinDead())
			this.tasks.addTask(3, this.followTwin);
		
		if(!this.isTwinDead()/* && !this.worldObj.isRemote*/ && !(this.myTwin == null))
		{
			if(this.getAttackTarget() != null && this.myTwinWeak.get().getAttackTarget() == null)
				this.myTwinWeak.get().setAttackTarget(this.getAttackTarget());
		}
		
		if(this.getHealth() < (this.getMaxHealth() / 2) && !this.hasRetreated && !this.isRetreating && !this.isTwinDead())
		{
			this.runAway();
			
			if(!this.isTwinDead() && this.myTwin != null)
				this.myTwinWeak.get().cancelRetreat();
		}
		
		super.onLivingUpdate();
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		if(par1DamageSource == DamageSource.fall)
			return false;
		else if(par1DamageSource.isProjectile())
		{
			this.worldObj.playSoundAtEntity(this, "dungeonmobs:li_a", 1.0F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F + 1.0F);
			return false;
		}
		else
			return super.attackEntityFrom(par1DamageSource, par2);
	}	
	
	@Override
	protected void jump()
    {
        this.motionY = 0.5D;

        if (this.isPotionActive(Potion.jump))
            this.motionY += (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);

        if (this.isSprinting())
        {
            float var1 = this.rotationYaw * 0.017453292F;
            this.motionX -= (double)(MathHelper.sin(var1) * 0.2F);
            this.motionZ += (double)(MathHelper.cos(var1) * 0.2F);
        }

        this.isAirBorne = true;
        ForgeHooks.onLivingJump(this);
    }
	
	private void findTwin()
	{
		boolean skipFlag = false;
		
		Entity foo = this.worldObj.getEntityByID(this.twinEntityID);
		
		if(foo instanceof EntityLizalfos)
		{
			EntityLizalfos bar = (EntityLizalfos)foo;
			
			if(bar.getTwinID() == this.twinID && !bar.equals(this))
			{
				this.setTwin(bar);
				bar.setTwin(this);
				skipFlag = true;
			}
		}
		
		if(!this.worldObj.isRemote && !skipFlag)
		{
			List allEntities;
			
			try
			{
				allEntities = this.worldObj.getLoadedEntityList();
			}
			catch(NoSuchMethodError e)
			{
				allEntities = this.worldObj.getEntitiesWithinAABB(EntityLizalfos.class, this.boundingBox.expand(48.0F, 48.0F, 48.0F));
			}
	    	
	    	Iterator iter = allEntities.iterator();
	    	
	    	boolean foundFlag = false;
	    	
	    	while(iter.hasNext() && !foundFlag)
	    	{
	    		foo = (Entity)iter.next();
	    		
	    		if(foo instanceof EntityLizalfos && !foo.equals(this))
	    		{
	    			EntityLizalfos bar = (EntityLizalfos)foo;
	    			
	    			if(this.twinID == bar.getTwinID())
	    			{
	    				this.setTwin(bar);
	    				bar.setTwin(this);
	    				
	    				this.twinEntityID = this.getTwin().getEntityId();
	    				this.getTwin().twinEntityID = this.getEntityId();
	    				
	    				foundFlag = true;
	    			}
	    		}
	    	}
	    	
	    	if(this.isTwin())
	    		this.tasks.addTask(3, this.followTwin);
	    	
	    	allEntities = null;
		}
	}
	
	public void cancelRetreat()
	{
		if(this.isRetreating)
		{
			this.tasks.removeTask(this.retreat);
			this.tasks.removeTask(this.panic);
			
			this.tasks.addTask(2, this.attack);
			
			this.targetTasks.addTask(0, this.revenge);
			this.targetTasks.addTask(1, this.target);
			
			this.isRetreating = false;
			this.hasRetreated = true;
			this.runTimer = 0;
		}
	}
	
	public void runAway()
	{
		this.isRetreating = true;
		
		this.tasks.removeTask(this.followTwin);
		this.tasks.removeTask(this.attack);
		
		this.targetTasks.removeTask(this.revenge);
		this.targetTasks.removeTask(this.target);
		
		this.tasks.addTask(2, this.panic);
		this.tasks.addTask(3, this.retreat);
		this.runTimer = 480;
		
		this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 240, 1));
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		if(DungeonMobsHelper.getDifficulty(this.worldObj) > 0)
		{
			int var2 = this.rand.nextInt(DungeonMobsHelper.getDifficulty(this.worldObj) + 1);
		
			if(var2 == 0)
			{
				Item aSword = Items.iron_sword;
		        ItemStack mySword = new ItemStack(aSword);
		        EnchantmentHelper.addRandomEnchantment(this.worldObj.rand, mySword, 15);
		        
		        EntityItem itemEnt = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, mySword);  
	            this.worldObj.spawnEntityInWorld(itemEnt);
			}
		}
	}
	
	@Override
	protected void fall(float par1)
    {
        par1 = ForgeHooks.onLivingFall(this, par1);
        
        if (par1 <= 0)
            return;

        int var2 = MathHelper.ceiling_float_int(par1 - 3.0F);

        if (var2 > 0)
        {
            Block var3 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset), MathHelper.floor_double(this.posZ));

            if (var3 != Blocks.air)
            {
            	SoundType var4 = var3.stepSound;
            	
            	//this.playSound(var3.stepSound.soundName, 0.5F, (float)(0.75F * this.worldObj.rand.nextDouble()));
                this.playSound(var4.soundName, var4.getVolume() * 0.5F, var4.getPitch() * 0.75F);
            }
        }
    }
}
