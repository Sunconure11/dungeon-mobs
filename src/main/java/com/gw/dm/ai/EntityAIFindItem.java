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

import com.gw.dm.entity.EntityRustMonster;
import com.gw.dm.util.RMFoodItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.*;

public class EntityAIFindItem extends EntityAIBase {
	
	private Entity targetEntity;
	private EntityRustMonster taskOwner;
	private World theWorld;
	private int targetDistance;
	private Set<RMFoodItem> wantedThings;
	private boolean livingFlag;
	private boolean gottaCompare;
	private EntityAINearestWantedThingSorter theNearestWantedThingSorter;
	
	
	public EntityAIFindItem(EntityRustMonster par1, int par2, Set<RMFoodItem> foods) {
		taskOwner = par1;
		theWorld = par1.world;
		setMutexBits(3);
		targetDistance = par2;
		wantedThings = foods;
		theNearestWantedThingSorter = new EntityAINearestWantedThingSorter(this, par1);
	}
	
	
	@Override
	public boolean shouldExecute() {
		List allThings = taskOwner.world.getEntitiesWithinAABB(Entity.class, taskOwner.getEntityBoundingBox().expand((double) targetDistance, 4.0D, (double) targetDistance));
		
		List wantLiving = new LinkedList<EntityLiving>();
		List wantItems = new LinkedList<Entity>();
		
		for (int i = 0; i < allThings.size(); i++) {
			if (allThings.get(i) instanceof EntityPlayer) {
				EntityPlayer aPlayer = (EntityPlayer) allThings.get(i);
				InventoryPlayer theStuff = aPlayer.inventory;
				
				int numSlots = theStuff.getSizeInventory();
				for (int j = 0; j < numSlots; j++) {
					ItemStack theItem = theStuff.getStackInSlot(j);
					//System.out.println("Checking Item: " + theItem.getItem());
					if (wantedThings.contains(new RMFoodItem(theItem.getItem(), theItem.getItemDamage()))) {
						wantLiving.add(aPlayer);
						break;
					}
				}
				
				for (ItemStack armorPiece : aPlayer.getArmorInventoryList()) {
					if (armorPiece != null) {
						if (wantedThings.contains(new RMFoodItem(armorPiece.getItem()))) {
							wantLiving.add(aPlayer);
							break;
						}
					}
				}
			}
			
			if (allThings.get(i) instanceof EntityMinecart) wantItems.add(allThings.get(i));
			
			if (allThings.get(i) instanceof EntityIronGolem) wantLiving.add(allThings.get(i));
			
			if (allThings.get(i) instanceof EntityItem) {
				EntityItem thisThing = (EntityItem) allThings.get(i);
				ItemStack theItem = thisThing.getItem();
				if (wantedThings.contains(new RMFoodItem(theItem.getItem(), theItem.getItemDamage()))) {
					wantItems.add(allThings.get(i));
				}
			}
		}
		
		Collections.sort(wantLiving, this.theNearestWantedThingSorter);
		Collections.sort(wantItems, this.theNearestWantedThingSorter);
		
		Iterator moarLiving = wantLiving.iterator();
		Iterator moarItems = wantItems.iterator();
		
		while (moarLiving.hasNext() || moarItems.hasNext()) {
			gottaCompare = false;
			livingFlag = false;
			
			if (moarLiving.hasNext() && moarItems.hasNext()) gottaCompare = true;
			
			if (!gottaCompare) {
				if (moarLiving.hasNext()) {
					targetEntity = (Entity) moarLiving.next();
					livingFlag = true;
				}
				else if (moarItems.hasNext()) {
					targetEntity = (Entity) moarItems.next();
				}
				else {
					System.out.println("[DUNGEON MOBS] ERROR: RM-AI-01 " + "- please report this error code to JaredBGreat.");
				}
			}
			else {
				Entity foo = (Entity) wantLiving.get(0);
				Entity bar = (Entity) wantItems.get(0);
				
				float distFoo = taskOwner.getDistance(foo);
				float distBar = taskOwner.getDistance(bar);
				
				if (distBar > distFoo) {
					targetEntity = (Entity) moarLiving.next();
					livingFlag = true;
				}
				else {
					targetEntity = (Entity) moarItems.next();
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		Entity var1 = taskOwner.getTarget();
		
		if (var1 == null) {
			return false;
		}
		else if (!var1.isEntityAlive()) {
			return false;
		}
		else if (taskOwner.getDistanceSq(var1) > (double) (targetDistance * targetDistance)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	public void startExecuting() {
		taskOwner.setTarget(targetEntity, livingFlag);
		
		super.startExecuting();
	}
	
	public void resetTask() {
		livingFlag = false;
		taskOwner.setTarget(null, false);
	}
	
	@Override
	public void updateTask() {
		if (livingFlag) {
			EntityLivingBase entity = (EntityLivingBase) taskOwner.getTarget();
			
			List playerEntities = taskOwner.world.playerEntities;
			Iterator iter = playerEntities.iterator();
			boolean hasStuff = false;
			
			while (iter.hasNext()) {
				EntityPlayer check = (EntityPlayer) iter.next();
				
				if (check.getEntityId() == entity.getEntityId()) {
					hasStuff = false;
					
					InventoryPlayer theStuff = check.inventory;
					
					int numSlots = theStuff.getSizeInventory();
					for (int j = 0; j < numSlots; j++) {
						ItemStack theItem = theStuff.getStackInSlot(j);
						//System.out.println("Checking Item: " + theItem.getItem());
						if (wantedThings.contains(new RMFoodItem(theItem.getItem(), theItem.getItemDamage()))) {
							hasStuff = true;
							break;
						}
					}
					
					for (ItemStack armorPiece : check.getArmorInventoryList()) {
						if (armorPiece != null) {
							if (wantedThings.contains(new RMFoodItem(armorPiece.getItem()))) {
								hasStuff = true;
								break;
							}
						}
					}
				}
			}
			if (!hasStuff && !(targetEntity instanceof EntityIronGolem)) resetTask();
		}
		super.updateTask();
	}
	
	
	public class EntityAINearestWantedThingSorter implements Comparator {
		final EntityAIFindItem parent;
		private Entity theEntity;
		
		public EntityAINearestWantedThingSorter(EntityAIFindItem par1EntityAINearestItem, Entity par2Entity) {
			parent = par1EntityAINearestItem;
			theEntity = par2Entity;
		}
		
		public int compareDistanceSq(Entity par1Entity, Entity par2Entity) {
			double var3 = theEntity.getDistanceSq(par1Entity);
			double var5 = theEntity.getDistanceSq(par2Entity);
			return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
		}
		
		public int compare(Object par1Obj, Object par2Obj) {
			return compareDistanceSq((Entity) par1Obj, (Entity) par2Obj);
		}
	}
}
