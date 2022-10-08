package entities;

import org.joml.Vector3f;

import interaction.BoundingBox;
import interaction.BoundingSphere;
import interaction.Bounds;
import models.TexturedModel;

/**
 * Abstract class used to define the structure of an Entity. Keeps track of the entity's position,
 * rotation, and scale. Also possesses a TexturedModel which is used for rendering purposes. Contains 
 * XXXX abstract methods that are used for XXXXX.
 * 
 * @author michaelrichardson
 */
public class Entity {
	
	private TexturedModel model;
	private int textureIndex;

	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	
	private Bounds bounds = null;
	
	/**
	 * Default constructor for an entity with its TexturedModel and transformation data
	 */
	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
	
	/**
	 * Used for TextureModels that use multiple unique textures
	 */
	public Entity(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		this.model = model;
		this.textureIndex = index;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	} 
	
	public void update() {
		getBounds().setPosition(this.position);
		getBounds().updateMinMax();
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}
	
	public void setBounds(Vector3f dimensions) {
		this.bounds = new BoundingBox(this, dimensions);
	}
	
	public void setBounds(float radius) {
		this.bounds = new BoundingSphere(radius);
	}
	
	public Bounds getBounds() {
		return this.bounds;
	}
	
	public float getTextureXOffset() {
		int column = textureIndex%model.getTexture().getNumberOfRows();
		return (float) column/(float)model.getTexture().getNumberOfRows();
	}
	
	public float getTextureYOffset() {
		int row = textureIndex/model.getTexture().getNumberOfRows();
		return (float) row/(float)model.getTexture().getNumberOfRows();
	}
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	
	
}
