package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityShrieker;
import com.gw.dm.model.ModelShrieker;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderShrieker extends RenderLiving
{
	protected ModelShrieker model;
	
	public RenderShrieker(ModelShrieker modelS, float f)
	{
		super(modelS, f);
		model = (ModelShrieker)mainModel;
	}
	
	public void renderShrieker(EntityShrieker entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender((EntityLiving)entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderShrieker((EntityShrieker)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderShrieker((EntityShrieker)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation shriekerTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Shrieker.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return shriekerTextures;
    }
}