package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class ModelHissingDemon extends ModelBase {
	private static final float[] tailAng;
	private static final float[] tailMov;
	
	private static final float ARM1Z = (float) ((Math.PI / 2) + (Math.PI / 4)); 
	private static final float ARM2Z = (float) Math.PI / 2;
	private static final float ARM3Z = (float) Math.PI / 4;
	
	private final ModelRenderer hbone;
	private final ModelRenderer ab1;
	private final ModelRenderer arm1_r1;
	private final ModelRenderer w1;
	private final ModelRenderer ab2;
	private final ModelRenderer arm2_r1;
	private final ModelRenderer w2;
	private final ModelRenderer ab3;
	private final ModelRenderer arm3_r1;
	private final ModelRenderer w3;
	private final ModelRenderer w3_a3_r1;
	private final ModelRenderer ab4;
	private final ModelRenderer arm4_r1;
	private final ModelRenderer w4;
	private final ModelRenderer w3_a4_r1;
	private final ModelRenderer hilt_a4_r1;
	private final ModelRenderer ab5;
	private final ModelRenderer arm5_r1;
	private final ModelRenderer w5;
	private final ModelRenderer ab6;
	private final ModelRenderer arm6_r1;
	private final ModelRenderer w6;
	private final ModelRenderer t1;
	private final ModelRenderer t2;
	private final ModelRenderer t3;
	private final ModelRenderer t4;
	private final ModelRenderer t5;
	private final ModelRenderer t6;
	private final ModelRenderer t7;
	private final ModelRenderer bb_main;
	
    public ModelRenderer[] arms;
    public ModelRenderer[] tail;
    
    
    static {
    	tailAng = new float[] {(float)(Math.PI * 0.25), (float)(Math.PI * 0.5), (float)(Math.PI * 0.75), 
    			(float)(Math.PI ), (float)(Math.PI * 1.25), (float)(Math.PI * 1.5), (float)(Math.PI * 1.75)};
    	tailMov = new float[] {0.5f, 1.0f, 1.1f, 1.2f, 1.1f, 1.0f, 0.75f};
    }

    
	public ModelHissingDemon() {
		textureWidth = 128;
		textureHeight = 128;

		hbone = new ModelRenderer(this);
		hbone.setRotationPoint(0.0F, -9.0F, 3.0F);
		hbone.cubeList.add(new ModelBox(hbone, 0, 0, -6.0F, -12.0F, -6.0F, 12, 12, 12, 0.0F, false));

		ab1 = new ModelRenderer(this);
		ab1.setRotationPoint(-6.0F, -8.0F, 3.0F);
		setRotationAngle(ab1, 0.0436F, -0.7854F, 0.0F);
		

		arm1_r1 = new ModelRenderer(this);
		arm1_r1.setRotationPoint(0.0F, 1.0F, 0.0F);
		ab1.addChild(arm1_r1);
		setRotationAngle(arm1_r1, 0.0F, 0.0F, 2.3562F);
		arm1_r1.cubeList.add(new ModelBox(arm1_r1, 0, 61, -2.5F, -2.0F, -2.25F, 4, 18, 4, 0.0F, false));

		w1 = new ModelRenderer(this);
		w1.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm1_r1.addChild(w1);
		w1.cubeList.add(new ModelBox(w1, 78, 1, -3.0F, 14.0F, -1.0F, 6, 1, 1, 0.0F, false));
		w1.cubeList.add(new ModelBox(w1, 91, 10, -4.0F, 14.0F, -4.0F, 1, 1, 7, 0.0F, false));
		w1.cubeList.add(new ModelBox(w1, 88, 4, -16.0F, 14.0F, -2.0F, 12, 1, 3, 0.0F, false));
		w1.cubeList.add(new ModelBox(w1, 120, 6, -17.0F, 14.0F, -1.0F, 1, 1, 1, 0.0F, false));

		ab2 = new ModelRenderer(this);
		ab2.setRotationPoint(-6.0F, -4.0F, 2.0F);
		setRotationAngle(ab2, 0.0F, -0.7854F, 0.0F);
		

		arm2_r1 = new ModelRenderer(this);
		arm2_r1.setRotationPoint(0.0F, -0.5F, 1.0F);
		ab2.addChild(arm2_r1);
		setRotationAngle(arm2_r1, 0.0F, 0.0F, 1.5708F);
		arm2_r1.cubeList.add(new ModelBox(arm2_r1, 21, 61, -1.75F, 0.25F, -2.25F, 4, 18, 4, 0.0F, false));

		w2 = new ModelRenderer(this);
		w2.setRotationPoint(-2.0F, 15.5F, 0.0F);
		arm2_r1.addChild(w2);
		w2.cubeList.add(new ModelBox(w2, 78, 1, -6.0F, 0.0F, -1.0F, 11, 1, 1, 0.0F, false));
		w2.cubeList.add(new ModelBox(w2, 90, 20, -10.0F, 0.0F, -4.0F, 4, 1, 4, 0.0F, false));
		w2.cubeList.add(new ModelBox(w2, 90, 25, -10.0F, 0.0F, -6.0F, 6, 1, 2, 0.0F, false));

		ab3 = new ModelRenderer(this);
		ab3.setRotationPoint(-5.0F, -3.0F, 3.0F);
		setRotationAngle(ab3, 0.0F, -0.7854F, 0.0F);
		

		arm3_r1 = new ModelRenderer(this);
		arm3_r1.setRotationPoint(-1.0F, 1.0F, 0.0F);
		ab3.addChild(arm3_r1);
		setRotationAngle(arm3_r1, 0.0F, 0.0F, 0.7854F);
		arm3_r1.cubeList.add(new ModelBox(arm3_r1, 0, 61, -2.25F, -1.25F, -2.25F, 4, 18, 5, 0.0F, false));

		w3 = new ModelRenderer(this);
		w3.setRotationPoint(0.0F, 14.0F, -3.0F);
		arm3_r1.addChild(w3);
		

		w3_a3_r1 = new ModelRenderer(this);
		w3_a3_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		w3.addChild(w3_a3_r1);
		setRotationAngle(w3_a3_r1, 0.0F, -1.5708F, 0.0F);
		w3_a3_r1.cubeList.add(new ModelBox(w3_a3_r1, 116, 31, -14.0F, 0.0F, -1.0F, 1, 1, 1, 0.0F, false));
		w3_a3_r1.cubeList.add(new ModelBox(w3_a3_r1, 84, 31, -13.0F, 0.0F, -2.0F, 12, 1, 3, 0.0F, false));
		w3_a3_r1.cubeList.add(new ModelBox(w3_a3_r1, 91, 10, -1.0F, 0.0F, -4.0F, 1, 1, 7, 0.0F, false));
		w3_a3_r1.cubeList.add(new ModelBox(w3_a3_r1, 78, 1, 0.0F, 0.0F, -1.0F, 6, 1, 1, 0.0F, false));

		ab4 = new ModelRenderer(this);
		ab4.setRotationPoint(6.0F, -7.0F, 3.0F);
		setRotationAngle(ab4, 0.0F, 0.7854F, 0.0F);
		

		arm4_r1 = new ModelRenderer(this);
		arm4_r1.setRotationPoint(0.0F, 0.0F, -3.0F);
		ab4.addChild(arm4_r1);
		setRotationAngle(arm4_r1, 0.0F, 0.0F, -2.3562F);
		arm4_r1.cubeList.add(new ModelBox(arm4_r1, 0, 61, -2.0F, -2.0F, 0.75F, 4, 18, 5, 0.0F, false));

		w4 = new ModelRenderer(this);
		w4.setRotationPoint(-1.0F, 15.0F, 3.0F);
		arm4_r1.addChild(w4);
		

		w3_a4_r1 = new ModelRenderer(this);
		w3_a4_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		w4.addChild(w3_a4_r1);
		setRotationAngle(w3_a4_r1, 3.1416F, 0.0F, 0.0F);
		w3_a4_r1.cubeList.add(new ModelBox(w3_a4_r1, 88, 45, 7.0F, 0.0F, 4.0F, 6, 1, 2, 0.0F, false));
		w3_a4_r1.cubeList.add(new ModelBox(w3_a4_r1, 88, 38, 9.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F, false));

		hilt_a4_r1 = new ModelRenderer(this);
		hilt_a4_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		w4.addChild(hilt_a4_r1);
		setRotationAngle(hilt_a4_r1, -1.5708F, 0.0F, 0.0F);
		hilt_a4_r1.cubeList.add(new ModelBox(hilt_a4_r1, 78, 1, -2.0F, 0.0F, -1.0F, 11, 1, 1, 0.0F, false));

		ab5 = new ModelRenderer(this);
		ab5.setRotationPoint(7.0F, -4.0F, 3.0F);
		setRotationAngle(ab5, 0.0F, 0.7854F, 0.0F);
		

		arm5_r1 = new ModelRenderer(this);
		arm5_r1.setRotationPoint(-1.0F, -0.5F, -3.0F);
		ab5.addChild(arm5_r1);
		setRotationAngle(arm5_r1, 0.0F, 0.0F, -1.5708F);
		arm5_r1.cubeList.add(new ModelBox(arm5_r1, 21, 61, -1.75F, 0.25F, 0.75F, 4, 18, 4, 0.0F, false));

		w5 = new ModelRenderer(this);
		w5.setRotationPoint(4.0F, 16.5F, 2.0F);
		arm5_r1.addChild(w5);
		w5.cubeList.add(new ModelBox(w5, 78, 1, -7.0F, -1.0F, 0.0F, 6, 1, 1, 0.0F, false));
		w5.cubeList.add(new ModelBox(w5, 91, 10, -1.0F, -1.0F, -3.0F, 1, 1, 7, 0.0F, false));
		w5.cubeList.add(new ModelBox(w5, 88, 4, 0.0F, -1.0F, -1.0F, 12, 1, 3, 0.0F, false));
		w5.cubeList.add(new ModelBox(w5, 120, 6, 12.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));

		ab6 = new ModelRenderer(this);
		ab6.setRotationPoint(4.0F, -3.0F, 0.0F);
		setRotationAngle(ab6, 0.0F, 0.7854F, 0.0F);
		

		arm6_r1 = new ModelRenderer(this);
		arm6_r1.setRotationPoint(2.0F, 1.0F, 3.0F);
		ab6.addChild(arm6_r1);
		setRotationAngle(arm6_r1, 0.0F, 0.0F, -0.7854F);
		arm6_r1.cubeList.add(new ModelBox(arm6_r1, 0, 61, -2.25F, -2.25F, -2.25F, 4, 18, 5, 0.0F, false));

		w6 = new ModelRenderer(this);
		w6.setRotationPoint(-1.0F, 14.0F, -4.0F);
		arm6_r1.addChild(w6);
		setRotationAngle(w6, 0.0F, 1.5708F, 0.0F);
		w6.cubeList.add(new ModelBox(w6, 78, 1, -7.0F, -1.0F, 0.0F, 6, 1, 1, 0.0F, false));
		w6.cubeList.add(new ModelBox(w6, 91, 10, -1.0F, -1.0F, -3.0F, 1, 1, 7, 0.0F, false));
		w6.cubeList.add(new ModelBox(w6, 88, 4, 0.0F, -1.0F, -1.0F, 12, 1, 3, 0.0F, false));
		w6.cubeList.add(new ModelBox(w6, 120, 6, 12.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));

		t1 = new ModelRenderer(this);
		t1.setRotationPoint(0.0F, 15.0F, 6.0F);
		t1.cubeList.add(new ModelBox(t1, 43, 17, -5.0F, -4.0F, -2.0F, 10, 8, 8, 0.0F, false));

		t2 = new ModelRenderer(this);
		t2.setRotationPoint(0.0F, 20.0F, 10.0F);
		t2.cubeList.add(new ModelBox(t2, 0, 44, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));

		t3 = new ModelRenderer(this);
		t3.setRotationPoint(0.0F, 24.0F, 14.0F);
		t3.cubeList.add(new ModelBox(t3, 51, 0, -4.0F, -8.0F, 0.0F, 8, 8, 8, 0.0F, false));

		t4 = new ModelRenderer(this);
		t4.setRotationPoint(0.0F, 24.0F, 24.0F);
		t4.cubeList.add(new ModelBox(t4, 40, 63, -3.0F, -6.0F, -2.0F, 6, 6, 6, 0.0F, false));

		t5 = new ModelRenderer(this);
		t5.setRotationPoint(0.0F, 24.0F, 28.0F);
		t5.cubeList.add(new ModelBox(t5, 65, 63, -2.0F, -4.0F, 0.0F, 4, 4, 4, 0.0F, false));

		t6 = new ModelRenderer(this);
		t6.setRotationPoint(0.0F, 24.0F, 32.0F);
		t6.cubeList.add(new ModelBox(t6, 39, 0, -1.5F, -3.0F, 0.0F, 3, 3, 3, 0.0F, false));

		t7 = new ModelRenderer(this);
		t7.setRotationPoint(0.0F, 24.0F, 35.0F);
		t7.cubeList.add(new ModelBox(t7, 0, 0, -1.0F, -2.0F, 0.0F, 2, 2, 2, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 25, -7.0F, -19.0F, 1.0F, 14, 9, 9, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 41, 38, -6.0F, -33.0F, 0.0F, 12, 18, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 27, 44, -5.0F, -30.0F, -1.5F, 4, 3, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 27, 44, 1.0F, -30.0F, -1.5F, 4, 3, 2, 0.0F, true));
	}
	

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		hbone.render(f5);
		ab1.render(f5);
		ab2.render(f5);
		ab3.render(f5);
		ab4.render(f5);
		ab5.render(f5);
		ab6.render(f5);
		t1.render(f5);
		t2.render(f5);
		t3.render(f5);
		t4.render(f5);
		t5.render(f5);
		t6.render(f5);
		t7.render(f5);
		bb_main.render(f5);
	}
	

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	


	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
	                              float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		hbone.rotateAngleY = netHeadYaw * 0.017453292F;
		hbone.rotateAngleX = headPitch * 0.017453292F;
		
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
