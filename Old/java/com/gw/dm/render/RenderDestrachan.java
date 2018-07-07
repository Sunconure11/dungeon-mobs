package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityDestrachan;
import com.gw.dm.model.ModelDestrachan;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderDestrachan extends RenderLiving
{
	private static final ResourceLocation destrachanTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Destrachan.png");
	protected ModelDestrachan model;
	
	public RenderDestrachan(ModelDestrachan modelD, float f)
	{
		super(modelD, f);
		model = (ModelDestrachan)mainModel;
	}
	
	public void renderDestrachan(EntityDestrachan entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderDestrachan((EntityDestrachan)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderDestrachan((EntityDestrachan)par1, par2, par4, par6, par8, par9);
	}
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return destrachanTextures;
    }
}