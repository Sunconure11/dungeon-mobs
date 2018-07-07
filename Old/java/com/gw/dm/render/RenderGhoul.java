package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityGhoul;
import com.gw.dm.model.ModelGhoul;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class RenderGhoul extends RenderLiving
{
	protected ModelGhoul model;
	
	public RenderGhoul(ModelGhoul modelG, float f)
	{
		super(modelG, f);
		model = (ModelGhoul)mainModel;
	}
	
	public void renderGhoul(EntityGhoul entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderGhoul((EntityGhoul)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderGhoul((EntityGhoul)par1, par2, par4, par6, par8, par9);
	}

	private static final ResourceLocation ghoulTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Ghoul.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return ghoulTextures;
    }
}