import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Window extends JFrame {	
	public final static int width = 400;
	public final static int height = 400;
	public final static int res = 2;
	public final static Color[][] screen = new Color[width][height];
	
	JFrame frame;
	Draw draw;
	Window() {
		frame = new JFrame("Ray Tracing");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setVisible(true);
		
		draw = new Draw();
		frame.add(draw);
	}
	void update() {
		draw.repaint();
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
				g2.fillRect(x*Window.res, y*Window.res, x*Window.res + Window.res, y*Window.res + Window.res);
			}
		}
	}
}