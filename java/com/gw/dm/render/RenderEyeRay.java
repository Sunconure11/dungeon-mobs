package com.gw.dm.render;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import com.gw.dm.DungeonMobs;

public class RenderEyeRay extends RenderSnowball {

	private static final ResourceLocation eyeRayTextures 
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/EyeRay.png");
	
	public RenderEyeRay(RenderManager rm, Item item, RenderItem itemRenderer) {
		super(rm, item, itemRenderer);
	}
	
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return eyeRayTextures;
    }
}