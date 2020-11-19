import java.awt.Color;
import java.util.ArrayList;

public class Scene {
	static final ArrayList<EntityObject> objects = new ArrayList<EntityObject>();
	
	Scene() {
		createScene();
		renderScene();
	}
	
	public static int toColorRange(double n) {
		return (int)Math.min(Math.max(n, 0),255);
	}
	
	void createScene() {
		objects.add(new Sphere(200,200,0,50, new Color(0,0,0)));
		//objects.add(new Sphere(300,200,0,100, new Color(0,0,0)));
		//objects.add(new Plane(0,600,0,PlaneAxis.Y,new Color(0,100,255)));
	}
	
	void renderScene() {
		for(int x = 0; x<Window.width; x++) {
			for(int y = 0; y<Window.height; y++) {
				Window.screen[x][y] = (new Ray(Camera.x,Camera.y,Camera.z, x - Camera.x, y - Camera.y, 0 - Camera.z)).run();
			}
		}
	}
}
