package com.gw.dm.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.projectile.EntityMagicMissile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityRakshasa extends EntityDungeonMob implements IRangedAttackMob
{
	public boolean ignoreHeight;
	private int castingTimer;
	private int hasteTimer;
	private int illusionTimer;
	private int imageTimer;
	public String currentName;
	public String currentSkin;
	public boolean hasIllusion;
	
	private EntityAIArrowAttack rangeAttack = new EntityAIArrowAttack(this, 0.3F, 5, 40, 24.0F);
	private EntityAIAttackOnCollide meleeAttack = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3F, false);
	
	private List myImages;
	
	public EntityRakshasa(World par1World) 
	{
		super(par1World);
		
		this.ignoreHeight = false;
		this.experienceValue = 60;
		
		this.castingTimer = 0;
		this.hasteTimer = 0;
		this.illusionTimer = 0;
		this.imageTimer = 0;
		this.currentName = "Rakshasa";
		this.currentSkin = "/mods/dungeonMobs/Rakshasa.png";
		this.hasIllusion = false;
		this.myImages = new LinkedList<EntityRakshasaImage>();
		
		//this.texture = "/mods/dungeonMobs/Rakshasa.png";
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, rangeAttack);
		this.tasks.addTask(3, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityRakshasa.class, 5.0F, 0.02F));
        this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityIllithid.class, 5.0F, 0.02F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(42.0D);
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
		return 8;
	}
	
	public int getTalkInterval()
    {
        return 80;
    }
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 6;
	}
	
	protected String getLivingSound()
	{
		return "dungeonmobs:ra_l";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:ra_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:ra_h";
	}
	
	public String getTexture()
	{
		return this.currentSkin;
	}
	
	public String getEntityName()
	{
		return this.currentName;
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var2 = 100;
		
		if(DungeonMobsHelper.getDifficulty(this.worldObj) > 0)
		{
			var2 = this.rand.nextInt(DungeonMobsHelper.getDifficulty(this.worldObj));
		
			if(var2 == 0)
			{
				Item aBook = Items.book;
		        ItemStack myBook = new ItemStack(aBook);
		        EnchantmentHelper.addRandomEnchantment(this.worldObj.rand, myBook, 30);
		        
		        EntityItem itemEnt = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, myBook);  
	            this.worldObj.spawnEntityInWorld(itemEnt);
			}
		}
	}

	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 38.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float lol) 
	{
		double var11 = var1.posX + var1.motionX - this.posX;
        double var13 = var1.boundingBox.minY + var1.motionY + var1.height - this.boundingBox.maxY;
        double var15 = var1.posZ + var1.motionZ  - this.posZ;
		
		EntityMagicMissile var2 = new EntityMagicMissile(this.worldObj, (EntityLiving)this, var11, var13, var15, var1);
		this.worldObj.spawnEntityInWorld(var2);
	}
	
	public void onLivingUpdate()
	{
		this.castingTimer++;
		
		/*
		if(this.illusionTimer > 0)
		{
			this.illusionTimer--;
			
			if(this.illusionTimer == 0)
				this.resetIllusion();
		}
		*/
		
		if(this.imageTimer > 0)
		{
			this.imageTimer--;
			
			if(this.imageTimer % 40 == 0)
			{
				this.switchPosition();
			}
		}
		
		if(this.hasteTimer > 0)
		{
			if(this.hasteTimer % 4 == 0)
				this.castingTimer++;
			
			this.hasteTimer--;
		}
		
		if(this.castingTimer >= 120 && !this.worldObj.isRemote)
		{
			this.worldObj.playSoundAtEntity(this, "dungeonmobs:ra_el", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			
			boolean castFlag = false;
			int castSelect;
			int castAttempts = 0;
			
			do
			{
				castSelect = this.worldObj.rand.nextInt(5);
				
				switch(castSelect)
				{
					case 0:
						if(!this.isPotionActive(Potion.invisibility))
						{
							this.addPotionEffect(new PotionEffect(Potion.invisibility.id, 240, 1));
							castFlag = true;
						}
						else
							castAttempts++;
						
						break;
					case 1:
						if(!this.isPotionActive(Potion.resistance))
						{
							this.addPotionEffect(new PotionEffect(Potion.resistance.id, 240, 3));
							castFlag = true;
						}
						else
							castAttempts++;
						
						break;
					case 2:
						if(!this.isPotionActive(Potion.moveSpeed) && this.hasteTimer < 30)
						{
							this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 240, 2));
							this.hasteTimer = 240;
							castFlag = true;
						}
						else
							castAttempts++;
						
						break;
					case 3:
						if(this.imageTimer < 30)
						{
							this.createImages();
							castFlag = true;
						}
						else
							castAttempts++;
						
						break;
					case 4:
						if(this.getAttackTarget() != null)
						{
							if(this.canEntityBeSeen(this.getAttackTarget()))
							{
								this.attackEntityWithRangedAttack(this.getAttackTarget(), 0F);
								castFlag = true;
							}
							else
								castAttempts++;
						}
						else
							castAttempts++;
						
						break;
					/*
					case 5: // dummied out for now...
						if(this.illusionTimer == 0)
						{
							this.makeIllusion();
							castFlag = true;
						}
						else
							castAttempts++;
						
						break;
					*/
					default:
						castAttempts++;
						break;
				}
			} while(!castFlag && castAttempts < 6);
			
			this.castingTimer -= 120;
		}
		
		super.onLivingUpdate();
	}
	
	private void createImages()
	{
		this.imageTimer = 240;
		
		int imageAttempts = 0;
		
		int imageCount = this.worldObj.rand.nextInt(3) + DungeonMobsHelper.getDifficulty(this.worldObj);
		
		for(int i = 0; i < imageCount && imageAttempts < 3 + imageCount; i++)
		{
			double imageX = this.posX + (this.worldObj.rand.nextFloat() * 4.0F);
			double imageY = this.posY;
			double imageZ = this.posZ + (this.worldObj.rand.nextFloat() * 4.0F);
			
			EntityRakshasaImage bar = new EntityRakshasaImage(this.worldObj, (EntityLivingBase)this.getAttackTarget(), this);
			bar.setLocationAndAngles(imageX, imageY, imageZ, this.rotationYaw, this.rotationPitch);
			
			if(bar.getCanSpawnHere() && !this.worldObj.isRemote)
			{
				if(!this.worldObj.isRemote)
				{
					this.worldObj.spawnEntityInWorld(bar);
					this.myImages.add(bar);
					if(this.getAttackTarget() != null)
						bar.setAttackTarget(this.getAttackTarget());
				}
				else
				{
					imageCount--;
					imageAttempts++;
				}
			}
			else
			{
				imageCount--;
				imageAttempts++;
			}
		}
	}
	
	private void switchPosition()
	{
		if(!this.worldObj.isRemote)
		{
			if(!myImages.isEmpty())
			{
				EntityRakshasaImage foo = (EntityRakshasaImage)this.myImages.get(this.worldObj.rand.nextInt(this.myImages.size()));
				
				double prevX = this.posX;
				double prevY = this.posY;
				double prevZ = this.posZ;
				
				this.posX = foo.posX;
				this.posY = foo.posY;
				this.posZ = foo.posZ;
				
				foo.posX = prevX;
				foo.posY = prevY;
				foo.posZ = prevZ;
			}
		}
	}
	
	public void destroyImage(EntityRakshasaImage foo)
	{
		Iterator iter = myImages.iterator();
		
		while(iter.hasNext())
		{
			if(foo.equals((EntityRakshasaImage)iter.next()))
			{
				this.myImages.remove(foo);
				break;
			}
		}
	}
	
	/*
	public void setDead()
	{
		Iterator iter = myImages.iterator();
		
		while(iter.hasNext())
		{
			EntityRakshasaImage foo = (EntityRakshasaImage)iter.next();
			
			if(!foo.isDead)
			{
				try
				{
					foo.setDead();
				}
				catch(ConcurrentModificationException e)
				{
					;
				}
			}
		}
		
		super.setDead();
	}
	*/
	
	/*
	private void makeIllusion()
	{
		if(!this.worldObj.isRemote)
		{
			List wantList = this.worldObj.playerEntities;
			
			if(!wantList.isEmpty())
			{
				EntityPlayer foo = (EntityPlayer)wantList.get(this.worldObj.rand.nextInt(wantList.size()));
				
				this.currentName = foo.getEntityName();
				
				this.tasks.removeTask(this.rangeAttack);
				this.tasks.addTask(2, this.meleeAttack);
				
				this.illusionTimer = 240;
				
				this.hasIllusion = true;
			}
		}
	}
	
	private void resetIllusion()
	{
		this.currentSkin = "/mods/dungeonMobs/Rakshasa.png";
		this.currentName = "Rakshasa";
		this.hasIllusion = false;
		
		this.tasks.removeTask(this.meleeAttack);
		this.tasks.addTask(2, this.rangeAttack);
	}
	*/
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("CastTimer", this.castingTimer);
        par1NBTTagCompound.setInteger("HasteTimer", this.hasteTimer);
        par1NBTTagCompound.setInteger("ImageTimer", this.imageTimer);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.castingTimer  = par1NBTTagCompound.getInteger("CastTimer");
        this.hasteTimer   = par1NBTTagCompound.getInteger("HasteTimer");
        this.imageTimer   = par1NBTTagCompound.getInteger("ImageTimer");
    }
}
