import java.awt.Color;

public class Sphere extends EntityObject {
	double r; //radius
	
	Sphere(double x, double y, double z, double r, Color color) {
		super(x,y,z, color);
		this.r = r;
	}
	
	Sphere(double x, double y, double z, double r, Color color, double reflect) {
		super(x,y,z, color,reflect);
		this.r = r;
	}
	
	Sphere(double x, double y, double z, double r, Color color, double reflect, double transparent) {
		super(x,y,z, color,reflect, transparent);
		this.r = r;
	}
	Sphere(double x, double y, double z, double r, Color color, double reflect, double transparent, double bounce) {
		super(x,y,z, color,reflect, transparent,bounce);
		this.r = r;
	}
	Sphere(double x, double y, double z, double r, Color color, double reflect, double transparent, double bounce, double xv, double yv, double zv) {
		super(x,y,z, color,reflect, transparent,bounce,xv,yv,zv);
		this.r = r;
	}
	Sphere(double x, double y, double z, double r, Color color, double reflect, double transparent, double bounce, double density,double xv, double yv, double zv) {
		super(x,y,z, color,reflect, transparent,bounce,density,xv,yv,zv);
		this.r = r;
	}
}
