package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelEyeRay;
import com.gw.dm.projectile.EntityEyeRay;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderEyeRay extends RenderLiving
{
	protected ModelEyeRay model;
	
	public RenderEyeRay(ModelEyeRay modelER, float f)
	{
		super(modelER, f);
		model = (ModelEyeRay)mainModel;
	}
	
	public void renderEyeRay(EntityEyeRay entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderEyeRay((EntityEyeRay)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderEyeRay((EntityEyeRay)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation eyeRayTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/EyeRay.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return eyeRayTextures;
    }
}