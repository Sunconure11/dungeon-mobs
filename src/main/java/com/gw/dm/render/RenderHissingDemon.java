package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityHissingDemon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderHissingDemon extends RenderLiving<EntityHissingDemon> {

	public RenderHissingDemon(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
        this.addLayer(new LayerSixHeldItems(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHissingDemon entity) {
		return new ResourceLocation(DungeonMobs.MODID, "textures/entity/hissing_demon.png");
	}
	
	@Override
	public void doRender(EntityHissingDemon entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		
	}

}
