package main;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joml.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import loader.Loader;
import loader.OBJLoader;
import models.RawModel;
import models.TexturedModel;
import renderer.MasterRenderer;
import textures.ModelTexture;
import toolbox.KeyListener;

/**
 * Main game loop class that is called from the Window singleton and used to 
 * start(), update(), and stop() the program. The update() method is called each
 * frame and will evaluate all processes before rendering the scene. 
 * 
 * @author michaelrichardson
 */
public class MainGameLoop {
	
	Loader loader = new Loader();
	Random random = new Random(5666778); // seed
	
    // Lights
	List<Light> lights = new ArrayList<Light>();
	
	// Player
	Player player = createNewPlayer("pineTree", "pineTree", 0,0,0);
	
	// Entities
	
	// Terrain
	Entity terrain1 = createNewEntity("squareTerrain", "mintGreen", 0, 0, 0);
	
    // Camera
    Camera camera = new Camera(player);
    
    // Renderer
    MasterRenderer renderer = new MasterRenderer(loader, camera);
	
	public void start() {
		
    	// Lights
        Light sun = new Light(new Vector3f(1000, 1000, 1000), new Vector3f(252/350f, 212/350f, 64/350f));
        Light light1 = new Light(new Vector3f(0, 15, 70), new Vector3f(0.8f, 0.8f, 0.8f), new Vector3f(1, 0.01f, 0.002f));
        Light light2 = new Light(new Vector3f(70, 15, 0), new Vector3f(0, 0f, 4f), new Vector3f(1, 0.01f, 0.002f));
        lights.add(sun);
        lights.add(light1);
        lights.add(light2);
        
        // Player
    	player.getModel().getTexture().setReflectivity(1.0f);
    	player.getModel().getTexture().setShineDamper(0.2f);
    	player.increasePosition(0.0f, player.getScale(), 0.0f);
    	player.setBounds(new Vector3f(player.getScale()*2,player.getScale()*2,player.getScale()*2));
		
    	// Entities

    	// Terrain
    	
    	
	}
	
	public void update() {
        processInput();
        player.update();
        
        camera.move();

        renderer.processEntity(player);
        renderer.processEntity(terrain1);
        
        renderer.render(lights, camera);
        
	}
	
	public void stop() {
		
	}
	
	private Entity createNewEntity(String objFile, String texFile, float posX, float posY, float posZ) {
		RawModel entityModel = OBJLoader.loadObjModel(objFile, loader);
	    TexturedModel entityTexturedModel = new TexturedModel(entityModel, new ModelTexture(loader.loadTexture(texFile)));
	    Entity entity = new Entity(entityTexturedModel, new Vector3f(posX,posY,posZ),0,0,0,1);
	    return entity;
	}
	
	private Player createNewPlayer(String objFile, String texFile, float posX, float posY, float posZ) {
		RawModel entityModel = OBJLoader.loadObjModel(objFile, loader);
	    TexturedModel entityTexturedModel = new TexturedModel(entityModel, new ModelTexture(loader.loadTexture(texFile)));
	    Player player = new Player(entityTexturedModel, new Vector3f(posX,posY,posZ),0,0,0,1);
	    return player;
	}
	
	private void processInput() {
		
		if (KeyListener.isKeyPressed(GLFW_KEY_F)) {
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE); // face (front, back, both), type (fill, line, point)
        } else {
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        }

	}

}
