package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelHookHorror;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderHookHorror extends RenderLiving {
	private static final ResourceLocation hookHorrorTextures = new ResourceLocation(DungeonMobs.MODID, "textures/entity/HookHorror.png");
	
	protected ModelHookHorror model;
	
	public RenderHookHorror(RenderManager manager, ModelHookHorror modelHH, float f) {
		super(manager, modelHH, f);
		model = (ModelHookHorror) mainModel;
	}
	
	
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return hookHorrorTextures;
	}
}