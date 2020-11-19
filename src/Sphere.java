import java.awt.Color;

public class Sphere extends EntityObject {
	double r; //radius
	
	Sphere(double x, double y, double z, double r, Color color) {
		super(x,y,z, color);
		this.r = r;
	}
}
