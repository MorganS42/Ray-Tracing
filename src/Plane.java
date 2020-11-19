import java.awt.Color;

public class Plane extends EntityObject {
	PlaneAxis axis;
	Plane(double x, double y, double z,PlaneAxis axis, Color color) {
		super(x,y,z,color);
		this.axis = axis;
	}
}

enum PlaneAxis {
	X,
	Y,
	Z
}
