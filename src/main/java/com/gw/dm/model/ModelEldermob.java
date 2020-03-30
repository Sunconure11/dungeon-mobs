package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

import java.util.Random;

public class ModelEldermob extends ModelBase {
	public ModelRenderer body;
	//TODO: Tentacles
	public ModelRenderer[][] tenticles;
	public float[][][] tenticlePos;
	public float[][] tenticleMove;
	private Random tenticleRandom;
	
	public ModelEldermob() {
		textureWidth = 128;
		textureHeight = 96;
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-16f, -8f, -16f, 32, 32, 32);
		body.setRotationPoint(0.0f, 0.0f, 0.0f);
		
		tenticleRandom = new Random(System.nanoTime());
		
		tenticles = new ModelRenderer[24][];
		tenticlePos = new float[24][4][3];
		tenticleMove = new float[24][13];
		
		tenticles[0] = makeTenticle(-14f, -6f, 16f, 0, 0, 1, tenticlePos[0], tenticleMove[0]);
		tenticles[1] = makeTenticle(6f, -6f, 16f, 0, 0, 1, tenticlePos[1], tenticleMove[1]);
		tenticles[2] = makeTenticle(6f, 14f, 16f, 0, 0, 1, tenticlePos[2], tenticleMove[2]);
		tenticles[3] = makeTenticle(-14f, 14f, 16f, 0, 0, 1, tenticlePos[3], tenticleMove[3]);
		
		tenticles[4] = makeTenticle(-14f, -6f, -22f, 0, 0, -1, tenticlePos[4], tenticleMove[4]);
		tenticles[5] = makeTenticle(6f, -6f, -22f, 0, 0, -1, tenticlePos[5], tenticleMove[5]);
		tenticles[6] = makeTenticle(6f, 14f, -22f, 0, 0, -1, tenticlePos[6], tenticleMove[6]);
		tenticles[7] = makeTenticle(-14f, 14f, -22f, 0, 0, -1, tenticlePos[7], tenticleMove[7]);
		
		tenticles[8] = makeTenticle(16f, -6f, -14f, 1, 0, 0, tenticlePos[8], tenticleMove[8]);
		tenticles[9] = makeTenticle(16f, 14f, -14f, 1, 0, 0, tenticlePos[9], tenticleMove[9]);
		tenticles[10] = makeTenticle(16f, 14f, 6f, 1, 0, 0, tenticlePos[10], tenticleMove[10]);
		tenticles[11] = makeTenticle(16f, -6f, 6f, 1, 0, 0, tenticlePos[11], tenticleMove[11]);
		
		tenticles[12] = makeTenticle(-22f, -6f, -14f, -1, 0, 0, tenticlePos[12], tenticleMove[12]);
		tenticles[13] = makeTenticle(-22f, 14f, -14f, -1, 0, 0, tenticlePos[13], tenticleMove[13]);
		tenticles[14] = makeTenticle(-22f, 14f, 6f, -1, 0, 0, tenticlePos[14], tenticleMove[14]);
		tenticles[15] = makeTenticle(-22f, -6f, 6f, -1, 0, 0, tenticlePos[15], tenticleMove[15]);
		
		tenticles[16] = makeTenticle(-14f, 22f, -14f, 0, 1, 0, tenticlePos[16], tenticleMove[16]);
		tenticles[17] = makeTenticle(6f, 22f, -14f, 0, 1, 0, tenticlePos[17], tenticleMove[17]);
		tenticles[18] = makeTenticle(6f, 22f, 6f, 0, 1, 0, tenticlePos[18], tenticleMove[18]);
		tenticles[19] = makeTenticle(-14f, 22f, 6f, 0, 1, 0, tenticlePos[19], tenticleMove[19]);
		
		tenticles[20] = makeTenticle(-14f, -16f, -14f, 0, -1, 0, tenticlePos[20], tenticleMove[20]);
		tenticles[21] = makeTenticle(6f, -16f, -14f, 0, -1, 0, tenticlePos[21], tenticleMove[21]);
		tenticles[22] = makeTenticle(6f, -16f, 6f, 0, -1, 0, tenticlePos[22], tenticleMove[22]);
		tenticles[23] = makeTenticle(-14f, -16f, 6f, 0, -1, 0, tenticlePos[23], tenticleMove[23]);
	}
	
	
	private ModelRenderer[] makeTenticle(float x, float y, float z, float xf, float yf, float zf, float[][] pos, float[] move) {
		move[0] = tenticleRandom.nextFloat() + 1f;
		move[0] -= move[0] * xf;
		move[1] = tenticleRandom.nextFloat() * (float) Math.PI;
		move[1] -= move[1] * xf;
		move[2] = tenticleRandom.nextFloat() + 1f;
		move[2] -= move[2] * yf;
		move[3] = tenticleRandom.nextFloat() * (float) Math.PI;
		move[3] -= move[3] * yf;
		move[4] = tenticleRandom.nextFloat() + 1f;
		move[4] -= move[4] * zf;
		move[5] = tenticleRandom.nextFloat() * (float) Math.PI;
		move[5] -= move[5] * zf;
		move[6] = tenticleRandom.nextFloat() * 0.05f;
		move[7] = 0.15f + tenticleRandom.nextFloat() * 0.05f;
		move[8] = 0.225f + tenticleRandom.nextFloat() * 0.075f;
		move[9] = 0.3f + tenticleRandom.nextFloat() * 0.1f;
		move[10] = 0.75f + tenticleRandom.nextFloat();
		move[11] = 0.75f + tenticleRandom.nextFloat();
		move[12] = 0.75f + tenticleRandom.nextFloat();
		ModelRenderer[] out = new ModelRenderer[4];
		//Tenticle base / segment 0
		pos[0][0] = x;
		pos[0][1] = y;
		pos[0][2] = z;
		out[0] = new ModelRenderer(this, 0, 80);
		out[0].addBox(x, y, z, 8, 8, 8);
		out[0].setRotationPoint(0.0f, 0.0f, 0.0f);
		//Tenticle segment 1
		pos[1][0] = x + (7 * xf) + 1;
		pos[1][1] = y + (7 * yf) + 1;
		pos[1][2] = z + (7 * zf) + 1;
		out[1] = new ModelRenderer(this, 32, 84);
		out[1].addBox(x + (7 * xf) + 1, y + (7 * yf) + 1, z + (7 * zf) + 1, 6, 6, 6);
		out[1].setRotationPoint(0.0f, 0.0f, 0.0f);
		//Tenticle segment 2
		pos[2][0] = x + (12 * xf) + 2;
		pos[2][1] = y + (12 * yf) + 2;
		pos[2][2] = z + (12 * zf) + 2;
		out[2] = new ModelRenderer(this, 56, 88);
		out[2].addBox(x + (12 * xf) + 2, y + (12 * yf) + 2, z + (12 * zf) + 2, 4, 4, 4);
		out[2].setRotationPoint(0.0f, 0.0f, 0.0f);
		//Tenticle segment 3
		pos[3][0] = x + (15 * xf) + 3;
		pos[3][1] = y + (15 * yf) + 3;
		pos[3][2] = z + (15 * zf) + 3;
		out[3] = new ModelRenderer(this, 72, 92);
		out[3].addBox(x + (15 * xf) + 3, y + (15 * yf) + 3, z + (15 * zf) + 3, 2, 2, 2);
		out[3].setRotationPoint(0.0f, 0.0f, 0.0f);
		return out;
	}
	
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float headYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.8, 0.8, 0.8);
		body.render(scale);
		renderTenticle(tenticles[0], scale);
		renderTenticle(tenticles[1], scale);
		renderTenticle(tenticles[2], scale);
		renderTenticle(tenticles[3], scale);
		renderTenticle(tenticles[4], scale);
		renderTenticle(tenticles[5], scale);
		renderTenticle(tenticles[6], scale);
		renderTenticle(tenticles[7], scale);
		renderTenticle(tenticles[8], scale);
		renderTenticle(tenticles[9], scale);
		renderTenticle(tenticles[10], scale);
		renderTenticle(tenticles[11], scale);
		renderTenticle(tenticles[12], scale);
		renderTenticle(tenticles[13], scale);
		renderTenticle(tenticles[14], scale);
		renderTenticle(tenticles[15], scale);
		renderTenticle(tenticles[16], scale);
		renderTenticle(tenticles[17], scale);
		renderTenticle(tenticles[18], scale);
		renderTenticle(tenticles[19], scale);
		renderTenticle(tenticles[20], scale);
		renderTenticle(tenticles[21], scale);
		renderTenticle(tenticles[22], scale);
		renderTenticle(tenticles[23], scale);
		GlStateManager.popMatrix();
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		float age = (ageInTicks + tenticleRandom.nextFloat()) / 6f;
		for (int i = 0; i < tenticles.length; i++) {
			moveTenticle(tenticles[i], tenticlePos[i], tenticleMove[i], age);
		}
	}
	
	private void renderTenticle(ModelRenderer[] tenticle, float scale) {
		tenticle[0].render(scale);
		tenticle[1].render(scale);
		tenticle[2].render(scale);
		tenticle[3].render(scale);
	}
	
	private void moveTenticle(ModelRenderer[] tenticle, float[][] initPos, float[] moveDat, float age) {
		tenticle[0].offsetX = (float) (moveDat[6] * moveDat[10] * Math.sin(age * moveDat[0] + moveDat[1]));
		tenticle[0].offsetY = (float) (moveDat[6] * moveDat[11] * Math.sin(age * moveDat[2] + moveDat[3]));
		tenticle[0].offsetZ = (float) (moveDat[6] * moveDat[12] * Math.sin(age * moveDat[4] + moveDat[5]));
		
		tenticle[1].offsetX = (float) (moveDat[7] * moveDat[10] * Math.sin(age * moveDat[0] + moveDat[1]));
		tenticle[1].offsetY = (float) (moveDat[7] * moveDat[11] * Math.sin(age * moveDat[2] + moveDat[3]));
		tenticle[1].offsetZ = (float) (moveDat[7] * moveDat[12] * Math.sin(age * moveDat[4] + moveDat[5]));
		
		tenticle[2].offsetX = (float) (moveDat[8] * moveDat[10] * Math.sin(age * moveDat[0] + moveDat[1]));
		tenticle[2].offsetY = (float) (moveDat[8] * moveDat[11] * Math.sin(age * moveDat[2] + moveDat[3]));
		tenticle[2].offsetZ = (float) (moveDat[8] * moveDat[12] * Math.sin(age * moveDat[4] + moveDat[5]));
		
		tenticle[3].offsetX = (float) (moveDat[9] * moveDat[10] * Math.sin(age * moveDat[0] + moveDat[1]));
		tenticle[3].offsetY = (float) (moveDat[9] * moveDat[11] * Math.sin(age * moveDat[2] + moveDat[3]));
		tenticle[3].offsetZ = (float) (moveDat[9] * moveDat[12] * Math.sin(age * moveDat[4] + moveDat[5]));
	}
	
}
