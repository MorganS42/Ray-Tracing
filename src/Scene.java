import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Scene {
	static final ArrayList<EntityObject> objects = new ArrayList<EntityObject>();
	static final ArrayList<LightSource> lights = new ArrayList<LightSource>();
	
	static final ArrayList<ArrayList<EntityObject>> scenes = new ArrayList<ArrayList<EntityObject>>();
	
	public static final int maxDepth = 4;

	Scene() {
	}
	
	void initializeScenes() {
		scenes.add(new ArrayList<EntityObject>(Arrays.asList(
			new Plane(0,600,0,PlaneAxis.Y,new Color(255,255,255),0.5,true, 300),//new Color(0,100,255)));
			new Plane(0,-1000,0,PlaneAxis.Y,new Color(0,0,255)),
			new Plane(-1500,0,0,PlaneAxis.X,new Color(0,255,0)),
			new Plane(1500,0,0,PlaneAxis.X,new Color(255,0,0)),
			new Plane(0,0,2500,PlaneAxis.Z,new Color(255,255,255)),
			new Plane(0,0,-200,PlaneAxis.Z,new Color(255,255,0),0,false, 300),
				
			new LightSource(-1000,0,2000, new Color(0,0,255),1e7),
			new LightSource(0,0,0, new Color(255,0,0),1e7),
			new LightSource(-1000,400,0, new Color(255,255,255),2e6),
				
			new Sphere(0,400,2000,200, new Color(255,0,255),0.5),
			new Sphere(500,300,1800,300, new Color(255,255,0),1,0),
			new Sphere(-500,200,1000,400, new Color(0,255,0),0.7,0),
			new Sphere(-800,450,400,150, new Color(0,255,255),0.5,0),
			new Sphere(1200,600,1000,500, new Color(0,255,0),0.7,0)
		)));
		
		scenes.add(new ArrayList<EntityObject>(Arrays.asList(
			new Plane(0,600,0,PlaneAxis.Y,new Color(255,255,255),0.5,true, 300),//new Color(0,100,255)));
				
			new LightSource(0,0,0, new Color(255,0,0),2e7),
				
			new Sphere(0,400,2000,200, new Color(255,0,255),0.5),
			new Sphere(500,300,1800,300, new Color(255,255,0),1,0)
		)));
	}
	
	void createScene() {
		for(EntityObject obj : scenes.get(Main.sceneToRender)) {
			if(obj.getClass() == LightSource.class) {
				lights.add((LightSource) obj);
			}
			else {
				objects.add(obj);
			}
		}
	}
	
	void renderScene() {
		for(int x = 0; x<Window.width; x++) {
			for(int y = 0; y<Window.height; y++) {
				double[] nv = Main.vectorNormalize(Main.screen.x + x - Main.camera.x,Main.screen.y + y - Main.camera.y, Main.screen.z - Main.camera.z);
				Window.screen[x][y] = (
						new Ray(Main.screen.x + x-Window.width/2,Main.screen.y + y-Window.height/2,Main.screen.z, nv,1)
				).run();
			}
		}
		
		Main.window.draw.repaint();
	}
}
