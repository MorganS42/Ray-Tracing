import java.awt.Color;

public class EntityObject {
	double x,y,z;
	double xv = 0,yv = 0,zv = 0;
	double bounce = 1;
	
	double reflect = 0;
	double transparent = 0;
	Color color;
	
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
	EntityObject(double x,double y,double z,Color color,double reflect, double transparent) {
		this(x,y,z, color, reflect);
		this.transparent = transparent;
	}
	EntityObject(double x,double y,double z,Color color,double reflect, double transparent, double bounce) {
		this(x,y,z, color, reflect);
		this.transparent = transparent;
		this.bounce = bounce;
	}
	EntityObject(double x,double y,double z,Color color,double reflect, double transparent, double bounce, double xv, double yv, double zv) {
		this(x,y,z, color, reflect);
		this.transparent = transparent;
		this.bounce = bounce;
		this.xv = xv;
		this.yv = yv;
		this.zv = zv;
	}
	
	void run() {
		if(this.getClass() == Sphere.class) {
			boolean falling = true;
			for(EntityObject o : Scene.objects) {
				if(o.getClass() == Plane.class) {
					switch(((Plane)o).axis) {			
						case X:
							if(o.x <= this.x+((Sphere)this).r && o.x>=this.x) {
								x = o.x - ((Sphere)this).r;
								xv = -xv;
							}
							else if(o.x >= this.x-((Sphere)this).r && o.x<=this.x) {
								x = o.x + ((Sphere)this).r;
								xv = -xv;
							}
						break;
						case Y:
							if(o.y <= this.y+((Sphere)this).r && o.y>=this.y) {
								y = o.y - ((Sphere)this).r;
								yv = -yv*bounce;
								falling = false;
							}
							else if(o.y >= this.y-((Sphere)this).r && o.y<=this.y) {
								y = o.y + ((Sphere)this).r;
								yv = -yv*bounce;
							}
						break;
						case Z:
							if(o.z <= this.z+((Sphere)this).r && o.z>=this.z) {
								z = o.z - ((Sphere)this).r;
								zv = -zv;
							}
							else if(o.z >= this.z-((Sphere)this).r && o.z<=this.z) {
								z = o.z + ((Sphere)this).r;
								zv = -zv;
							}
						break;
					}
				}
				else if(o.getClass() == Sphere.class && this!=o) {
					if(Main.dist(x, y, z, o.x, o.y, o.z) <= ((Sphere)this).r + ((Sphere)o).r && Math.abs(xv)>0) {
						//Fix later
						double thisMass = 4.0/3.0 * Math.pow(((Sphere)this).r,3) * Math.PI;
						double otherMass = 4.0/3.0 * Math.pow(((Sphere)o).r,3) * Math.PI;
						
						o.xv += xv * (1.0 - (thisMass/otherMass));
						x = o.x + (((Sphere)this).r + ((Sphere)o).r) * (xv > 0 ? -1 : 1);
						xv = xv * (thisMass/otherMass);
						
					}
				}
				
				if(falling) {
					yv += Main.gravity;
				}
			}
		}
		
		x+=xv;
		y+=yv;
		z+=zv;
	}
}
