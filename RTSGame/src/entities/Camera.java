package entities;

import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import toolbox.MouseListener;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch = 25;
	private float yaw = 0;
	private float roll = 0;
	private float FOV = 70;
	
	private Entity player;
	private float distanceFromPlayer = 50;
	private float angleAroundPlayer = 0;
	
	public Camera(Entity player) {
		this.player = player;
	}
	
	public void move() {
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPositionThird(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
	}
    
    // Third-Person Camera
    public void calculateZoom() {
        float zoomLevel = -MouseListener.getScrollY() * 0.6f;
        distanceFromPlayer -= zoomLevel;
        if (distanceFromPlayer < 5.0f) {
            distanceFromPlayer = 5.0f;
        }
        if (distanceFromPlayer > 50.0f) {
            distanceFromPlayer = 50.0f;
        }
    }
    public void calculatePitch() {
         if (MouseListener.mouseButtonDown(0)) {
            float pitchChange = MouseListener.getDy() * 0.1f;
            pitch += pitchChange;
            if (pitch > 65.0f)
            	pitch = 65.0f;
            if (pitch < -20.0f)
            	pitch = -20.0f;
         }
    }
    public void calculateAngleAroundPlayer() {
         if (MouseListener.mouseButtonDown(0)) {
            float angleChange = MouseListener.getDx() * 0.2f;
            angleAroundPlayer -= angleChange;
         }
    }
    private float calculateHorizontalDistance() {
        return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
    }

    private float calculateVerticalDistance() {
        return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
    }
    private void calculateCameraPositionThird(float horizDistance, float verticDistance) {
        float theta = player.getRotY() + angleAroundPlayer;
        float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
        position.x = player.getPosition().x - offsetX;
        position.z = player.getPosition().z - offsetZ;
        position.y = player.getPosition().y + verticDistance;
    }

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	public float getFOV() {
		return FOV;
	}
	
	public Matrix4f createViewMatrix() {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.identity();
		viewMatrix.rotate((float) Math.toRadians(getPitch()), new Vector3f(1, 0, 0));
		viewMatrix.rotate((float) Math.toRadians(getYaw()), new Vector3f(0, 1, 0));
		Vector3f cameraPos = getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
		viewMatrix.translate(negativeCameraPos);
		return viewMatrix;
	}
	
	

}
