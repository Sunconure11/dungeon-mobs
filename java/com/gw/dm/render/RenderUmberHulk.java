package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityUmberHulk;
import com.gw.dm.model.ModelUmberHulk;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderUmberHulk extends RenderLiving
{
	protected ModelUmberHulk model;
	
	public RenderUmberHulk(ModelUmberHulk modelUH, float f)
	{
		super(modelUH, f);
		model = (ModelUmberHulk)mainModel;
	}
	
	public void renderUmberHulk(EntityUmberHulk entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderUmberHulk((EntityUmberHulk)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderUmberHulk((EntityUmberHulk)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation umberHulkTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/UmberHulk.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return umberHulkTextures;
    }

}
