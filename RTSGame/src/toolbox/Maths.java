package toolbox;

import org.joml.*;
import org.joml.Math;

public class Maths {

	/**
	 * Entities
	 */
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.translate(translation);
		matrix.rotate(Math.toRadians(rx), new Vector3f(1, 0, 0));
		matrix.rotate(Math.toRadians(ry), new Vector3f(0, 1, 0));
		matrix.rotate(Math.toRadians(rz), new Vector3f(0, 0, 1));
		matrix.scale(scale);
		return matrix;
	}
	
	/**
	 * GUIs
	 */
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		matrix.translate(translation.x, translation.y, 0.0f);
		matrix.scale(new Vector3f(scale.x, scale.y, 1f));
		return matrix;
	}
	


	
}
