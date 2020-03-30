package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.DungeonMobsDamageSource;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.StringTokenizer;

import static com.gw.dm.util.ConfigHandler.cockatriceIg;

public class EntityCockatrice extends EntityDungeonMob {
	private static String mobName;
	private boolean ignoreHeight;
	private int stoneChance;
	private EntityPetrified stonedPlayer;
	private boolean incoming;
	private boolean waitTick;
	
	public EntityCockatrice(World par1World) {
		super(par1World);
		experienceValue = 15;
		ignoreHeight = false;
		setSize(1.2F, 1.5F);
		
		if (world != null) stoneChance = 6 * DungeonMobsHelper.getDifficulty(world);
		else stoneChance = 15;
		
		incoming = false;
		stonedPlayer = null;
		waitTick = false;
		
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackMelee(this, 1.0F, false));
		tasks.addTask(3, new EntityAIWander(this, 1.0F));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, null));
	}
	
	@Override
	public int getTotalArmorValue() {
		return 2;
	}
	
	@Override
	public int getTalkInterval() {
		return 80;
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		
		if (incoming && !world.isRemote) {
			if (waitTick) {
				waitTick = false;
			}
			else {
				world.spawnEntity(stonedPlayer);
				
				incoming = false;
				stonedPlayer = null;
			}
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityCockatriceAmbient;
	}
	
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var5;
		
		var3 = rand.nextInt(3);
		
		for (var5 = 0; var5 < var3; var5++) {
			dropItem(Items.FEATHER, 1);
		}
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityCockatriceHurt;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityCockatriceDeath;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity ent) {
		int moo = world.rand.nextInt(100) + 1;
		
		if ((moo < stoneChance) && (ent instanceof EntityPlayer) && (!world.isRemote) && (ent.ticksExisted > 100)) {
			EntityPlayer cow = (EntityPlayer) ent;
			
			if (!cow.capabilities.isCreativeMode) {
				super.playSound(AudioHandler.entityCockatriceStone, 1.0f, 1.0f);
				
				EntityPetrified foo = new EntityPetrified(world);
				EntityPlayer bar = (EntityPlayer) ent;
				
				foo.setLocationAndAngles(bar.posX, bar.posY, bar.posZ, bar.rotationYaw, bar.rotationPitch);
				
				foo.setStuff(bar);
				
				bar.attackEntityFrom(DungeonMobsDamageSource.PETRIFIED, 1024);
				
				incoming = true;
				waitTick = true;
				stonedPlayer = foo;
			}
		}
		else if (moo < stoneChance && !world.isRemote) {
			int a = (int) ent.posX;
			int b = (int) ent.getEntityBoundingBox().minY;
			int c = (int) ent.posZ;
			
			ent.setDead();
			super.playSound(AudioHandler.entityCockatriceStone, 1.0f, 1.0f);
			
			world.setBlockState(new BlockPos(a, b, c), Blocks.STONE.getDefaultState());
			world.setBlockState(new BlockPos(a, b + 1, c), Blocks.STONE.getDefaultState());
		}
		return super.attackEntityAsMob(ent);
	}
	
	@Override
	public boolean getCanSpawnHere() {
		if (cockatriceIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) return false;
		
		if (posY > 64.0D && !ignoreHeight) return false;
		
		return super.getCanSpawnHere();
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D * ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D * ConfigHandler.damagex + ConfigHandler.damageplus);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}
	
	public String getRegistryName() {
		if (mobName == null) fixNameIfNull();
		return mobName;
	}
	
	
	public void setRegistryName(String name) {
		mobName = (DungeonMobs.MODID + ":" + name).trim().toLowerCase();
	}
	
	
	private void fixNameIfNull() {
		StringTokenizer fixer = new StringTokenizer(this.getName().trim().toLowerCase(), ".");
		do {
			mobName = fixer.nextToken();
		}
		while (fixer.hasMoreTokens());
	}
}
