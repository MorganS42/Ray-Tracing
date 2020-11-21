import java.awt.Color;

public class Plane extends EntityObject {
	PlaneAxis axis;
	boolean checker = false;
	double checkerSize = 100;
	
	Plane(double x, double y, double z,PlaneAxis axis, Color color) {
		super(x,y,z,color);
		this.axis = axis;
	}
	Plane(double x, double y, double z,PlaneAxis axis, boolean checker) {
		super(x,y,z, new Color(255,255,255));
		this.checker = checker;
		this.axis = axis;
	}
	Plane(double x, double y, double z,PlaneAxis axis, boolean checker, double checkerSize) {
		super(x,y,z, new Color(255,255,255));
		this.checker = checker;
		this.checkerSize = checkerSize;
		this.axis = axis;
	}
	Plane(double x, double y, double z,PlaneAxis axis, Color color, boolean checker, double checkerSize) {
		super(x,y,z,color);
		this.checker = checker;
		this.checkerSize = checkerSize;
		this.axis = axis;
	}
	Plane(double x, double y, double z,PlaneAxis axis, Color color, double reflection, boolean checker, double checkerSize) {
		super(x,y,z,color, reflection);
		this.checker = checker;
		this.checkerSize = checkerSize;
		this.axis = axis;
	}
}

enum PlaneAxis {
	X,
	Y,
	Z
}
