package com.gw.dm.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelBeamosBeam;
import com.gw.dm.model.ModelEyeRay;
import com.gw.dm.projectile.EntityBeamosBeam;

public class RenderBeamosBeam extends RenderLiving
{
	protected ModelBeamosBeam model;
	private static final ResourceLocation beamosBeamTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/BeamosBeam.png");
	
	public RenderBeamosBeam(ModelBeamosBeam modelBB, float f)
	{
		super(modelBB, f);
		model = (ModelBeamosBeam)mainModel;
	}
	
	public void renderBeamosBeam(EntityBeamosBeam entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderBeamosBeam((EntityBeamosBeam)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderBeamosBeam((EntityBeamosBeam)par1, par2, par4, par6, par8, par9);
	}
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return beamosBeamTextures;
    }
}