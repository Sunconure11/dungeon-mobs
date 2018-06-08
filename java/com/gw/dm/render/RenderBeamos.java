package com.gw.dm.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityBeamos;
import com.gw.dm.model.ModelBeamos;

public class RenderBeamos extends RenderLiving 
{
	private static final ResourceLocation beamosTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Beamos.png");
	protected ModelBeamos model;
	
	public RenderBeamos(ModelBeamos modelB, float f)
	{
		super(modelB, f);
		model = (ModelBeamos)mainModel;
	}
	
	public void renderBeamos(EntityBeamos entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderBeamos((EntityBeamos)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderBeamos((EntityBeamos)par1, par2, par4, par6, par8, par9);
	}
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return beamosTextures;
    }
}
