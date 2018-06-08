package com.gw.dm.ai;

/* 
 * EntityAIFindItem
 * Targeting AI. Causes task owner to look for specific item IDs.
 * Also causes the owner to target a player if they have the specific item IDs in their inventory.
 * Also causes the owner to target minecarts. 'cause they're metal...
 * Also causes the owner to attack iron golems. Again, 'cause they're metal...
 * 
 * (c) GnomeWorks 2013
 * Do not redistribute without permission.
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.gw.dm.entity.EntityRustMonster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityAIFindItem extends EntityAIBase
{
	private Entity targetEntity;
	private EntityRustMonster taskOwner;
	private World theWorld;
	private int targetDistance;
	private int listSize;
	private int[] wantedThings;
	private boolean livingFlag;
	private boolean gottaCompare;
	private EntityAINearestWantedThingSorter theNearestWantedThingSorter;
	
	public EntityAIFindItem(EntityRustMonster par1, int par2, int par3, int[] par4)
	{
		this.taskOwner = par1;
		this.theWorld = par1.worldObj;
		this.setMutexBits(3);
		this.targetDistance = par2;
		this.listSize = par3;
		this.wantedThings = par4;
		this.theNearestWantedThingSorter = new EntityAINearestWantedThingSorter(this, par1);
	}

	@Override
	public boolean shouldExecute() 
	{
		List allThings = this.taskOwner.worldObj.getEntitiesWithinAABB(Entity.class, this.taskOwner.boundingBox.expand((double)this.targetDistance, 4.0D, (double)this.targetDistance));
		
		List wantLiving = new LinkedList<EntityLiving>();
		List wantItems = new LinkedList<Entity>();
		
		for(int i = 0; i < allThings.size(); i++)
		{
			if(allThings.get(i) instanceof EntityPlayer)
			{
				EntityPlayer aPlayer = (EntityPlayer)allThings.get(i);
				InventoryPlayer theStuff = aPlayer.inventory;
				
				for(int j = 0; j < this.listSize; j++)
				{
					if(theStuff.hasItem(Item.getItemById(wantedThings[j])))
					{
						wantLiving.add(aPlayer);
						break;
					}
				}
				
				for(int j = 0; j < 4; j++)
				{
					ItemStack armorPiece = aPlayer.getCurrentArmor(j);
					
					if(armorPiece != null)
					{
						for(int k = 0; k < this.listSize; k++)
						{
							if(Item.getIdFromItem(armorPiece.getItem()) == wantedThings[k])
							{
								wantLiving.add(aPlayer);
								j = 4;
								break;
							}
						}
					}
				}
			}
			
			if(allThings.get(i) instanceof EntityMinecart)
				wantItems.add(allThings.get(i));
			
			if(allThings.get(i) instanceof EntityIronGolem)
				wantLiving.add(allThings.get(i));
			
			if(allThings.get(i) instanceof EntityItem)
			{
				EntityItem thisThing = (EntityItem)allThings.get(i);
				ItemStack whatItIs = thisThing.getEntityItem();
				
				for(int j = 0; j < this.listSize; j++)
				{
					if(Item.getIdFromItem(whatItIs.getItem()) == wantedThings[j])
						wantItems.add(allThings.get(i));
				}
			}
		}
		
		Collections.sort(wantLiving, this.theNearestWantedThingSorter);
		Collections.sort(wantItems, this.theNearestWantedThingSorter);
		
		Iterator moarLiving = wantLiving.iterator();
		Iterator moarItems = wantItems.iterator();
		
		while(moarLiving.hasNext() || moarItems.hasNext())
		{
			gottaCompare = false;
			livingFlag = false;
			
			if(moarLiving.hasNext() && moarItems.hasNext())
				gottaCompare = true;
			
			if(!gottaCompare)
			{
				if(moarLiving.hasNext())
				{
					this.targetEntity = (Entity)moarLiving.next();
					this.livingFlag = true;
				}
				else if(moarItems.hasNext())
					this.targetEntity = (Entity)moarItems.next();
				else
					System.out.println("[DUNGEON MOBS] ERROR: RM-AI-01 - please report this error code to GnomeWorks.");
			}
			else
			{
				Entity foo = (Entity)wantLiving.get(0);
				Entity bar = (Entity)wantItems.get(0);
				
				float distFoo = this.taskOwner.getDistanceToEntity(foo);
				float distBar = this.taskOwner.getDistanceToEntity(bar);
				
				if(distBar > distFoo)
				{
					this.targetEntity = (Entity)moarLiving.next();
					this.livingFlag = true;
				}
				else
					this.targetEntity = (Entity)moarItems.next();
			}
			
			return true;
		}
		
		return false;
	}
	
	public void startExecuting()
	{
		this.taskOwner.setTarget(this.targetEntity, livingFlag);
		
		super.startExecuting();
	}
	
	public void resetTask()
	{
		this.livingFlag = false;
		this.taskOwner.setTarget(null, false);
	}
	
	public boolean continueExecuting()
    {
        Entity var1 = this.taskOwner.getTarget();

        if (var1 == null)
            return false;
        else if (!var1.isEntityAlive())
            return false;
        else if (this.taskOwner.getDistanceSqToEntity(var1) > (double)(this.targetDistance * this.targetDistance))
            return false;
        else
            return true;
    }
	
	public void updateTask()
	{
		if(this.livingFlag)
		{
			EntityLivingBase foo = (EntityLivingBase)this.taskOwner.getTarget();
			
			List playerEntities = this.taskOwner.worldObj.playerEntities;
			Iterator bork = playerEntities.iterator();
			boolean hasStuff = false;
			
			while(bork.hasNext())
			{
				EntityPlayer check = (EntityPlayer)bork.next();
				
				if(check.getEntityId() == foo.getEntityId())
				{
					hasStuff = false;
					
					InventoryPlayer theStuff = check.inventory;
					
					for(int j = 0; j < this.listSize; j++)
					{
						if(theStuff.hasItem(Item.getItemById(wantedThings[j])))
						{
							hasStuff = true;
							break;
						}
					}
					
					for(int j = 0; j < 4; j++)
					{
						ItemStack armorPiece = check.getCurrentArmor(j);
						
						if(armorPiece != null)
						{
							for(int k = 0; k < this.listSize; k++)
							{
								if(Item.getIdFromItem(armorPiece.getItem()) == wantedThings[k])
								{
									hasStuff = true;
									j = 4;
									break;
								}
							}
						}
					}
				}
			}
			
			if(!hasStuff && !(this.targetEntity instanceof EntityIronGolem))
				this.resetTask();
		}
		
		super.updateTask();
	}
	
	public class EntityAINearestWantedThingSorter implements Comparator
	{
	    private Entity theEntity;

	    final EntityAIFindItem parent;

	    public EntityAINearestWantedThingSorter(EntityAIFindItem par1EntityAINearestItem, Entity par2Entity)
	    {
	        this.parent = par1EntityAINearestItem;
	        this.theEntity = par2Entity;
	    }

	    public int compareDistanceSq(Entity par1Entity, Entity par2Entity)
	    {
	        double var3 = this.theEntity.getDistanceSqToEntity(par1Entity);
	        double var5 = this.theEntity.getDistanceSqToEntity(par2Entity);
	        return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
	    }

	    public int compare(Object par1Obj, Object par2Obj)
	    {
	        return this.compareDistanceSq((Entity)par1Obj, (Entity)par2Obj);
	    }
	}
}
