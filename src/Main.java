import java.awt.Color;

public class Main {
	public static final int sceneToRender = 0;
	
	static Window window;
	static Screen screen = new Screen(0,0,0);
	static Camera camera = new Camera(Window.width/2,0,-Window.height*2);
	static final Scene scene = new Scene();
	
	static final boolean darkMode = true;
	
	public static void main(String[] args) {
		window = new Window();
		
		scene.initializeScenes();
		scene.createScene();
		scene.renderScene();
	}
	
	public static double[] vectorNormalize(double x, double y, double z) {
		double a = Math.sqrt(x*x+y*y+z*z);
		double[] nv = new double[3]; //new vector
		nv[0] = x / a;
		nv[1] = y / a;
		nv[2] = z / a;
		return nv;
	}
	public static double[] vectorNormalize(double[] v) {
		return vectorNormalize(v[0],v[1],v[2]);
	}
	
	public static double[] crossProduct(double x1, double y1, double z1, double x2, double y2, double z2) {
		double[] nv = new double[3]; //new vector
		nv[0] = y1*z2 - z1*y2;
		nv[1] = z1*x2 - x1*z2;
		nv[2] = x1*y2 - y1*x2;
		return nv;
	}
	public static double[] crossProduct(double[] v1, double[] v2) {
		return crossProduct(v1[0],v1[1],v1[2],v2[0],v2[1],v2[2]);
	}
	
	public static double dotProduct(double x1, double y1, double z1, double x2, double y2, double z2) {
		return x1*x2 + y1*y2 + z1 * z2;
	}
	public static double dotProduct(double[] v1, double[] v2) {
		return dotProduct(v1[0],v1[1],v1[2],v2[0],v2[1],v2[2]);
	}
	
	public static double[] vectorMutliply(double x, double y, double z, double n) {
		double[] nv = new double[3]; //new vector
		nv[0] = x*n;
		nv[1] = y*n;
		nv[2] = z*n;
		return nv;
	}
	public static double[] vectorMutliply(double[] v1, double n) {
		return vectorMutliply(v1[0],v1[1],v1[2],n);
	}
	
	public static double[] vectorAdd(double x1, double y1, double z1, double x2, double y2, double z2) {
		double[] nv = new double[3]; //new vector
		nv[0] = x1+x2;
		nv[1] = y1+y2;
		nv[2] = z1+z2;
		return nv;
	}
	public static double[] vectorAdd(double[] v1, double[] v2) {
		return vectorAdd(v1[0],v1[1],v1[2],v2[0],v2[1],v2[2]);
	}
	
	public static double[] vectorSub(double x1, double y1, double z1, double x2, double y2, double z2) {
		double[] nv = new double[3]; //new vector
		nv[0] = x1-x2;
		nv[1] = y1-y2;
		nv[2] = z1-z2;
		return nv;
	}
	public static double[] vectorSub(double[] v1, double[] v2) {
		return vectorSub(v1[0],v1[1],v1[2],v2[0],v2[1],v2[2]);
	}

	public static double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
		return  Math.sqrt(
					Math.pow(x1-x2,2) +
					Math.pow(y1-y2,2) +
					Math.pow(z1-z2,2)
				);
	}
	
	public static Color colorMultiply(double x, Color color) {
		return new  Color(
						toColorRange(color.getRed() * x), 
						toColorRange(color.getGreen() * x), 
						toColorRange(color.getBlue() * x)
					);
	}
	
	public static Color colorAverage(LightColor colors[]) {
		double red = 0;
		double green = 0;
		double blue = 0;
		double total = 0;
		for(int i=0; i<colors.length; i++) {
			red+=colors[i].color.getRed() * colors[i].effect;
			green+=colors[i].color.getGreen() * colors[i].effect;
			blue+=colors[i].color.getBlue() * colors[i].effect;
			total+=colors[i].effect;
		}
		red/=total;
		green/=total;
		blue/=total;
		return new Color(toColorRange(red),toColorRange(green),toColorRange(blue));
	}
	
	public static Color colorAverage(Color colors[]) {
		double red = 0;
		double green = 0;
		double blue = 0;
		for(int i=0; i<colors.length; i++) {
			red+=colors[i].getRed();
			green+=colors[i].getGreen();
			blue+=colors[i].getBlue();
		}
		red/=colors.length;
		green/=colors.length;
		blue/=colors.length;
		return new Color((int)red,(int)green,(int)blue);
	}
	
	public static int toColorRange(double n) {
		return (int)Math.min(Math.max(n, 0),255);
	}
}
