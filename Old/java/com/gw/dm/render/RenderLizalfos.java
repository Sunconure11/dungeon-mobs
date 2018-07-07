package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityLizalfos;
import com.gw.dm.model.ModelLizalfos;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderLizalfos extends RenderLiving
{
	protected ModelLizalfos model;
	
	public RenderLizalfos(ModelLizalfos modelL, float f)
	{
		super(modelL, f);
		model = (ModelLizalfos)mainModel;
	}
	
	public void renderLizalfos(EntityLizalfos entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderLizalfos((EntityLizalfos)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderLizalfos((EntityLizalfos)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation lizalfosTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Lizalfos.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return lizalfosTextures;
    }
}
