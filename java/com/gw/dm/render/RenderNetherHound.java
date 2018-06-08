package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityNetherHound;
import com.gw.dm.model.ModelNetherHound;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderNetherHound extends RenderLiving
{
	protected ModelNetherHound model;
	
	public RenderNetherHound(ModelNetherHound modelNH, float f)
	{
		super(modelNH, f);
		model = (ModelNetherHound)mainModel;
	}
	
	public void renderNetherHound(EntityNetherHound entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderNetherHound((EntityNetherHound)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderNetherHound((EntityNetherHound)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation hellHoundTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/HellHound.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return hellHoundTextures;
    }
}
