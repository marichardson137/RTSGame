package renderer;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import entities.Camera;
import entities.Entity;
import entities.Light;
import loader.Loader;
import main.Window;
import models.TexturedModel;
import shaders.StaticShader;

/**
 * The main render class used to manage the sub-renderers and adjust settings for specific objects.
 * 
 * @author michaelrichardson
 */
public class MasterRenderer {
	
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 2000f;
	Matrix4f projectionMatrix = new Matrix4f();
	
	public static final float RED = 0.5444f;
	public static final float GREEN = 0.62f;
	public static final float BLUE = 0.69f;
	
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;

	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	
	public MasterRenderer(Loader loader, Camera camera) {
		enableCulling();
		renderer = new EntityRenderer(shader);
		createProjectionMatrix(camera);
	}
	
	public static void enableCulling() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);	
	}
	
	public static void disableCulling() {
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void render(List<Light> lights, Camera camera) {
		prepare();
		createProjectionMatrix(camera);

		shader.start();
		shader.loadLights(lights);
		shader.loadViewMatrix(camera);
		shader.loadSkyColor(RED, GREEN, BLUE);
		renderer.render(entities);
		shader.stop();

		entities.clear();
	}
	
	public void processEntity(Entity entity) {
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if (batch != null) {
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(RED, GREEN, BLUE, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	private void createProjectionMatrix(Camera camera) {
		projectionMatrix = new Matrix4f();
		projectionMatrix.identity();
		
		projectionMatrix.perspective((float)Math.toRadians(camera.getFOV()), (float)Window.WIDTH / Window.HEIGHT, NEAR_PLANE, FAR_PLANE);
	
//		float xOrtho = 1920.0f / camera.getFOV();
//		float yOrtho = 1080.0f / camera.getFOV();
//		projectionMatrix.ortho(-xOrtho, xOrtho, -yOrtho, yOrtho, -100f, 1000.0f); // left, right, bottom, top, front, back (creates a cubic frustum)
		
		renderer.projectionMatrix = projectionMatrix;

	}
	
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
	public void cleanUp() { 
		shader.cleanUp();
	}
	

}
