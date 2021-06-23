package com.gw.dm.model;

import com.gw.dm.entity.EntityFallenAngel;
import com.gw.dm.entity.EntityHissingDemon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelHissingDemon extends ModelBase {
	private static final float[] tailAng;
	private static final float[] tailMov;
	
	private static final float ARM1Z = (float) ((Math.PI / 2) + (Math.PI / 4)); 
	private static final float ARM2Z = (float) Math.PI / 2;
	private static final float ARM3Z = (float) Math.PI / 4;
	
	private final ModelRenderer hbone;
	private final ModelRenderer head;
	private final ModelRenderer ab1;
	private final ModelRenderer arm1_r1;
	private final ModelRenderer ab2;
	private final ModelRenderer arm2_r1;
	private final ModelRenderer ab3;
	private final ModelRenderer arm3_r1;
	private final ModelRenderer ab4;
	private final ModelRenderer arm4_r1;
	private final ModelRenderer ab5;
	private final ModelRenderer arm5_r1;
	private final ModelRenderer ab6;
	private final ModelRenderer arm6_r1;
	private final ModelRenderer t1;
	private final ModelRenderer t2;
	private final ModelRenderer t3;
	private final ModelRenderer t4;
	private final ModelRenderer t5;
	private final ModelRenderer t6;
	private final ModelRenderer t7;
	private final ModelRenderer bb_main;
    // The Right Arms
    public ModelRenderer[] arms;
    public ModelRenderer[] tail;
    
    
    static {
    	tailAng = new float[] {(float)(Math.PI * 0.25), (float)(Math.PI * 0.5), (float)(Math.PI * 0.75), 
    			(float)(Math.PI ), (float)(Math.PI * 1.25), (float)(Math.PI * 1.5), (float)(Math.PI * 1.75)};
    	tailMov = new float[] {0.5f, 1.0f, 1.1f, 1.2f, 1.1f, 1.0f, 0.75f};
    }
    

    public ModelHissingDemon() {
		textureWidth = 96;
		textureHeight = 96;

		head = hbone = new ModelRenderer(this);
		hbone.setRotationPoint(0.0F, 1.0F, 0.0F);
		hbone.cubeList.add(new ModelBox(hbone, 0, 0, -6.0F, -22.0F, -3.0F, 12, 12, 12, 0.0F, false));

		ab1 = new ModelRenderer(this);
		ab1.setRotationPoint(-6.0F, -8.0F, 3.0F);
		

		arm1_r1 = new ModelRenderer(this);
		arm1_r1.setRotationPoint(0.0F, 1.0F, 0.0F);
		ab1.addChild(arm1_r1);
		arm1_r1.setTextureOffset(0, 61).addBox(-2.5F, -2.0F, -2.25F, 4.0F, 18.0F, 4.0F, 0.0F, false);

		ab2 = new ModelRenderer(this);
		ab2.setRotationPoint(-6.0F, -4.0F, 2.0F);
		

		arm2_r1 = new ModelRenderer(this);
		arm2_r1.setRotationPoint(0.0F, -0.5F, 1.0F);
		ab2.addChild(arm2_r1);
		arm2_r1.setTextureOffset(21, 61).addBox(-1.75F, 0.25F, -2.25F, 4.0F, 18.0F, 4.0F, 0.0F, false);

		ab3 = new ModelRenderer(this);
		ab3.setRotationPoint(-5.0F, -3.0F, 3.0F);
		

		arm3_r1 = new ModelRenderer(this);
		arm3_r1.setRotationPoint(-1.0F, 1.0F, 0.0F);
		ab3.addChild(arm3_r1);
		arm3_r1.setTextureOffset(0, 61).addBox(-2.25F, -1.25F, -2.25F, 4.0F, 18.0F, 5.0F, 0.0F, false);

		ab4 = new ModelRenderer(this);
		ab4.setRotationPoint(6.0F, -7.0F, 3.0F);
		

		arm4_r1 = new ModelRenderer(this);
		arm4_r1.setRotationPoint(0.0F, 0.0F, -3.0F);
		ab4.addChild(arm4_r1);
		arm4_r1.setTextureOffset(0, 61).addBox(-2.0F, -2.0F, 0.75F, 4.0F, 18.0F, 5.0F, 0.0F, false);

		ab5 = new ModelRenderer(this);
		ab5.setRotationPoint(7.0F, -4.0F, 3.0F);
		

		arm5_r1 = new ModelRenderer(this);
		arm5_r1.setRotationPoint(-1.0F, -0.5F, -3.0F);
		ab5.addChild(arm5_r1);
		arm5_r1.setTextureOffset(21, 61).addBox(-1.75F, 0.25F, 0.75F, 4.0F, 18.0F, 4.0F, 0.0F, false);

		ab6 = new ModelRenderer(this);
		ab6.setRotationPoint(4.0F, -3.0F, 0.0F);
		

		arm6_r1 = new ModelRenderer(this);
		arm6_r1.setRotationPoint(2.0F, 1.0F, 3.0F);
		ab6.addChild(arm6_r1);
		arm6_r1.setTextureOffset(0, 61).addBox(-2.25F, -2.25F, -2.25F, 4.0F, 18.0F, 5.0F, 0.0F, false);

		t7 = new ModelRenderer(this);
		t7.setRotationPoint(0.0F, 24.0F, 0.0F);
		t7.cubeList.add(new ModelBox(t7, 0, 0, -1.0F, -2.0F, 35.0F, 2, 2, 2, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 25, -7.0F, -19.0F, 1.0F, 14, 9, 9, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 41, 38, -6.0F, -33.0F, 0.0F, 12, 18, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 27, 44, -5.0F, -30.0F, -1.5F, 4, 3, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 27, 44, 1.0F, -30.0F, -1.5F, 4, 3, 2, 0.0F, true));
		
		arms = new ModelRenderer[6];		
		arms[0] = arm1_r1;
		arms[1] = arm2_r1;
		arms[2] = arm3_r1;
		arms[3] = arm4_r1;
		arms[4] = arm5_r1;
		arms[5] = arm6_r1;
		
		tail = new ModelRenderer[7];
		tail[0] = t1;
		tail[1] = t2;
		tail[2] = t3;
		tail[3] = t4;
		tail[4] = t5;
		tail[5] = t6;
		tail[6] = t7;
	}


	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount,
	                   float age, float headYaw, float headPitch, float scale) {
		hbone.render(scale);
		ab1.render(scale);
		ab2.render(scale);
		ab3.render(scale);
		ab4.render(scale);
		ab5.render(scale);
		ab6.render(scale);
		t1.render(scale);
		t2.render(scale);
		t3.render(scale);
		t4.render(scale);
		t5.render(scale);
		t6.render(scale);
		t7.render(scale);
		bb_main.render(scale);
	}


	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
	                              float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		head.rotateAngleY = netHeadYaw * 0.017453292F;
		head.rotateAngleX = headPitch * 0.017453292F;
		
        arms[0].rotateAngleZ = ARM1Z;
        arms[1].rotateAngleZ = ARM2Z;
        arms[2].rotateAngleZ = ARM3Z;
        arms[0].rotateAngleY = arms[1].rotateAngleY = arms[2].rotateAngleY = 0f; 
        arms[0].rotateAngleX = arms[1].rotateAngleX = arms[2].rotateAngleX = -ARM3Z; 
        arms[3].rotateAngleZ  = -ARM1Z;
        arms[4].rotateAngleZ  = -ARM2Z;
        arms[5].rotateAngleZ  = -ARM3Z;
        arms[3].rotateAngleY  = arms[4].rotateAngleY  = arms[5].rotateAngleY  = 0f;
        arms[3].rotateAngleX  = arms[4].rotateAngleX  = arms[5].rotateAngleX  = -ARM3Z;

        tail[0].offsetX = MathHelper.cos(limbSwing * 0.2666f + tailAng[0]) * tailMov[0] * limbSwingAmount / 2f;
        tail[1].offsetX = MathHelper.cos(limbSwing * 0.2666f + tailAng[1]) * tailMov[1] * limbSwingAmount / 2f;
        tail[2].offsetX = MathHelper.cos(limbSwing * 0.2666f + tailAng[2]) * tailMov[2] * limbSwingAmount / 2f;
        tail[3].offsetX = MathHelper.cos(limbSwing * 0.2666f + tailAng[3]) * tailMov[3] * limbSwingAmount / 2f;
        tail[4].offsetX = MathHelper.cos(limbSwing * 0.2666f + tailAng[4]) * tailMov[4] * limbSwingAmount / 2f;
        tail[5].offsetX = MathHelper.cos(limbSwing * 0.2666f + tailAng[5]) * tailMov[5] * limbSwingAmount / 2f;
        tail[6].offsetX = MathHelper.cos(limbSwing * 0.2666f + tailAng[6]) * tailMov[6] * limbSwingAmount / 2f;
	}


	public void postRenderArm(float scale, int hand) {
        arms[hand].postRender(scale);
	}
	
	
	
}
