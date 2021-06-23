package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityHissingDemon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


@SideOnly(Side.CLIENT)
public class RenderHissingDemon extends GeoEntityRenderer<EntityHissingDemon> {

	public RenderHissingDemon(RenderManager renderManager, AnimatedGeoModel<EntityHissingDemon> modelProvider) {
		super(renderManager, modelProvider);
        this.shadowSize = 1.0f;
	}
	
	
	@Override
	public void doRender(EntityHissingDemon entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		
	}

}
