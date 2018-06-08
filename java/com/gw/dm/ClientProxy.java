package com.gw.dm;

import com.gw.dm.entity.EntityAhriman;
import com.gw.dm.entity.EntityBeamos;
import com.gw.dm.entity.EntityBladeTrap;
import com.gw.dm.entity.EntityCaveFisher;
import com.gw.dm.entity.EntityCockatrice;
import com.gw.dm.entity.EntityDestrachan;
import com.gw.dm.entity.EntityGhoul;
import com.gw.dm.entity.EntityHookHorror;
import com.gw.dm.entity.EntityIllithid;
import com.gw.dm.entity.EntityLizalfos;
import com.gw.dm.entity.EntityManticore;
import com.gw.dm.entity.EntityNetherHound;
import com.gw.dm.entity.EntityPetrified;
import com.gw.dm.entity.EntityRakshasa;
import com.gw.dm.entity.EntityRakshasaImage;
import com.gw.dm.entity.EntityRustMonster;
import com.gw.dm.entity.EntityShrieker;
import com.gw.dm.entity.EntityThoqqua;
import com.gw.dm.entity.EntityTroll;
import com.gw.dm.entity.EntityUmberHulk;
import com.gw.dm.entity.EntityVescavor;
import com.gw.dm.model.ModelAhriman;
import com.gw.dm.model.ModelBeamos;
import com.gw.dm.model.ModelBeamosBeam;
import com.gw.dm.model.ModelBladeTrap;
import com.gw.dm.model.ModelCaveFisher;
import com.gw.dm.model.ModelCockatrice;
import com.gw.dm.model.ModelDestrachan;
import com.gw.dm.model.ModelEyeRay;
import com.gw.dm.model.ModelGhoul;
import com.gw.dm.model.ModelHookHorror;
import com.gw.dm.model.ModelIllithid;
import com.gw.dm.model.ModelLizalfos;
import com.gw.dm.model.ModelManticore;
import com.gw.dm.model.ModelNetherHound;
import com.gw.dm.model.ModelPetrified;
import com.gw.dm.model.ModelRakshasa;
import com.gw.dm.model.ModelRakshasaImage;
import com.gw.dm.model.ModelRustMonster;
import com.gw.dm.model.ModelShrieker;
import com.gw.dm.model.ModelThoqqua;
import com.gw.dm.model.ModelTroll;
import com.gw.dm.model.ModelUmberHulk;
import com.gw.dm.model.ModelVescavor;
import com.gw.dm.projectile.EntityBeamosBeam;
import com.gw.dm.projectile.EntityEyeRay;
import com.gw.dm.render.RenderAhriman;
import com.gw.dm.render.RenderBeamos;
import com.gw.dm.render.RenderBeamosBeam;
import com.gw.dm.render.RenderBladeTrap;
import com.gw.dm.render.RenderCaveFisher;
import com.gw.dm.render.RenderCockatrice;
import com.gw.dm.render.RenderDestrachan;
import com.gw.dm.render.RenderEyeRay;
import com.gw.dm.render.RenderGhoul;
import com.gw.dm.render.RenderHookHorror;
import com.gw.dm.render.RenderIllithid;
import com.gw.dm.render.RenderLizalfos;
import com.gw.dm.render.RenderManticore;
import com.gw.dm.render.RenderNetherHound;
import com.gw.dm.render.RenderPetrified;
import com.gw.dm.render.RenderRakshasa;
import com.gw.dm.render.RenderRakshasaImage;
import com.gw.dm.render.RenderRustMonster;
import com.gw.dm.render.RenderShrieker;
import com.gw.dm.render.RenderThoqqua;
import com.gw.dm.render.RenderTroll;
import com.gw.dm.render.RenderUmberHulk;
import com.gw.dm.render.RenderVescavor;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{
	public void registerRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityRustMonster.class, new RenderRustMonster(new ModelRustMonster(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhoul.class, new RenderGhoul(new ModelGhoul(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityShrieker.class, new RenderShrieker(new ModelShrieker(), 0.6F));
		RenderingRegistry.registerEntityRenderingHandler(EntityUmberHulk.class, new RenderUmberHulk(new ModelUmberHulk(), 2.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityHookHorror.class, new RenderHookHorror(new ModelHookHorror(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAhriman.class, new RenderAhriman(new ModelAhriman(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityEyeRay.class, new RenderEyeRay(new ModelEyeRay(), 0.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTroll.class, new RenderTroll(new ModelTroll(), 1.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCaveFisher.class, new RenderCaveFisher(new ModelCaveFisher(), 1.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDestrachan.class, new RenderDestrachan(new ModelDestrachan(), 1.4F));
		RenderingRegistry.registerEntityRenderingHandler(EntityIllithid.class, new RenderIllithid(new ModelIllithid(), 1.1F));
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherHound.class, new RenderNetherHound(new ModelNetherHound(), 1.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRakshasa.class, new RenderRakshasa(new ModelRakshasa(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRakshasaImage.class, new RenderRakshasaImage(new ModelRakshasaImage(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLizalfos.class, new RenderLizalfos(new ModelLizalfos(), 1.8F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCockatrice.class, new RenderCockatrice(new ModelCockatrice(), 0.6F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPetrified.class, new RenderPetrified(new ModelPetrified(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityManticore.class, new RenderManticore(new ModelManticore(), 1.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBladeTrap.class, new RenderBladeTrap(new ModelBladeTrap(), 0.98F));
		RenderingRegistry.registerEntityRenderingHandler(EntityThoqqua.class, new RenderThoqqua(new ModelThoqqua(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityVescavor.class, new RenderVescavor(new ModelVescavor(), 0.4F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBeamos.class, new RenderBeamos(new ModelBeamos(), 0.98F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBeamosBeam.class, new RenderBeamosBeam(new ModelBeamosBeam(), 0.0F));
	}
	
	/*
	@EventHandler
	public void versionCheck(FMLPostInitializationEvent event) 
	{
		System.out.println("Doing a thing!");
		
		//DungeonMobs.versionChecker = new VersionChecker();
		
	}
	*/
}
