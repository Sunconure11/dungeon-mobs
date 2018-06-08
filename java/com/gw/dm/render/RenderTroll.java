package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityTroll;
import com.gw.dm.model.ModelTroll;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class RenderTroll extends RenderLiving
{
	protected ModelTroll model;
	
	public RenderTroll(ModelTroll modelT, float f)
	{
		super(modelT, f);
		model = (ModelTroll)mainModel;
	}
	
	public void renderTroll(EntityTroll entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderTroll((EntityTroll)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderTroll((EntityTroll)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation trollTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Troll.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return trollTextures;
    }
}