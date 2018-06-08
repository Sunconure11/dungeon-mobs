package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityPetrified;
import com.gw.dm.model.ModelPetrified;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderPetrified extends RenderLiving
{
	protected ModelPetrified model;
	
	public RenderPetrified(ModelPetrified modelP, float f)
	{
		super(modelP, f);
		model = (ModelPetrified)mainModel;
	}
	
	public void renderPetrified(EntityPetrified entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderPetrified((EntityPetrified)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderPetrified((EntityPetrified)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation petrifiedTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Petrified.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return petrifiedTextures;
    }
}
