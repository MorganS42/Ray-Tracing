import java.awt.Color;

public class LightSource extends EntityObject {
	double lightIntensity;
	LightSource(double x, double y, double z, Color color, double lightIntensity) {
		super(x,y,z,color);
		this.lightIntensity = lightIntensity;
	}
	
	LightColor getLight(double px, double py, double pz) { //point x,y,z
		double r = Main.dist(px,py,pz,x,y,z);
		double effect = (lightIntensity)/(4*Math.PI*r*r);
		LightColor newColor = new LightColor(new Color(0,0,0),effect);
		if(new Ray(px,py,pz,Main.vectorNormalize(x - px,y - py,z - pz),1).intersect() == null) {
			newColor.color = Main.colorMultiply(effect,color);
			//Main.colorMultiply(1/(Math.pow(Main.dist(px,py,pz,x,y,z),p)*Math.pow(rod,p)),color);
		}
		return newColor;
	}
}

class LightColor{
	Color color;
	double effect;
	
	LightColor(Color color, double effect) {
		this.color = color;
		this.effect = effect;
	}
}