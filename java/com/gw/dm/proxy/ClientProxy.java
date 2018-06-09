package com.gw.dm.proxy;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import com.gw.dm.entity.EntityAhriman;
import com.gw.dm.entity.EntityGhoul;
import com.gw.dm.model.ModelAhriman;
import com.gw.dm.model.ModelGhoul;
import com.gw.dm.render.RenderAhriman;
import com.gw.dm.render.RenderGhoul;

public class ClientProxy extends CommonProxy {
	
	public void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityGhoul.class, 
				new IRenderFactory<EntityGhoul>(){
					@Override
					public Render<EntityGhoul> createRenderFor(
							RenderManager manager) {
						return new RenderGhoul(manager, new ModelGhoul(), 0.5f);
					}			
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityAhriman.class, 
				new IRenderFactory<EntityAhriman>(){
					@Override
					public Render<EntityAhriman> createRenderFor(
							RenderManager manager) {
						return new RenderAhriman(manager, new ModelAhriman(), 0.5f);
					}			
		});
	}
	
}
