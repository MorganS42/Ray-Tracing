import java.awt.Color;

public class Ray {
	double x,y,z; //original position
	double vx,vy,vz; //vector
	double ix,iy,iz; //intersection point
	
	Ray(double x, double y, double z, double vx, double vy, double vz) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}
	
	Color run() {
		Color color = new Color(255,0,0);
		double x1 = x;
		double y1 = y;
		double z1 = z;
		
		double x2 = x + vx;
		double y2 = y + vy;
		double z2 = z + vz;
		
		for(EntityObject o : Scene.objects) {
			if(o.getClass() == Sphere.class) { 
				
				
				double a = Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2) + Math.pow((z2 - z1),2);
				double b = (-2)*((x2 - x1)*(o.x - x1) + (y2 - y1)*(o.y - y1) + (o.z - z1)*(z2 - z1));
				double c = Math.pow((o.x - x1),2) + Math.pow((o.y - y1),2) + Math.pow((o.z - z1),2) - ((Sphere)o).r*((Sphere)o).r;
				
				double inter = b*b - 4*a*c;
				
				if(inter >= 0) {
					
					double t = ((-b + Math.sqrt(inter))/(2*a) - 0.5)*100;
					System.out.println(t);
					color = new Color(Scene.toColorRange(t),Scene.toColorRange(t),Scene.toColorRange(t));//o.color;
				}
			}
			else if(o.getClass() == Plane.class) {
				boolean intersect = false;
				switch(((Plane)o).axis) {
					case X:
						if(o.x > x) {
							if(vx>0) {
								intersect = true;
							}
						}
						else if(o.x < x) {
							if(vx<0) {
								intersect = true;
							}
						}
					break;
					case Y:
						if(o.y > y) {
							if(vy>0) {
								intersect = true;
							}
						}
						else if(o.y < y) {
							if(vy<0) {
								intersect = true;
							}
						}
					break;
					case Z:
						if(o.z > z) {
							if(vz>0) {
								intersect = true;
							}
						}
						else if(o.z < z) {
							if(vz<0) {
								intersect = true;
							}
						}
					break;
				}
				
				if(intersect) {
					color = o.color;
				}
			}
		}
		
		return color;
	}
}
