package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityManticore;
import com.gw.dm.model.ModelManticore;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderManticore extends RenderLiving 
{
protected ModelManticore model;
	
	public RenderManticore(ModelManticore modelM, float f)
	{
		super(modelM, f);
		model = (ModelManticore)mainModel;
	}
	
	public void renderManticore(EntityManticore entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderManticore((EntityManticore)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderManticore((EntityManticore)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation manticoreTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Manticore.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return manticoreTextures;
    }
}
