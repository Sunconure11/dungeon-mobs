package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityRakshasaImage;
import com.gw.dm.model.ModelRakshasaImage;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderRakshasaImage extends RenderLiving
{
	protected ModelRakshasaImage model;
	
	public RenderRakshasaImage(ModelRakshasaImage modelRI, float f)
	{
		super(modelRI, f);
		model = (ModelRakshasaImage)mainModel;
	}
	
	public void renderRakshasaImage(EntityRakshasaImage entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderRakshasaImage((EntityRakshasaImage)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderRakshasaImage((EntityRakshasaImage)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation rakshasaImageTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Rakshasa.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return rakshasaImageTextures;
    }
}