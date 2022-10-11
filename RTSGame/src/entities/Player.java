package entities;

import org.joml.Vector3f;

import models.TexturedModel;
import main.Window;
import toolbox.KeyListener;

public class Player extends Entity {
	
	private static final float RUN_SPEED = 30;
	private static final float TURN_SPEED = 150;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 40;
	
	private static final float TERRAIN_HEIGHT = 0;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0; 
	private boolean isInAir = false;

	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	@Override
	public void update() {
		move();
		super.update();
	}
	
	public void move() {
		processInput();
		super.increaseRotation(0, currentTurnSpeed * Window.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * Window.getFrameTimeSeconds();
		float dx = distance * (float) Math.sin(Math.toRadians(super.getRotY()));
		float dz = distance * (float) Math.cos(Math.toRadians(super.getRotY()));
		super.increasePosition(dx, 0, dz);
		upwardsSpeed += GRAVITY * Window.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * Window.getFrameTimeSeconds(), 0);
		
		if (super.getPosition().y < TERRAIN_HEIGHT + this.getScale()) {
			upwardsSpeed = 0;
			isInAir = false;
			super.getPosition().y = TERRAIN_HEIGHT + this.getScale();
		}
		
//		Vector3f normal = terrain.getNormalOfTerrain(super.getPosition().x, super.getPosition().z);	
//		Vector2f rotation = Maths.determineVectorRotations(normal);		
//		super.setRotX(rotation.x);
//		super.setRotZ(rotation.y);
			
	}
	
	private void jump() {
		if (!isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
	}			
	
	private void processInput() {
//		if (KeyListener.isKeyPressed(GLFW_KEY_W)) {
//			this.currentSpeed = RUN_SPEED;
//		} else if (KeyListener.isKeyPressed(GLFW_KEY_S)) {
//			this.currentSpeed = -RUN_SPEED;		
//		} else {
//			this.currentSpeed = 0;
//		}
//		if (KeyListener.isKeyPressed(GLFW_KEY_D)) {
//			this.currentTurnSpeed = -TURN_SPEED;
//		} else if (KeyListener.isKeyPressed(GLFW_KEY_A)) {
//			this.currentTurnSpeed = TURN_SPEED;
//		} else {
//			this.currentTurnSpeed = 0;
//		}
//		if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
//			jump();
//		}
	}

}
 