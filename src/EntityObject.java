import java.awt.Color;

public class EntityObject {
	double x,y,z;
	double xv = 0, yv = 0, zv = 0;
	double nxv = 0, nyv = 0, nzv = 0; //new velocities
	double bounce = 1;
	double density = 1;
	
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
		this.nxv = xv;
		this.nyv = yv;
		this.nzv = zv;
	}
	EntityObject(double x,double y,double z,Color color,double reflect, double transparent, double bounce, double density, double xv, double yv, double zv) {
		this(x,y,z, color, reflect,transparent,bounce,xv,yv,zv);
		this.density = density;
	}
	
	void updateVelocities() {
		if(this.getClass() == Sphere.class) {
			boolean falling = true;
			for(EntityObject o : Scene.objects) {
				if(o.getClass() == Plane.class) {
					switch(((Plane)o).axis) {			
						case X:
							if(o.x <= this.x+((Sphere)this).r && o.x>=this.x) {
								x = o.x - ((Sphere)this).r;
								nxv = -xv;
							}
							else if(o.x >= this.x-((Sphere)this).r && o.x<=this.x) {
								x = o.x + ((Sphere)this).r;
								nxv = -xv;
							}
						break;
						case Y:
							if(o.y <= this.y+((Sphere)this).r && o.y>=this.y) {
								y = o.y - ((Sphere)this).r;
								nyv = -yv*bounce;
								falling = false;
							}
							else if(o.y >= this.y-((Sphere)this).r && o.y<=this.y) {
								y = o.y + ((Sphere)this).r;
								nyv = -yv*bounce;
							}
						break;
						case Z:
							if(o.z <= this.z+((Sphere)this).r && o.z>=this.z) {
								z = o.z - ((Sphere)this).r;
								nzv = -zv;
							}
							else if(o.z >= this.z-((Sphere)this).r && o.z<=this.z) {
								z = o.z + ((Sphere)this).r;
								nzv = -zv;
							}
						break;
					}
				}
				else if(o.getClass() == Sphere.class && this!=o) {
					if(Main.dist(x, y, z, o.x, o.y, o.z) < ((Sphere)this).r + ((Sphere)o).r && Math.abs(xv)>0) {
						//Fix later
						//x = o.x + (((Sphere)this).r + ((Sphere)o).r) * (xv > o.xv ? -1 : 1);
						//y = o.y + (((Sphere)this).r + ((Sphere)o).r) * (yv > o.yv ? -1 : 1);
						//z = o.z + (((Sphere)this).r + ((Sphere)o).r) * (zv > o.zv ? -1 : 1);
						
						double thisMass = 4.0/3.0 * Math.pow(((Sphere)this).r,3) * Math.PI * density;
						double otherMass = 4.0/3.0 * Math.pow(((Sphere)o).r,3) * Math.PI * o.density;
						
						nxv   = xv * ((thisMass - otherMass)/(thisMass + otherMass)) + 
							  o.xv * ((otherMass * 2)/(thisMass + otherMass));
						o.nxv = xv * ((thisMass * 2)/(thisMass + otherMass)) - 
							  o.xv * ((thisMass - otherMass)/(thisMass + otherMass));
						
						nyv   = yv * ((thisMass - otherMass)/(thisMass + otherMass)) + 
							  o.yv * ((otherMass * 2)/(thisMass + otherMass));
						o.nyv = yv * ((thisMass * 2)/(thisMass + otherMass)) - 
							  o.yv * ((thisMass - otherMass)/(thisMass + otherMass));
						
						nzv   = zv * ((thisMass - otherMass)/(thisMass + otherMass)) + 
							  o.zv * ((otherMass * 2)/(thisMass + otherMass));
						o.nzv = zv * ((thisMass * 2)/(thisMass + otherMass)) - 
							  o.zv * ((thisMass - otherMass)/(thisMass + otherMass));
					}
				}
				
				if(falling) {
					nyv += Main.gravity;
				}
			}
		}
	}
	void updatePosition() {
		xv=nxv;
		yv=nyv;
		zv=nzv;
		x+=xv;
		y+=yv;
		z+=zv;
	}
}
