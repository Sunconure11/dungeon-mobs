package com.gw.dm.proxy;

import com.gw.dm.DungeonMobs;
import com.gw.dm.blocks.TileEntityBladeTrap;
import com.gw.dm.entity.*;
import com.gw.dm.model.*;
import com.gw.dm.projectile.EntityEldermobBall;
import com.gw.dm.projectile.EntityEyeRay;
import com.gw.dm.projectile.EntityLightball;
import com.gw.dm.projectile.EntityMagicMissile;
import com.gw.dm.render.*;
import com.gw.dm.util.DungeonMobsHelper;
import com.gw.dm.util.InputConfusedMovement;
import com.gw.dm.util.MiscRegistrar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Collection;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	static InputConfusedMovement confusedMovementInput;

	@SubscribeEvent
	public static void doStuff(ClientTickEvent event) {

		EntityPlayerSP player = Minecraft.getMinecraft().player;

		if (player != null) {
			if (isAddled(player)) {
				if (!(player.movementInput instanceof InputConfusedMovement)) {
					confusedMovementInput
							= new InputConfusedMovement(player.movementInput);

					int foo = DungeonMobsHelper.getDifficulty(player.world);
					confusedMovementInput.setConfValue(foo);

					confusedMovementInput.randomize();
					player.movementInput = confusedMovementInput;
					confusedMovementInput.setConfusion(true);
				} else {
					confusedMovementInput.setConfusion(true);
					if (player.ticksExisted % 40 == 0)
						confusedMovementInput.randomize();
				}

			} else if (player.movementInput instanceof InputConfusedMovement)
				confusedMovementInput.setConfusion(false);
		}
	}

	
	private static boolean isAddled(EntityPlayer player) {
		Collection<PotionEffect> effects = player.getActivePotionEffects();
		for (PotionEffect eff : effects) {
			if (eff.getPotion() == MiscRegistrar.potionAddle) {
				return true;
			}
		}
		return false;
	}

	
	@Override
	public void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityGhoul.class,
				new IRenderFactory<EntityGhoul>() {
					@Override
					public Render<EntityGhoul> createRenderFor(
							RenderManager manager) {
						return new RenderGhoul(manager, new ModelGhoul(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityAhriman.class,
				new IRenderFactory<EntityAhriman>() {
					@Override
					public Render<EntityAhriman> createRenderFor(
							RenderManager manager) {
						return new RenderAhriman(manager, new ModelAhriman(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityEyeRay.class,
				new IRenderFactory<EntityEyeRay>() {
					@Override
					public Render<EntityEyeRay> createRenderFor(
							RenderManager manager) {
						return new RenderProjectile(manager, 0.5f,
								new ResourceLocation(DungeonMobs.MODID,
										"textures/entity/eyeray.png"));
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityTroll.class,
				new IRenderFactory<EntityTroll>() {
					@Override
					public Render<EntityTroll> createRenderFor(
							RenderManager manager) {
						return new RenderTroll(manager, new ModelTroll(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityManticore.class,
				new IRenderFactory<EntityManticore>() {
					@Override
					public Render<EntityManticore> createRenderFor(
							RenderManager manager) {
						return new RenderManticore(manager, new ModelManticore(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityVampire.class,
				new IRenderFactory<EntityVampire>() {
					@Override
					public Render<EntityVampire> createRenderFor(
							RenderManager manager) {
						return new RenderVampire(manager, new ModelBiped(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityRevenant.class,
				new IRenderFactory<EntityRevenant>() {
					@Override
					public Render<EntityZombie> createRenderFor(
							RenderManager manager) {
						return new RenderZombie(manager);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityHookHorror.class,
				new IRenderFactory<EntityHookHorror>() {
					@Override
					public Render<EntityHookHorror> createRenderFor(
							RenderManager manager) {
						return new RenderHookHorror(manager, new ModelHookHorror(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityDestrachan.class,
				new IRenderFactory<EntityDestrachan>() {
					@Override
					public Render<EntityDestrachan> createRenderFor(
							RenderManager manager) {
						return new RenderDestrachan(manager, new ModelDestrachan(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityCaveFisher.class,
				new IRenderFactory<EntityCaveFisher>() {
					@Override
					public Render<EntityCaveFisher> createRenderFor(
							RenderManager manager) {
						return new RenderCaveFisher(manager, new ModelCaveFisher(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherHound.class,
				new IRenderFactory<EntityNetherHound>() {
					@Override
					public Render<EntityNetherHound> createRenderFor(
							RenderManager manager) {
						return new RenderNetherHound(manager, new ModelNetherHound(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityRustMonster.class,
				new IRenderFactory<EntityRustMonster>() {
					@Override
					public Render<EntityRustMonster> createRenderFor(
							RenderManager manager) {
						return new RenderRustMonster(manager, new ModelRustMonster(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityUmberHulk.class,
				new IRenderFactory<EntityUmberHulk>() {
					@Override
					public Render<EntityUmberHulk> createRenderFor(
							RenderManager manager) {
						return new RenderUmberHulk(manager, new ModelUmberHulk(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityPetrified.class,
				new IRenderFactory<EntityPetrified>() {
					@Override
					public Render<EntityPetrified> createRenderFor(
							RenderManager manager) {
						return new RenderPetrified(manager, new ModelPetrified(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityCockatrice.class,
				new IRenderFactory<EntityCockatrice>() {
					@Override
					public Render<EntityCockatrice> createRenderFor(
							RenderManager manager) {
						return new RenderCockatrice(manager, new ModelCockatrice(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityShrieker.class,
				new IRenderFactory<EntityShrieker>() {
					@Override
					public Render<EntityShrieker> createRenderFor(
							RenderManager manager) {
						return new RenderShrieker(manager, new ModelShrieker(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityThoqqua.class,
				new IRenderFactory<EntityThoqqua>() {
					@Override
					public Render<EntityThoqqua> createRenderFor(
							RenderManager manager) {
						return new RenderThoqqua(manager, new ModelThoqqua(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityVescavor.class,
				new IRenderFactory<EntityVescavor>() {
					@Override
					public Render<EntityVescavor> createRenderFor(
							RenderManager manager) {
						return new RenderVescavor(manager, new ModelVescavor(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityIllithid.class,
				new IRenderFactory<EntityIllithid>() {
					@Override
					public Render<EntityIllithid> createRenderFor(
							RenderManager manager) {
						return new RenderIllithid(manager, new ModelIllithid(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityRakshasa.class,
				new IRenderFactory<EntityRakshasa>() {
					@Override
					public Render<EntityRakshasa> createRenderFor(
							RenderManager manager) {
						return new RenderRakshasa(manager, new ModelRakshasa(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityMagicMissile.class,
				new IRenderFactory<EntityMagicMissile>() {
					@Override
					public RenderMagicMissile createRenderFor(
							RenderManager manager) {
						return new RenderMagicMissile(manager);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityRakshasaImage.class,
				new IRenderFactory<EntityRakshasaImage>() {
					@Override
					public Render<EntityRakshasaImage> createRenderFor(
							RenderManager manager) {
						return new RenderRakshasa(manager, new ModelRakshasa(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityLightball.class,
				new IRenderFactory<EntityLightball>() {
					@Override
					public Render<EntityLightball> createRenderFor(
							RenderManager manager) {
						return new RenderProjectile(manager, 0.5f,
								new ResourceLocation(DungeonMobs.MODID,
										"textures/entity/lightball.png"));
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityFallenAngel.class,
				new IRenderFactory<EntityFallenAngel>() {
					@Override
					public Render<EntityFallenAngel> createRenderFor(
							RenderManager manager) {
						return new RenderFallenAngel(manager, new ModelFallenAngel(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityLizalfos.class,
				new IRenderFactory<EntityLizalfos>() {
					@Override
					public Render<EntityLizalfos> createRenderFor(
							RenderManager manager) {
						return new RenderLizalfos(manager, new ModelLizalfos(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityEldermob.class,
				new IRenderFactory<EntityEldermob>() {
					@Override
					public Render<EntityEldermob> createRenderFor(
							RenderManager manager) {
						return new RenderEldermob(manager, new ModelEldermob(), 0.5f);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityEldermobBall.class,
				new IRenderFactory<EntityEldermobBall>() {
					@Override
					public Render<EntityEldermobBall> createRenderFor(
							RenderManager manager) {
						return new RenderProjectile(manager, 0.5f,
								new ResourceLocation(DungeonMobs.MODID,
										"textures/entity/eldermobball.png"));
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityBladeTrap.class, 
		new IRenderFactory<EntityBladeTrap>(){
			@Override
			public Render<EntityBladeTrap> createRenderFor(
					RenderManager manager) {
				return new RenderBladeTrapEntity(manager, new ModelBladeTrap(), 0.5f);
			}			
		});
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBladeTrap.class, 
				new RenderBladeTrapBlock());
	}

}
