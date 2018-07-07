package com.gw.dm.util;

import com.gw.dm.DungeonMobs;
import net.minecraft.util.MovementInput;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class InputConfusedMovement extends MovementInput {
	protected MovementInput underlyingMovementInput;
	private boolean confused = false;
	private int confValue = 1;

	public InputConfusedMovement(MovementInput interceptedInput) {
		underlyingMovementInput = interceptedInput;
		confValue = 1;
	}

	@Override
	public void updatePlayerMoveState() {
		underlyingMovementInput.updatePlayerMoveState();

		this.jump = underlyingMovementInput.jump;
		this.sneak = underlyingMovementInput.sneak;
		this.moveStrafe = underlyingMovementInput.moveStrafe;
		this.moveForward = underlyingMovementInput.moveForward;

		if (this.confused) {
			switch (this.confValue) {
				case (1):
					this.moveStrafe = -underlyingMovementInput.moveStrafe;
					break;
				case (2):
					this.moveStrafe = -underlyingMovementInput.moveStrafe;
					this.moveForward = -underlyingMovementInput.moveForward;
					break;
				case (3):
					this.moveStrafe = -underlyingMovementInput.moveStrafe;
					this.moveForward = -underlyingMovementInput.moveForward;
					this.jump = underlyingMovementInput.sneak;
					this.sneak = underlyingMovementInput.jump;
					break;
				default:
					this.moveStrafe = -underlyingMovementInput.moveStrafe;
					break;
			}
		}
	}


	public void setConfusion(boolean b) {
		confused = b;
	}


	public void setConfValue(int i) {
		confValue = i;
	}


	public void randomize() {
		if (confused) {
			underlyingMovementInput.updatePlayerMoveState();

			this.jump = underlyingMovementInput.jump;
			this.sneak = underlyingMovementInput.sneak;
			this.moveStrafe = underlyingMovementInput.moveStrafe;
			this.moveForward = underlyingMovementInput.moveForward;

			Random rand = new Random();

			int foo = rand.nextInt(8);

			switch (foo) {
				case (0):
					this.moveStrafe = underlyingMovementInput.moveStrafe;
					this.moveForward = underlyingMovementInput.moveForward;
					break;
				case (1):
					this.moveStrafe = -underlyingMovementInput.moveStrafe;
					this.moveForward = underlyingMovementInput.moveForward;
					break;
				case (2):
					this.moveStrafe = underlyingMovementInput.moveStrafe;
					this.moveForward = -underlyingMovementInput.moveForward;
					break;
				case (3):
					this.moveStrafe = -underlyingMovementInput.moveStrafe;
					this.moveForward = -underlyingMovementInput.moveForward;
					break;
				case (4):
					this.moveStrafe = underlyingMovementInput.moveForward;
					this.moveForward = underlyingMovementInput.moveStrafe;
					break;
				case (5):
					this.moveStrafe = -underlyingMovementInput.moveForward;
					this.moveForward = underlyingMovementInput.moveStrafe;
					break;
				case (6):
					this.moveStrafe = underlyingMovementInput.moveForward;
					this.moveForward = -underlyingMovementInput.moveStrafe;
					break;
				case (7):
					this.moveStrafe = -underlyingMovementInput.moveForward;
					this.moveForward = -underlyingMovementInput.moveStrafe;
					break;
				default:
					this.moveStrafe = underlyingMovementInput.moveForward;
					this.moveForward = -underlyingMovementInput.moveStrafe;
					break;
			}

			foo = rand.nextInt(2);

			if (foo == 1) {
				this.jump = underlyingMovementInput.jump;
				this.sneak = underlyingMovementInput.sneak;
			} else {
				this.jump = underlyingMovementInput.sneak;
				this.sneak = underlyingMovementInput.jump;
			}
		}
	}
}
