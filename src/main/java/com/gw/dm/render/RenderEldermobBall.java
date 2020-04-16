package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEldermobBall<T extends Entity> extends RenderProjectile {

	public RenderEldermobBall(RenderManager renderManager) {
		super(renderManager, 1.0f,
				new ResourceLocation(DungeonMobs.MODID,
						"textures/entity/eldermobball.png"));
	}
}
