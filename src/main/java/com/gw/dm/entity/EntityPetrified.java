package com.gw.dm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPetrified extends EntityLiving {

    public EntityPetrified(World par1World) {
        super(par1World);
        experienceValue = 0;
        isImmuneToFire = true;
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(100.0D);
    }


    @Override
    public void jump() {/*Do Nothing*/}


    @Override
    public int getTotalArmorValue() {
        return 0;
    }


    @Override
    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere();
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource src) {
        return SoundEvents.BLOCK_STONE_HIT;
    }


    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }


    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
		EntityItem itemEnt = new EntityItem(world, posX, posY, posZ, new ItemStack(Blocks.COBBLESTONE, this.getRNG().nextInt(2) + 1, 0));
        world.spawnEntity(itemEnt);
    }


    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }


    @Override
    protected boolean canDespawn() {
        return false;
    }


    @Override
    public void faceEntity(Entity par1Entity, float par2, float par3) {/*Do Nothing*/}


    public boolean isPotionApplicable(PotionEffect par1PotionEffect) {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource src, float amount) {
        if (src.getTrueSource() instanceof EntityPlayer) {
            if (((EntityPlayer) src.getTrueSource())
                    .getHeldItemMainhand()
                    .getItem()
                    .canHarvestBlock(Blocks.STONE.getDefaultState())) {
                return super.attackEntityFrom(src, amount);
            }
        }
        return super.attackEntityFrom(src, amount / 10);
    }
}
