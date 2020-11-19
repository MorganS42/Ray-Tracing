import java.awt.Color;

public class EntityObject {
	double x,y,z;
	
	double reflect = 0;
	Color color = new Color(0);
	
	EntityObject() {
	}
	EntityObject(double x,double y,double z) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	EntityObject(double x,double y,double z,Color color) {
		this(x,y,z);
		this.color = color;
	}
	EntityObject(double x,double y,double z,Color color,double reflect) {
		this(x,y,z, color);
		this.reflect = reflect;
	}
}
