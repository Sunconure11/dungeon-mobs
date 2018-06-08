package com.gw.dm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPetrified extends EntityLiving
{
	public ItemStack[] stuff;
	
	public EntityPetrified(World par1World) 
	{
		super(par1World);
		this.experienceValue = 0;
		this.stuff = null;
		this.isImmuneToFire = true;
		
		//this.tasks.addTask(0, new EntityAILookIdle(this));
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
		return true;
	}

	public void jump()
	{
		;
	}
	
	public int getTotalArmorValue()
	{
		return 0;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 0;
	}
	
	public boolean getCanSpawnHere()
	{
		return super.getCanSpawnHere();
	}
	
	protected String getHurtSound()
	{
		return "dig.stone";
	}
	
	protected String getLivingSound()
	{
		return null;
	}
	
	protected String getDyingSound()
	{
		return "dig.stone";
	}
	
	public void setStuff(EntityPlayer foo)
	{
		this.stuff = new ItemStack[40];
		
		for(int i = 0; i < 40; i++)
		{
			if(foo.inventory.getStackInSlot(i) != null)
				this.stuff[i] = foo.inventory.getStackInSlot(i);
		}
		
		foo.inventory = new InventoryPlayer(foo);
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		if(this.stuff != null)
		{
			for(int i = 0; i < 40; i++)
			{
				if(this.stuff[i] != null)
				{
					EntityItem itemEnt = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, this.stuff[i]);  
		            this.worldObj.spawnEntityInWorld(itemEnt);
				}
			}
		}
	}
	
	@Override
	public boolean canBreatheUnderwater()
    {
        return true;
    }
	
	protected boolean canDespawn()
    {
        return false;
    }
	
	public void faceEntity(Entity par1Entity, float par2, float par3)
    {
		;
    }
	
	private float updateRotation(float par1, float par2, float par3)
    {
		return 0.0F;
    }
	
	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
    {
		return false;
    }
	
	public void knockBack(Entity par1Entity, int par2, double par3, double par5)
	{
		;
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		return super.attackEntityFrom(par1DamageSource, par2);
	}	
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.stuff.length; ++i)
        {
            if (this.stuff[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.stuff[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        
        par1NBTTagCompound.setTag("Items", nbttaglist);
        
        /*
        for(int i = 0; i < 40; i++)
        {
        	if(this.stuff[i] != null)
        		par1NBTTagCompound.setTag("item[ " + i + "]", this.stuff[i].writeToNBT(par1NBTTagCompound));
        }
        */
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
        this.stuff = new ItemStack[40];


        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("slot") & 255;

            if (j >= 0 && j < this.stuff.length)
            {
                this.stuff[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        /*
        for(int i = 0; i < 40; i++)
        {
        	NBTTagCompound foo = par1NBTTagCompound.getCompoundTag("item[" + i + "]"); 
        	
        	if(foo != null)
        	{
        		ItemStack bar = ItemStack.loadItemStackFromNBT(foo);
	        	
	        	if(bar != null)
	        		this.stuff[i] = bar;
        	}
        }
        */
    }
	
	public void setAngles(float par1, float par2)
    {
		;
    }
}
