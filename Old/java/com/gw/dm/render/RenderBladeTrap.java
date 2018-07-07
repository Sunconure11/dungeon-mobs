package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityBladeTrap;
import com.gw.dm.model.ModelBladeTrap;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderBladeTrap extends RenderLiving
{
	private static final ResourceLocation bladeTrapTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/BladeTrap.png");
	protected ModelBladeTrap model;
	
	public RenderBladeTrap(ModelBladeTrap modelBT, float f)
	{
		super(modelBT, f);
		model = (ModelBladeTrap)mainModel;
	}
	
	public void renderBladeTrap(EntityBladeTrap entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderBladeTrap((EntityBladeTrap)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderBladeTrap((EntityBladeTrap)par1, par2, par4, par6, par8, par9);
	}
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return bladeTrapTextures;
    }
}