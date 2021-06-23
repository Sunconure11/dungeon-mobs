package com.gw.dm.model;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityHissingDemon;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelHissingDemon extends AnimatedGeoModel<EntityHissingDemon> {

	@Override
	public ResourceLocation getAnimationFileLocation(EntityHissingDemon animatable) {
		return new ResourceLocation(DungeonMobs.MODID, "animations/hissing_demon.json");
	}

	@Override
	public ResourceLocation getModelLocation(EntityHissingDemon object) {
		return new ResourceLocation(DungeonMobs.MODID, "geo/hissing_demon.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityHissingDemon object) {
		return new ResourceLocation(DungeonMobs.MODID, "textures/entity/hissing_demon.png");
	}
	
	
	
	
	
}
