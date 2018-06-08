package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityRakshasa;
import com.gw.dm.model.ModelRakshasa;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class RenderRakshasa extends RenderLiving
{
	protected ModelRakshasa model;
	
	public RenderRakshasa(ModelRakshasa modelR, float f)
	{
		super(modelR, f);
		model = (ModelRakshasa)mainModel;
	}
	
	protected int renderRakshasaIllusion(EntityRakshasa ent, int par2, float par3)
    {
		//this.loadTexture(ent.currentSkin);
        return par2 == 0 && ent.hasIllusion ? 1 : -1;
    }
	
	public void renderRakshasa(EntityRakshasa entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderRakshasa((EntityRakshasa)par1, par2, par4, par6, par8, par9);
	}
	
	protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.renderRakshasaIllusion((EntityRakshasa)par1EntityLiving, par2, par3);
    }
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderRakshasa((EntityRakshasa)par1, par2, par4, par6, par8, par9);
	}
	
	private static final ResourceLocation rakshasaTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Rakshasa.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return rakshasaTextures;
    }
}

