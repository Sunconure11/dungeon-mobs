package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityThoqqua;
import com.gw.dm.model.ModelThoqqua;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderThoqqua extends RenderLiving
{
	protected ModelThoqqua model;
	
	public RenderThoqqua(ModelThoqqua modelT, float f)
	{
		super(modelT, f);
		model = (ModelThoqqua)mainModel;
	}
	
	public void renderThoqqua(EntityThoqqua entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderThoqqua((EntityThoqqua)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderThoqqua((EntityThoqqua)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation thoqquaTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Thoqqua.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return thoqquaTextures;
    }
}
