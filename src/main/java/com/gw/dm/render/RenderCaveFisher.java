package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelCaveFisher;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCaveFisher extends RenderLiving {
	private static final ResourceLocation caveFisherTextures = new ResourceLocation(DungeonMobs.MODID, "textures/entity/CaveFisher.png");
	protected ModelCaveFisher model;
	
	
	public RenderCaveFisher(RenderManager manager, ModelCaveFisher modelCF, float f) {
		super(manager, modelCF, f);
		model = (ModelCaveFisher) mainModel;
	}
	
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return caveFisherTextures;
	}
}