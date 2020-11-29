import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Scene {
	static final ArrayList<EntityObject> objects = new ArrayList<EntityObject>();
	static final ArrayList<LightSource> lights = new ArrayList<LightSource>();
	
	static final ArrayList<ArrayList<EntityObject>> scenes = new ArrayList<ArrayList<EntityObject>>();
	
	public static final int maxDepth = 4;

	public static final Color skyColor = new Color(0,0,0);
	
	Scene() {
	}
	
	void initializeScenes() {
		scenes.add(new ArrayList<EntityObject>(Arrays.asList(
			new Plane(0,600,0,PlaneAxis.Y,new Color(255,255,255),0,true, 300),//new Color(0,100,255)));
			//new Plane(0,-1000,0,PlaneAxis.Y,new Color(0,0,255),0),
			//new Plane(-1500,0,0,PlaneAxis.X,new Color(0,255,0),0),
			//new Plane(2000,0,0,PlaneAxis.X,new Color(0,120,255),0),
			//new Plane(0,0,2500,PlaneAxis.Z,new Color(255,255,255),0),
			//new Plane(0,0,-1000,PlaneAxis.Z,new Color(255,255,0),0),
				
			new LightSource(-1000,0,2000, new Color(0,0,255),1e7),
			new LightSource(0,0,0, new Color(0,255,0),1e7),
			new LightSource(-1000,400,0, new Color(255,255,255),2e6),
			new LightSource(1000,-100,1000, new Color(255,0,0),5e6),
				
			new Sphere(0,400,1800,200, new Color(255,0,255),0.5,0,1,0,0,0),
			new Sphere(500,300,1800,300, new Color(255,255,0),1,0,1,10,0,0),
			new Sphere(-500,200,1000,400, new Color(0,255,0),1,0,1,0,0,0),
			new Sphere(-800,450,400,150, new Color(0,255,255),0.5,0,1,0,0,0)
			//new Sphere(1200,1000,1000,500, new Color(0,255,0),0.7,0,1,10,0,-20)
		)));
		
		scenes.add(new ArrayList<EntityObject>(Arrays.asList(
			new Plane(0,600,0,PlaneAxis.Y,new Color(255,255,255),0,true, 300),//new Color(0,100,255)));
			new Plane(0,-1000,0,PlaneAxis.Y,new Color(0,0,255),0),
			new Plane(-1500,0,0,PlaneAxis.X,new Color(0,255,0),0),
			new Plane(2000,0,0,PlaneAxis.X,new Color(0,120,255),0),
			new Plane(0,0,2500,PlaneAxis.Z,new Color(255,255,255),0),
			new Plane(0,0,-1000,PlaneAxis.Z,new Color(255,255,0),0),
				
			new LightSource(-1000,0,2000, new Color(0,0,255),1e7),
			new LightSource(0,0,0, new Color(0,255,0),1e7),
			new LightSource(-1000,400,0, new Color(255,255,255),2e6),
			new LightSource(1000,-100,1000, new Color(255,0,0),5e6),
				
			new Sphere(0,400,1800,200, new Color(255,0,255),0.5,0,1,0,0,0),
			new Sphere(500,300,1800,300, new Color(255,255,0),1,0,1,10,0,0),
			new Sphere(-500,200,1000,400, new Color(0,255,0),1,0,1,0,0,0),
			new Sphere(-800,450,400,150, new Color(0,255,255),0.5,0,1,0,0,0)
			//new Sphere(1200,1000,1000,500, new Color(0,255,0),0.7,0,1,10,0,-20)
		)));
		
		scenes.add(new ArrayList<EntityObject>(Arrays.asList(
			new Plane(0,600,0,PlaneAxis.Y,new Color(255,255,255),0.5,true, 300),//new Color(0,100,255)));
			new Plane(0,-1000,0,PlaneAxis.Y,new Color(0,0,255),0),
			new Plane(-1500,0,0,PlaneAxis.X,new Color(0,255,0),0),
			new Plane(1500,0,0,PlaneAxis.X,new Color(255,0,0),0),
			new Plane(0,0,2500,PlaneAxis.Z,new Color(255,255,255),0),
			//new Plane(0,0,-200,PlaneAxis.Z,new Color(255,255,0),0),
				
			new LightSource(-1000,0,2000, new Color(0,0,255),1e7),
			new LightSource(0,0,0, new Color(255,0,0),1e7),
			new LightSource(-1000,400,0, new Color(255,255,255),2e6),
			new LightSource(1000,-100,1000, new Color(0,255,0),5e6),
				
			new Sphere(0,0,1800,200, new Color(255,0,255),0.5,0,0.9,5,0,5),
			new Sphere(500,300,1800,300, new Color(255,255,0),1,0,0.9,-5,0,10),
			new Sphere(-500,200,1000,400, new Color(0,255,0),0.7,0,0.9,5,0,15),
			new Sphere(-800,450,400,150, new Color(0,255,255),0.5,0,0.9,-5,0,20),
			new Sphere(1200,1000,1000,500, new Color(0,255,0),0.7,0,0.9,5,0,25)
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
	
	Color[][] renderScene() {
		Color[][] frame = new Color[Window.width][Window.height];
		for(int x = 0; x<Window.width; x++) {
			for(int y = 0; y<Window.height; y++) {
				frame[x][y] = new Color(0,0,0);
			}
		}
		
		double theta = Main.screen.xzr;
		double thetaMod = theta % (Math.PI * 2);
		double thetaMod2 = theta % (Math.PI * 4) + Math.PI * 2;
		double dist = Screen.dis;
		
		for(int x = 0; x<Window.width; x++) {
			for(int y = 0; y<Window.height; y++) {
				double r = (Window.width/2 - x);
				double c = (r*Math.sin(thetaMod)) / (Math.sin((Math.PI-thetaMod)/2));
				
				if(thetaMod2 < 0) {
					c = -c;
				}
				
				double a = c/2 * Math.tan((Math.PI - thetaMod)/2);
				double b = c/2 * Math.cos((Math.PI - thetaMod)/2) - r;
				
				double fx = Main.screen.x+x - (b + Math.cos(theta/2 - Math.PI) * dist); //+ Screen.dis * Math.cos(Main.screen.xzr); //final x
				double fy = Main.screen.y+y; //final y
				double fz = Main.screen.z-(a + dist - Math.sin(theta/2) * dist); //- Screen.dis * Math.sin(Main.screen.xzr); //final z
								
				double[] nv = Main.vectorNormalize(fx - Main.camera.x, fy - Main.camera.y, fz - Main.camera.z);
				//(new Ray(fx,fy,fz, nv,1)).run();
				//frame[x][y] = new Color(0,0,0);
				frame[x][y] = (
						new Ray(fx,fy,fz, nv,1)
				).run();
				
				/*frame[(int)Math.max(Math.min(Main.camera.x/10.0 + Window.width/2,Window.width-1),0)]
					 [(int)Math.max(Math.min(Main.camera.z/10.0 + Window.height/2,Window.height-1),0)] = new Color(0,255,0);

				frame[(int)Math.max(Math.min((fx- (b + Math.cos(theta/2 - Math.PI) * dist))/10.0 + Window.width/2,Window.width-1),0)]
					 [(int)Math.max(Math.min((fz-(a + dist - Math.sin(theta/2) * dist))/10.0 + Window.height/2,Window.height-1),0)] = new Color(255,0,0);
				
				
				frame[(int)Math.max(Math.min(fx/10.0 + Window.width/2,Window.width-1),0)]
						 [(int)Math.max(Math.min(fz/10.0 + Window.height/2,Window.height-1),0)] = new Color(0,0,255);
				*/
			}
		}
		
		return frame;
	}
	
	void updateScene() {
		for(EntityObject o : objects) {
			o.updateVelocities();
		}
		for(EntityObject o : objects) {
			o.updatePosition();
		}
	}
}
