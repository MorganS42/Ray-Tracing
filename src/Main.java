public class Main {
	static Window window;
	static Camera camera;
	static final Scene scene = new Scene();
	
	public static void main(String[] args) {
		window = new Window();
		camera = new Camera(Window.width/2,Window.height/2,-Window.height/2);
		scene.createScene();
		scene.renderScene();
	}
}
