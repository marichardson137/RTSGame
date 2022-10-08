package interaction;


public class BoundingSphere extends Bounds {
	
	private float radius;
	
	public BoundingSphere(float radius) {
		this.radius = radius;
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	@Override
	public void updateMinMax() {
		
	}

}
