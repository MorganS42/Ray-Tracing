import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Window extends JFrame {	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = 60;
	public static int height = 40;
	public static int res = 20;
	public static Color[][] screen = new Color[width][height];
	
	JFrame frame;
	Draw draw;
	Window() {
		this.fullRes(0.15);
		frame = new JFrame("Ray Tracing");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setVisible(true);
		
		draw = new Draw();
		frame.add(draw);
		
		frame.addKeyListener(new Input());
	}
	void update() {
	}
	void fullRes() {
		this.fullRes(1);
	}
	void fullRes(double res) { 
		Window.width = (int) (Window.screenSize.width * res);
    	Window.height = (int) (Window.screenSize.height * res);
    	Window.res = (int) Math.round(1/res);
    	Window.screen = new Color[Window.width][Window.height];
    	Main.camera = new Camera(Window.width/2,0,-Window.height*2);
	}
}


@SuppressWarnings("serial")
class Draw extends JPanel {
	public Draw() {
		
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new Color(0,0,0));
		g2.fillRect(0, 0, Window.width*Window.res, Window.height*Window.res);
		for(int x = 0; x<Window.width; x++) {
			for(int y = 0; y<Window.height; y++) {
				g2.setPaint(Window.screen[x][y]);
				g2.fillRect(x*Window.res, y*Window.res, Window.res, Window.res);
			}
		}
	}
}