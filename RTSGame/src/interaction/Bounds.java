package interaction;

import org.joml.Vector3f;

public abstract class Bounds {
	
	protected Vector3f position;	
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public abstract void updateMinMax();
}
