package com.gw.dm.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityIllithid extends EntityDungeonMob
{
	private boolean ignoreHeight;
	private int tentacleCounter;
	public boolean isGrappling;
	
	private EntityLivingBase stunnedEntity;
	private int stunDuration;
	
	private int mindBlastTicks;
	
	private List dominatedEntities;
	//private List entityOwners;
	private Map ownerIDs;
	
	private EntityAIAttackOnCollide grapple = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0F, false);
	
	public EntityIllithid(World par1World) 
	{
		super(par1World);
		this.ignoreHeight = false;
		
		this.experienceValue = 50;
		this.tentacleCounter = 0;
		
		this.setSize(1.1F, 2.9F);
		
		this.stunnedEntity = null;
		this.stunDuration = 0;
		this.mindBlastTicks = 0;
		
		this.dominatedEntities = new LinkedList<EntityTameable>();
		//this.entityOwners      = new LinkedList<String>();
		this.ownerIDs          = new HashMap<EntityTameable, EntityLivingBase>();
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityIllithid.class, 5.0F, 0.02F));
        this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityRakshasa.class, 5.0F, 0.02F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }

	protected boolean isAIEnabled()
	{
		return true;
	}
	
	public int getTotalArmorValue()
	{
		return 5;
	}
		
	public int getAttackStrength(Entity par1Entity)
	{
		if(this.tentacleCounter < 4)
			return 6;
		else
		{
			this.worldObj.playSoundAtEntity(this.getAttackTarget(), "dungeonmobs:i_e", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			this.tentacleCounter = 0;
			return 100;
		}
	}
	
	public int getTalkInterval()
    {
        return 180;
    }
	
	protected String getLivingSound()
	{
		return "dungeonmobs:i_l";
	}
	
	protected String getHurtSound()
	{
		return "mob.zombie.hurt";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:i_d";
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		
		var3 = this.rand.nextInt(4);

        for (var4 = 0; var4 < var3; var4++)
        {
            this.dropItem(Items.slime_ball, 1);
        }
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 32.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	public void onLivingUpdate()
	{
		if(this.getAttackTarget() == null)
		{
			Iterator cow = this.dominatedEntities.iterator();
			
			while(cow.hasNext())
			{
				EntityTameable winner = (EntityTameable)cow.next();
				winner.setAttackTarget(this.getAttackTarget());
			}
		}
		
		this.mindBlastTicks++;
		
		if(this.mindBlastTicks >= (60 - ((DungeonMobsHelper.getDifficulty(this.worldObj) - 1) * 20))) 
		{
			List tameables = this.worldObj.getEntitiesWithinAABB(EntityTameable.class, this.boundingBox.expand(24.0F, 24.0F, 24.0F));
			List want = new LinkedList<EntityTameable>();
			
			for(int i = 0; i < tameables.size(); i++)
			{
				EntityTameable foo = (EntityTameable)tameables.get(i);
				
				if(foo.isTamed() && this.canEntityBeSeen(foo))
				{
					if(foo.getOwner() != null)
						want.add(foo);
				}
			}
			
			Iterator iter = want.iterator();
			
			if(iter.hasNext())
			{
				EntityTameable target = (EntityTameable)iter.next();
				
				if(this.getAttackTarget() != null && this.getAttackTarget().equals((EntityLivingBase)target))
					this.setAttackTarget(null);
				
				int ownerID = target.getOwner().getEntityId();
				
				EntityLivingBase moo = target.getOwner();
				
				target.setTamed(false);
				
				target.setRevengeTarget(this.getAttackTarget());
				
				if(this.getAttackTarget() == null)
					target.setAttackTarget((EntityLivingBase)this.worldObj.getEntityByID(ownerID));
				else
					target.setAttackTarget(this.getAttackTarget());
				
				this.dominatedEntities.add(target);
				//this.entityOwners.add(moo);
				this.ownerIDs.put(target, moo);
			}
			else
			{
				if(this.getAttackTarget() != null)
				{
					if(!this.getAttackTarget().isPotionActive(Potion.digSlowdown))
						this.getAttackTarget().addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 240, 3));
					
					if(!this.getAttackTarget().isPotionActive(Potion.moveSlowdown))
						this.getAttackTarget().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 240, 3));
					
					if(this.getAttackTarget() instanceof EntityPlayer)
						this.stunnedEntity = this.getAttackTarget();
				}
			}
			
			this.mindBlastTicks = 0;
		}
		
		if(this.getAttackTarget() == null)
			this.stunnedEntity = null;
		
		if(this.stunnedEntity != null)
		{
			this.tasks.addTask(2, this.grapple);
			
			if(!this.stunnedEntity.isPotionActive(Potion.digSlowdown) && !this.stunnedEntity.isPotionActive(Potion.moveSlowdown))
			{
				this.tasks.removeTask(this.grapple);
				this.stunnedEntity = null;
			}
			else if(this.stunnedEntity instanceof EntityPlayer)
			{
				EntityPlayer foo = (EntityPlayer)this.stunnedEntity;
				
				if(this.boundingBox.expand(2.0F, 2.0F, 2.0F).intersectsWith(foo.boundingBox))
				{
					/*
					if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
						this.stunTarget(FMLClientHandler.instance().getClient().thePlayer);
					else
						DungeonMobsHelper.sendStunPacket(foo);
					*/
					
					this.isGrappling = true;
					this.stunDuration++;
					
					ItemStack hat = foo.getCurrentArmor(3);
					
					/*
					if(hat != null)
						System.out.println(hat.getDisplayName());
					*/
					
					if(this.stunDuration % 30 == 0 && hat == null)
					{
						//System.out.println("Tentacle counter: " + this.tentacleCounter);
						
						this.tentacleCounter++;
						
						if(this.tentacleCounter > 4)
							this.tentacleCounter = 4;
					}
				}
				else
				{
					this.tentacleCounter = 0;
					this.stunDuration = 0;
					this.isGrappling = false;
				}
				
				if(this.tentacleCounter == 4)
					this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(100.0D);
				else
					this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
			}
		}
		
		super.onLivingUpdate();
	}
	
	public boolean attackEntityAsMob(Entity ent)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer foo = (EntityPlayer)ent;

			if(!foo.capabilities.isCreativeMode)
			{
				if(foo.inventory.armorItemInSlot(3) != null)
				{
					if(this.worldObj.rand.nextInt(4 - DungeonMobsHelper.getDifficulty(this.worldObj)) == 0)
					{
						this.worldObj.playSoundAtEntity(this.getAttackTarget(), "dungeonmobs:i_p", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
						EntityItem bork = ent.entityDropItem(foo.inventory.armorItemInSlot(3), 1.5F);
						//bork.delayBeforeCanPickup = 60;
						ent.setCurrentItemOrArmor(4, null);
					}
				}
			}
		}
		
		return super.attackEntityAsMob(ent);
	}
	
	public void stunTarget(EntityPlayer bar)
	{
		bar.motionX *= 0.0D;
		bar.motionY *= 0.0D;
		bar.motionZ *= 0.0D;
	}
	
	public void onDeath(DamageSource par1DamageSource)
	{
		Iterator iter1 = this.dominatedEntities.iterator();
		//Iterator iter2 = this.entityOwners.iterator();
		
		while(iter1.hasNext())
		{
			EntityTameable foo = (EntityTameable)iter1.next();
			//String bar = (String)iter2.next();
			
			
			//foo.setOwner(bar);
			
			EntityLivingBase bar = (EntityLivingBase)ownerIDs.get(foo);
			
			foo.func_152115_b(bar.getUniqueID().toString());
			//foo.setOwner((String)ownerIDs.get(foo));
			foo.setTamed(true);
			
			foo.setAttackTarget(null);
		}
		
		super.onDeath(par1DamageSource);
	}
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("TentacleCounter", this.tentacleCounter);
        par1NBTTagCompound.setInteger("MindBlast", this.mindBlastTicks);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.tentacleCounter  = par1NBTTagCompound.getInteger("TentacleCounter");
        this.mindBlastTicks   = par1NBTTagCompound.getInteger("MindBlast");
    }
}
