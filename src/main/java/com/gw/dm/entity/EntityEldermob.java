package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonFlying;
import com.gw.dm.ai.AIEldermobMove;
import com.gw.dm.projectile.EntityCloudGenerator;
import com.gw.dm.projectile.EntityEldermobBall;
import com.gw.dm.projectile.EntityFireCloudGenerator;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import static com.gw.dm.util.ConfigHandler.outerThingEI;
import static com.gw.dm.util.ConfigHandler.outerThingIg;
import com.gw.dm.util.DungeonMobsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;

public class EntityEldermob extends EntityDungeonFlying
        implements IMob, IRangedAttackMob, IBeMagicMob {
    private static final int SD = 1024 * 1024;

    private static final String mobName = DungeonMobs.MODID + ":dmeldermob";

    private static final PotionEffect poison = new PotionEffect(MobEffects.POISON, 300, 0);

    public EntityEldermob(World worldIn) {
        super(worldIn);
        moveHelper = new AIEldermobMove.EldermobMover(this);
        setSize(2.0f, 2.0f);
        experienceValue = 65;
        isImmuneToFire = true;
    }


    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        if (!world.isRemote) {
            double dx = target.posX - posX;
            double dy = (target.posY + (target.height / 2)) - (posY + (height / 2));
            double dz = target.posZ - posZ;
            EntityThrowable ball;
            int which;

            if (ConfigHandler.fireClouds) {
                which = rand.nextInt(3);
            } else {
                which = rand.nextInt(2);
            }
            if ((which == 2) && (target.isImmuneToFire())) {
                which--;
            }
            if ((which == 1) && target.isEntityUndead()) {
                which--;
            }
            switch (which) {
                case 0:
                    ball = new EntityEldermobBall(world, this);
                    ball.shoot(dx, dy, dz, 1.2f, 0.0f);
                    world.spawnEntity(ball);
                    break;
                case 1:
                    dy = target.posY - posY - (height / 2);
                    ball = new EntityCloudGenerator(world, this, poison);
                    ball.shoot(dx, dy, dz, 1.2f, 0.0f);
                    world.spawnEntity(ball);
                    break;
                case 2:
                    ball = new EntityFireCloudGenerator(world, this);
                    ball.shoot(dx, dy, dz, 1.2f, 0.0f);
                    world.spawnEntity(ball);
                    break;
                default:
                    ball = new EntityEldermobBall(world, this);
                    ball.shoot(dx, dy, dz, 1.2f, 0.0f);
                    world.spawnEntity(ball);
                    break;
            }
        }
    }


    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }


    @Override
    public boolean isInLava() {
        return false;
    }


    @Override
    public boolean isBurning() {
        return false;
    }


    @Override
    public boolean canBeHitWithPotion() {
        return false;
    }


    @Override
    protected boolean canDespawn() {
        return true;
    }


    @Override
    protected SoundEvent getAmbientSound() {
        if (rand.nextBoolean()) {
            return AudioHandler.entityEldermobA1;
        } else {
            return AudioHandler.entityEldermobA2;
        }
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource src) {
        return AudioHandler.entityEldermobA3;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AudioHandler.entityEldermobA3;
    }


    public int getTalkInterval() {
        return 100;
    }


    protected float getSoundVolume() {
        return 2.5f;
    }


    @Override
    public void onUpdate() {
        if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            setDead();
        }
        if ((getAttackTarget() != null) && getAttackTarget().isDead) {
            setAttackTarget(null);
        }
        super.onUpdate();
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0d);
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(66.0d
                * ConfigHandler.healthx);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0d);
        getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(1.0d);
    }


    @Override
    protected void initEntityAI() {
        // TODO: Movement and attacks
        tasks.addTask(2, new EntityAIAttackRanged(this, 0.3F, 10, 50, 40.0F));
        tasks.addTask(2, new AIEldermobMove(this, 1.0).startingMove());
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(6, new EntityAILookIdle(this));
        targetTasks.addTask(3, new EntityAIFindEntityNearestPlayer(this));
    }


    @Override
    public void setSwingingArms(boolean swingingArms) {/*The tenticals always writhe...*/}


    @Override
    public boolean getCanSpawnHere() {
        if (world.provider instanceof WorldProviderEnd
                && deepEnoughInEnd() && (rand.nextInt(24) == 0)) {
            return super.getCanSpawnHere();
        } else if (outerThingIg && !(world.provider instanceof WorldProviderEnd)) {
            return super.getCanSpawnHere();
        }
        if (DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
            return super.getCanSpawnHere();
        }
        if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
            return false;
        }
        if (posY > 32.0D && !ignoreHeight) {
            return false;
        }
        return super.getCanSpawnHere();
    }


    /**
     * This is too keep them from spawning on the main end island,
     * i.e., from interfering with the dragon fight.
     *
     * @return
     */
    private boolean deepEnoughInEnd() {
        return outerThingEI || (((posX * posX) + (posZ * posZ)) > SD);
    }


    @Override
    protected void damageEntity(DamageSource source, float damageAmount) {
        if (source.isFireDamage() || source.isMagicDamage()
                || (source.getTrueSource() == this)) {
            return;
        }
        super.damageEntity(source, damageAmount);
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.isFireDamage() || source.isMagicDamage()) {
            return false;
        }
        Entity entity = source.getTrueSource();
        if (entity == this) {
            return false;
        }
        if ((entity != null) && (entity instanceof EntityLivingBase)
                && !(entity instanceof EntityEldermob)) {
            if ((getAttackTarget() == null)
                    || (rand.nextInt(20) < amount)
                    || (!canEntityBeSeen(getAttackTarget()))) {
                setAttackTarget((EntityLivingBase) entity);
            }
        }
        return super.attackEntityFrom(source, amount);
    }


    @Override
    /**
     * Drop a randomly enchanted book.
     *
     * Unlike for many other mobs, this will not be replaced with
     * the JSON based system, as its needs its own special logic,
     * notable for enchanting (unless there is more I don't know
     * about the new system, or they enhance it).
     */
    protected void dropFewItems(boolean par1, int par2) {
        dropItem(Item.getItemFromBlock(Blocks.SLIME_BLOCK),
                rand.nextInt(2) + par2);
        dropItem(Items.ENDER_EYE, rand.nextInt(2) + par2);
        if (par1) {
            int var2 = rand.nextInt(DungeonMobsHelper.getDifficulty(world) + 2);
            if (var2 == 0) {
                ItemStack myBook = new ItemStack(Items.ENCHANTED_BOOK, 1);
                //EnchantmentHelper.addRandomEnchantment(world.rand, myBook, 30, true);
                DungeonMobsHelper.addEnchantment(myBook, rand);
                EntityItem itemEnt = new EntityItem(world, posX, posY, posZ, myBook);
                world.spawnEntity(itemEnt);
            }
        }
    }


}
