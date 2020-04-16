package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelFallenAngel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderFallenAngel<T extends EntityLiving> extends RenderLiving<T> {
	public ModelBase mainModel;

	public RenderFallenAngel(RenderManager rendermanagerIn,
	                         ModelFallenAngel modelIn, float shadowsizeIn) {
		super(rendermanagerIn, modelIn, shadowsizeIn);
		mainModel = modelIn;
		addLayer(new LayerCustomHead(modelIn.head));

	}


	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return new ResourceLocation(DungeonMobs.MODID, "textures/entity/FallenAngel.png");
	}

}
