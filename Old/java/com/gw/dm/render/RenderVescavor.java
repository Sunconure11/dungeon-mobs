package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityCockatrice;
import com.gw.dm.entity.EntityVescavor;
import com.gw.dm.model.ModelCockatrice;
import com.gw.dm.model.ModelVescavor;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderVescavor extends RenderLiving
{
	private static final ResourceLocation vescavorTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Vescavor.png");
	protected ModelVescavor model;
	
	public RenderVescavor(ModelVescavor modelV, float f)
	{
		super(modelV, f);
		model = (ModelVescavor)mainModel;
	}
	
	public void renderVescavor(EntityVescavor entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderVescavor((EntityVescavor)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderVescavor((EntityVescavor)par1, par2, par4, par6, par8, par9);
	}
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return vescavorTextures;
    }
}