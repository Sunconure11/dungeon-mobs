package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityHookHorror;
import com.gw.dm.model.ModelHookHorror;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderHookHorror extends RenderLiving
{
	protected ModelHookHorror model;
	
	public RenderHookHorror(ModelHookHorror modelHH, float f)
	{
		super(modelHH, f);
		model = (ModelHookHorror)mainModel;
	}
	
	public void renderHookHorror(EntityHookHorror entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderHookHorror((EntityHookHorror)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderHookHorror((EntityHookHorror)par1, par2, par4, par6, par8, par9);
	}
	
private static final ResourceLocation hookHorrorTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/HookHorror.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return hookHorrorTextures;
    }
}