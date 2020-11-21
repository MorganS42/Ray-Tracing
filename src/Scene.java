import java.awt.Color;
import java.util.ArrayList;

public class Scene {
	static final ArrayList<EntityObject> objects = new ArrayList<EntityObject>();
	static final ArrayList<LightSource> lights = new ArrayList<LightSource>();
	
	public static final int maxDepth = 4;

	Scene() {
	}
	
	void createScene() {
		objects.add(new Sphere(0,400,2000,200, new Color(255,0,255),0.5));
		objects.add(new Sphere(500,300,1800,300, new Color(255,255,0),1,0));
		
		lights.add(new LightSource(0,0,0, new Color(255,255,255),2e7));
		//lights.add(new LightSource(1000,0,2000, new Color(0,255,255),5e6));
		
		//lights.add(new LightSource(400,-400,2000, new Color(0,0,255)));
		//objects.add(new Sphere(300,200,0,100, new Color(0,0,0)));
		
		objects.add(new Plane(0,600,0,PlaneAxis.Y,new Color(255,255,255),0.5,true, 300));//new Color(0,100,255)));
		objects.add(new Plane(0,-1000,0,PlaneAxis.Y,new Color(0,0,255)));
		objects.add(new Plane(-1500,0,0,PlaneAxis.X,new Color(0,255,0)));
		objects.add(new Plane(1500,0,0,PlaneAxis.X,new Color(255,0,0)));
		objects.add(new Plane(0,0,2500,PlaneAxis.Z,new Color(255,255,255)));
		objects.add(new Plane(0,0,-200,PlaneAxis.Z,new Color(255,0,255),0,true, 300));
		//objects.add(new Plane(0,0,18000,PlaneAxis.Z,new Color(255,0,255),0,true, 300));
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
