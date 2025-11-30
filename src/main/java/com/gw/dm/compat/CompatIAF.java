package com.gw.dm.compat;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.*;
import com.github.alexthe666.iceandfire.message.MessageStoneStatue;
import com.github.alexthe666.iceandfire.misc.IafSoundRegistry;
import com.gw.dm.util.AudioHandler;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CompatIAF {
    public static void turnedIntoIAFStone(Entity attacker, Entity targetEntity, World worldIn){
        if (targetEntity != null) {
            if (targetEntity instanceof EntityLiving || targetEntity instanceof EntityPlayer) {
                if (targetEntity instanceof EntityPlayer) {
                    targetEntity.playSound(AudioHandler.entityCockatriceStone, 1, 1);
                    targetEntity.attackEntityFrom(IceAndFire.gorgon, Integer.MAX_VALUE);
                    EntityStoneStatue statue = new EntityStoneStatue(worldIn);
                    statue.setPositionAndRotation(targetEntity.posX, targetEntity.posY, targetEntity.posZ, targetEntity.rotationYaw, targetEntity.rotationPitch);
                    statue.smallArms = true;
                    if (!worldIn.isRemote) {
                        worldIn.spawnEntity(statue);
                    }
                } else {
                    StoneEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(targetEntity, StoneEntityProperties.class);
                    if (properties != null) {
                        properties.isStone = true;
                    }
                    IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageStoneStatue(targetEntity.getEntityId(), true));
                    if (targetEntity instanceof EntityDragonBase) {
                        EntityDragonBase dragon = (EntityDragonBase) targetEntity;
                        dragon.setFlying(false);
                        dragon.setHovering(false);
                    }
                    if (targetEntity instanceof EntityHippogryph) {
                        EntityHippogryph dragon = (EntityHippogryph) targetEntity;
                        dragon.setFlying(false);
                        dragon.setHovering(false);
                        dragon.airTarget = null;
                    }
                    if (targetEntity instanceof IDropArmor) {
                        ((IDropArmor) targetEntity).dropArmor();
                    }
                }

                if (targetEntity instanceof EntityGorgon) {
                    attacker.playSound(IafSoundRegistry.GORGON_PETRIFY, 1, 1);
                } else {
                    attacker.playSound(AudioHandler.entityCockatriceStone, 1, 1);
                }
                /*if (!(entity instanceof EntityPlayer && ((EntityPlayer) entity).isCreative())) {
                    stack.shrink(1);
                }*/
            }
        }
    }
}
