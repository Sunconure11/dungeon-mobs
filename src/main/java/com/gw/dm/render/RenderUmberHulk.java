package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelUmberHulk;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderUmberHulk extends RenderLiving {
	private static final ResourceLocation umberHulkTextures = new ResourceLocation(DungeonMobs.MODID, "textures/entity/umberhulk.png");
	protected ModelUmberHulk model;
	
	public RenderUmberHulk(RenderManager manager, ModelUmberHulk modelUH, float f) {
		super(manager, modelUH, f);
		model = (ModelUmberHulk) mainModel;
	}
	
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return umberHulkTextures;
	}
	
}
