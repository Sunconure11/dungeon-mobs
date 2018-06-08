package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityCockatrice;
import com.gw.dm.model.ModelCockatrice;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderCockatrice extends RenderLiving
{
	private static final ResourceLocation cockatriceTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Cockatrice.png");
	protected ModelCockatrice model;
	
	public RenderCockatrice(ModelCockatrice modelC, float f)
	{
		super(modelC, f);
		model = (ModelCockatrice)mainModel;
	}
	
	public void renderCockatrice(EntityCockatrice entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderCockatrice((EntityCockatrice)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderCockatrice((EntityCockatrice)par1, par2, par4, par6, par8, par9);
	}
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return cockatriceTextures;
    }
}