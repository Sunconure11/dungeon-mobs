package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelBladeTrap;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBladeTrapEntity extends RenderLiving {
	private static final ResourceLocation bladeTrapTextures = new ResourceLocation(DungeonMobs.MODID, "textures/entity/BladeTrap.png");
	protected ModelBladeTrap model;
	
	
	public RenderBladeTrapEntity(RenderManager manager, ModelBladeTrap modelBT, float f) {
		super(manager, modelBT, f);
		model = (ModelBladeTrap) mainModel;
	}
	
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return bladeTrapTextures;
	}
}