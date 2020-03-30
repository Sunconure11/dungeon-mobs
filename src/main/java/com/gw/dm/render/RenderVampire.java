package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityVampire;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVampire extends RenderBiped<EntityVampire> {
	public static final ResourceLocation VAMPIRE_TEXTURES = new ResourceLocation(DungeonMobs.MODID, "textures/entity/vampire1.png");

	public RenderVampire(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, (ModelBiped) modelbaseIn, shadowsizeIn);
	}


	@Override
	protected ResourceLocation getEntityTexture(EntityVampire entity) {
		return VAMPIRE_TEXTURES;
	}
}
