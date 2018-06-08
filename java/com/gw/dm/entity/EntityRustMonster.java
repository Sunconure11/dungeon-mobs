package com.gw.dm.entity;

import com.gw.dm.EntityDungeonMob;
import com.gw.dm.ai.EntityAIAttackAnythingWanted;
import com.gw.dm.ai.EntityAIFindItem;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityRustMonster extends EntityDungeonMob 
{
	private static int[] foodList = {14, 15, 41, 42, 101, 131, 256, 257, 258, 259, 265, 266, 283, 284, 285, 286, 
			292, 294, 302, 303, 304, 305, 306, 307, 308, 309, 314, 315, 316, 317, 322, 325, 
			330, 342, 343, 345, 347, 371, 66, 33, 27, 28, 267};
	
	private Entity myTarget;
	private boolean pissed;
	private boolean ignoreHeight;
	
	public EntityRustMonster(World par1World) 
	{
		super(par1World);
		this.setSize(1.4F, 1.4F);
		this.experienceValue = 20;
		this.ignoreHeight = false;
		this.pissed = false;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackAnythingWanted(this, Entity.class, 1.0F, false));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAIFindItem(this, 24, this.foodList.length, this.foodList));
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }
	
	protected boolean isAIEnabled()
	{
		return true;
	}
	
	public int getTotalArmorValue()
	{
		return 9;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		if(par1Entity.getClass() == EntityIronGolem.class)
			return 40;
		
		return 4;
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	@Override
	protected String getLivingSound()
	{
		return "dungeonmobs:rm_l";
	}
	
	@Override
	protected String getHurtSound()
	{
		return "dungeonmobs:rm_h";
	}
	
	@Override
	protected String getDeathSound()
	{
		return "dungeonmobs:rm_d";
	}
	
	/*
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.worldObj.playSoundAtEntity(this, null, 0.15F, 1.3F);
	}
	*/
	
	/*
	protected int getDropItemId()
	{
		return Items.coal.itemID;
	}
	*/
	
	protected void dropRareDrop(int par1)
	{
		// rare drops
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		if(par1)
			this.dropItem(Items.coal, this.rand.nextInt(2) + par2);
		
		/*
		int var3;
		int var4;
		
		var3 = this.rand.nextInt(2 + par2);

        for (var4 = 0; var4 < var3; var4++)
        {
        	if(DungeonMobsHelper.isMoCreaturesActive())
        		this.dropItem(DungeonMobsHelper.getItemIDByName("chitinDirt"), 1);
        	else
        		this.dropItem(Items.coal, 1);
        }
        */
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(!this.ignoreHeight)
		{
			for(int i = -24; i < 24; i++)
			{
				for(int j = -24; j < 24; j++)
				{
					for(int k = -24; k < 24; k++)
					{
						int checkX = (int)this.posX + i;
						int checkY = (int)this.posY + j;
						int checkZ = (int)this.posZ + k;
						
						if(checkY < 1)
							checkY = 1;
						
						if(this.worldObj.getBlock(checkX, checkY, checkZ) == Blocks.iron_ore)
							return super.getCanSpawnHere();
						
						if(this.worldObj.getBlock(checkX, checkY, checkZ) == Blocks.gold_ore)
							return super.getCanSpawnHere();
					}
				}
			}
			
			return false;
		}
		else
			return super.getCanSpawnHere();
	}
	
	@Override
	public int getTalkInterval()
    {
        return 120;
    }
	
	@Override
	protected void jump()
    {
        this.motionY = 0.4D;

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
	
	public void setTarget(Entity entity, boolean flag)
	{
		if(entity == null)
		{
			this.myTarget = null;
			this.setAttackTarget(null);
			this.pissed = false;
		}
		else
		{
			this.myTarget = entity;
		
			if(flag)
				this.setAttackTarget((EntityLivingBase)entity);
			else
				this.setAttackTarget(null);
		}
	}
	
	public Entity getTarget()
	{
		if(this.pissed)
			return this.getAttackTarget();
		
		if(this.getAttackTarget() == null)
			return this.myTarget;
		else
			return this.getAttackTarget();
	}
	
	 public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	 {
		 if(par1DamageSource.getEntity() instanceof EntityPlayer)
		 {
			 EntityPlayer foo = (EntityPlayer)par1DamageSource.getEntity();
			 ItemStack bar = foo.getHeldItem();
			 
			 if(bar != null)
			 {
				 for(int i = 0; i < foodList.length; i++)
				 {
					 if(Item.getIdFromItem(bar.getItem()) == foodList[i])
					 {
						 if(foo.getHeldItem().isItemStackDamageable())
						 {
							 int yar = this.getRNG().nextInt(11) + 15;
							 foo.getHeldItem().damageItem(yar, this);
						 }
						 else
						 {
							 int numToDestroy = this.getRNG().nextInt(6) + 1;
							 for(int m = 0; m < numToDestroy; m++)
							 {
								 if(foo.getHeldItem().stackSize == 1)
									 m = numToDestroy;
								 
								 foo.getHeldItem().stackSize--;
								 
							 }
						 }
					 }
				 }
			 }
		 }
		 
		 /*
		 if(!this.pissed && par1DamageSource.getEntity() instanceof EntityPlayer)
		 {
			 this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
			 this.setAttackTarget((EntityLiving)par1DamageSource.getEntity());
			 this.pissed = true;
		 }
		 */
			 
		 return super.attackEntityFrom(par1DamageSource, par2);
	 }
	 
	 public boolean attackEntityAsMob(Entity ent)
	 {
		 if(ent instanceof EntityPlayer)
		 {
			 EntityPlayer foo = (EntityPlayer)ent;
			 
			 for(int i = 0; i < 4; i++)
			 {
				 ItemStack armorPiece = foo.getCurrentArmor(i);
				 
				 if(armorPiece != null)
				 {
					 for(int j = 0; j < foodList.length; j++)
					 {
						 if(Item.getIdFromItem(armorPiece.getItem()) == foodList[j])
						 {
							 int lawlz = this.getRNG().nextInt(11) + 15;
							 armorPiece.damageItem(lawlz,  this);
						 }
					 }
				 }
			 }
		 }
		 /*
		 else
		 {
			 int foo = this.rand.nextInt(4) + 3;
			 
			 for(int i = 0; i < foo; i++)
			 {
				 float var1 = (float)ent.posX + (this.rand.nextFloat() * 2);
				 float var2 = (float)ent.posY + (this.rand.nextFloat() * 2) + 0.5F;
				 float var3 = (float)ent.posZ + (this.rand.nextFloat() * 2);
				 this.worldObj.spawnParticle("Smoke", var1, var2, var3, 0.0F, 0.0F, 0.0F);
			 }
		 }
		 */
		 
		 return super.attackEntityAsMob(ent);
	 }
	 
	 public void setFoodList(int[] list)
	 {
		 foodList = list;
	 }
	 
	 public static void appendToFoodList(int single)
	 {
		 int[] yar = {single};
		 
		 appendToFoodList(yar);
	 }
	 
	 public static void appendToFoodList(int[] list)
	 {
		 int newLength = foodList.length + list.length;
		 int newList[] = new int[newLength];
		 
		 for(int i = 0; i < foodList.length; i++)
		 {
			 newList[i] = foodList[i];
		 }
		 
		 for(int i = 0; i < list.length; i++)
		 {
			 newList[i + foodList.length] = list[i];
		 }
		 
		 foodList = null;
		 foodList = newList;
	 }
}
