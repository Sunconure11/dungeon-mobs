package com.gw.dm.render;

import com.gw.dm.entity.EntityHissingDemon;
import com.gw.dm.model.ModelHissingDemon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerSixHeldItems implements LayerRenderer<EntityHissingDemon> {
	private RenderHissingDemon demonRenderer;

    public LayerSixHeldItems(RenderHissingDemon livingEntityRendererIn) {
        demonRenderer = livingEntityRendererIn;
    }

    
	@Override
	public void doRenderLayer(EntityHissingDemon demon, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		//int armInUse = demon.getHandToSwing();
        GlStateManager.pushMatrix(); {
	        this.renderHeldItem(demon, demon.getHeldItem(0), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, 0);
	        this.renderHeldItem(demon, demon.getHeldItem(1), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, 1);
	        this.renderHeldItem(demon, demon.getHeldItem(2), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, 2);
	        this.renderHeldItem(demon, demon.getHeldItem(3), ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, 3);
	        this.renderHeldItem(demon, demon.getHeldItem(4), ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, 4);
	        this.renderHeldItem(demon, demon.getHeldItem(5), ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, 5);
        } GlStateManager.popMatrix();
		
	}
	
	
    private void renderHeldItem(EntityHissingDemon demon, ItemStack item, ItemCameraTransforms.TransformType transType, int arm) {
		//System.err.println("Rendering ... " + item);
        if ((item != null) && !item.isEmpty()) {
   		 boolean flagl = arm > 2;
			 EnumHandSide side;
			 if(flagl) {
				 side = EnumHandSide.LEFT;
			 } else {
				 side = EnumHandSide.RIGHT;
			 }
            GlStateManager.pushMatrix(); {
	            // Forge: moved this call down, fixes incorrect offset while sneaking.
	            this.translateToHand(arm);
	            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
	            GlStateManager.translate((float)(flagl ? -1 : 1) / 16.0F, 0.125F, -0.625F);
	            Minecraft.getMinecraft().getItemRenderer().renderItemSide(demon, item, transType, flagl);
            } GlStateManager.popMatrix();
        }
    }
	

    protected void translateToHand(int hand) {
        ((ModelHissingDemon)this.demonRenderer.getMainModel()).postRenderArm(0.0625F, hand);
    }

	
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
