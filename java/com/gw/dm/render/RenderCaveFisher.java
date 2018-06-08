package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityCaveFisher;
import com.gw.dm.model.ModelCaveFisher;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderCaveFisher extends RenderLiving
{
	private static final ResourceLocation caveFisherTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/CaveFisher.png");
	protected ModelCaveFisher model;
	
	public RenderCaveFisher(ModelCaveFisher modelCF, float f)
	{
		super(modelCF, f);
		model = (ModelCaveFisher)mainModel;
	}
	
	public void renderCaveFisher(EntityCaveFisher entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderCaveFisher((EntityCaveFisher)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderCaveFisher((EntityCaveFisher)par1, par2, par4, par6, par8, par9);
	}
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return caveFisherTextures;
    }
}