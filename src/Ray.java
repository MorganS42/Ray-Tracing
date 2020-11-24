import java.awt.Color;

public class Ray {
	double x,y,z; //original position
	double vx,vy,vz; //vector
	
	double reflectVx,reflectVy,reflectvz; //reflected vector
	double ix,iy,iz; //intersection point
	
	double refractVx,refractVy,refractVz; //refracted vector 
	double erx,ery,erz; //exit refracted position
	
	double it; //intersection t (time)
	
	private static final double correction = 0.001;
	
	int depth;
	
	Ray(double x, double y, double z, double vx, double vy, double vz, int depth) {
		this.x = x + vx;
		this.y = y + vy;
		this.z = z + vz;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.depth = depth;
	}
	
	Ray(double x, double y, double z, double[] nv, int depth) {
		this(x,y,z,nv[0],nv[1],nv[2],depth);
	}
	
	Color run() {
		Color color = Scene.skyColor;
		
		EntityObject o = this.intersect();
		if(o!=null) {
			color = o.color;
			if(o.getClass() == Plane.class) {
				if(((Plane)o).rainbow) {
					color = new Color(Color.HSBtoRGB(0, 255, 255));
				}
				
				if(((Plane)o).checker) {
					boolean xl = (Math.round(ix/((Plane)o).checkerSize) % 2 == 0);
					boolean yl = (Math.round(iy/((Plane)o).checkerSize) % 2 == 0);
					boolean zl = (Math.round(iz/((Plane)o).checkerSize) % 2 == 0);
					
					boolean c = false;
					
					switch(((Plane)o).axis) {
						case X:
							c = (yl || zl) && !(yl && zl);
						break;
						case Y:
							c = (xl || zl) && !(xl && zl);
						break;
						case Z:
							c = (xl || yl) && !(xl && yl);
						break;
					}
					
					if(c) {
						color = o.color;
					}
					else {
						color = new Color(0,0,0);
					}
				}
			}
			
			
			if(o.transparent > 0 && depth < Scene.maxDepth) {
				LightColor refract = new LightColor(new Ray(erx,ery,erz,refractVx,refractVy,refractVz,depth+1).run(),o.transparent);
				LightColor thisColor = new LightColor(color,1-o.transparent);
				
				color = Main.colorAverage(new LightColor[]{refract, thisColor});
			}
			
			if(o.reflect > 0 && depth < Scene.maxDepth) {
				LightColor reflect = new LightColor(new Ray(ix,iy,iz,reflectVx,reflectVy,reflectvz,depth+1).run(),o.reflect);
				LightColor thisColor = new LightColor(color,1-o.reflect);
				
				color = Main.colorAverage(new LightColor[]{reflect, thisColor});
			}
			
			
			
			LightColor[] lightValues = new LightColor[Scene.lights.size()];
			double light = 0;
			
			int i = 0;
			for(LightSource l : Scene.lights) {
				lightValues[i] = l.getLight(ix, iy, iz);
				light += lightValues[i].effect;
				i++;
			}
			
			Color lightColor = Main.colorAverage(lightValues);
			if(Main.darkMode) {
				color = Main.colorAverage(new Color[]{lightColor, new Color(
						Main.toColorRange(color.getRed()*light),
						Main.toColorRange(color.getGreen()*light),
						Main.toColorRange(color.getBlue()*light))});
			}
			else {
				color = Main.colorAverage(new Color[]{lightColor, color});
			}
		}
		return color;
	}
	
	EntityObject intersect() {
		return intersect(false,0);
	}
	EntityObject intersect(double r) {
		return intersect(true,r);
	}
	
	EntityObject intersect(boolean max, double maxt) {
		double x1 = x;
		double y1 = y;
		double z1 = z;
		
		double x2 = x + vx;
		double y2 = y + vy;
		double z2 = z + vz;
		
		EntityObject winningObject = null;
		it = -1;
		
		for(EntityObject o : Scene.objects) {
			if(o.getClass() == Sphere.class) { 
				double a = Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2) + Math.pow((z2 - z1),2);
				double b = (-2)*((x2 - x1)*(o.x - x1) + (y2 - y1)*(o.y - y1) + (o.z - z1)*(z2 - z1));
				double c = Math.pow((o.x - x1),2) + Math.pow((o.y - y1),2) + Math.pow((o.z - z1),2) - ((Sphere)o).r*((Sphere)o).r;
				
				double inter = b*b - 4*a*c;
				
				if(inter >= 0) {
					
					double t = 0;
					
					double t1 = (-b + Math.sqrt(inter))/(2*a);
					double t2 = (-b - Math.sqrt(inter))/(2*a);
					
					if(t1<t2 && t1>0) {
						t = t1;
					}
					else {
						t = t2;
						t2 = t1;
					}
					
					if(t > correction && (t<it || it==-1) && (t<=maxt || !max)) {
						ix = x + vx * t;
						iy = y + vy * t;
						iz = z + vz * t;
						
						if(o.reflect > 0) {
							double[] d = {vx,vy,vz};
							double[] n = Main.vectorNormalize((ix-o.x), (iy-o.y), (iz-o.z));
							
							double[] reflectVector = Main.vectorNormalize(Main.vectorSub(d,Main.vectorMutliply(n, 2*Main.dotProduct(d, n))));
							
							reflectVx = reflectVector[0];
							reflectVy = reflectVector[1];
							reflectvz = reflectVector[2];
						}
						
						if(o.transparent > 0) {
							//erx = o.x*2 -ix; //Gets opposite point on sphere
							//ery = o.y*2 -iy;
							//erz = o.z*2 -iz;
							
							erx = x + vx * t2;
							ery = y + vy * t2;
							erz = z + vz * t2;
							
							refractVx = vx;
							refractVy = vy;
							refractVz = vz;
						}
						
						it = t;
						
						winningObject = o;
					}
				}
			}
			else if(o.getClass() == Plane.class) {
				boolean intersect = false;
				double t = 0;
				
				double trvx = vx;
				double trvy = vy;
				double trvz = vz;
				
				switch(((Plane)o).axis) {			
					case X:
						if(o.x > x) {
							if(vx>0) {
								t = (o.x - x)/vx;
								trvx = -vx;
								intersect = true;
							}
						}
						else if(o.x < x) {
							if(vx<0) {
								t = (o.x - x)/vx;
								trvx = -vx;
								intersect = true;
							}
						}
					break;
					case Y:
						if(o.y > y) {
							if(vy>0) {
								t = (o.y - y)/vy;
								trvy = -vy;
								intersect = true;
							}
						}
						else if(o.y < y) {
							if(vy<0) {
								t = (o.y - y)/vy;
								trvy = -vy;
								intersect = true;
							}
						}
					break;
					case Z:
						if(o.z > z) {
							if(vz>0) {
								t = (o.z - z)/vz;
								trvz = -vz;
								intersect = true;
							}
						}
						else if(o.z < z) {
							if(vz<0) {
								t = (o.z - z)/vz;
								trvz = -vz;
								intersect = true;
							}
						}
					break;
				}
				if(intersect && t > correction && (t<it || it==-1) && (t<=maxt || !max)) {
					ix = x + vx*t;
					iy = y + vy*t;
					iz = z + vz*t;
					
					reflectVx = trvx;
					reflectVy = trvy;
					reflectvz = trvz;
					
					it = t;
					
					winningObject = o;
				}
			}
		}
		return winningObject;
	}
}
