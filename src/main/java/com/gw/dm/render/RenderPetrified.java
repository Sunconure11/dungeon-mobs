package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelPetrified;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPetrified extends RenderLiving {
	private static final ResourceLocation petrifiedTextures = new ResourceLocation(DungeonMobs.MODID, "textures/entity/Petrified.png");
	protected ModelPetrified model;
	
	public RenderPetrified(RenderManager manager, ModelPetrified modelP, float f) {
		super(manager, modelP, f);
		model = (ModelPetrified) mainModel;
	}
	
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return petrifiedTextures;
	}
}
