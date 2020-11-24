public class Screen {
	public double x,y,z;
	public double xzr = 0; //xz rotation
	public double yzr = 0; //yz rotation
	public double xyr = 0; //xy rotation
	public static double dis; //distance to camera
	Screen(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		Screen.dis = Main.dist(x, y, z, Main.camera.x, Main.camera.y, Main.camera.z);
	}
}
