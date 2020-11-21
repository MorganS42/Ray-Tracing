import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
	private static final double smoothness = 10;
	
	public void keyPressed(KeyEvent evt) {
		switch(evt.getKeyCode()) {
			case KeyEvent.VK_UP:
				Main.camera.y+=smoothness;
            break;
            
	        case KeyEvent.VK_DOWN:
	        	Main.camera.y-=smoothness;
            break;
            
	        case KeyEvent.VK_LEFT:
	        	Main.camera.x+=smoothness;
            break;
            
	        case KeyEvent.VK_RIGHT :
	        	Main.camera.x-=smoothness;
            break;
            
	        case KeyEvent.VK_SHIFT :
	        	Main.screen.y+=smoothness;
	        	Main.camera.y+=smoothness;
            break;
            
	        case KeyEvent.VK_SPACE :
	        	Main.screen.y-=smoothness;
	        	Main.camera.y-=smoothness;
            break;
		}
		switch(evt.getKeyChar()) {
			case 'w':
				Main.screen.z+=smoothness;
				Main.camera.z+=smoothness;
            break;
            
	        case 's':
	        	Main.screen.z-=smoothness;
				Main.camera.z-=smoothness;
            break;
            
	        case 'a':
	        	Main.screen.x-=smoothness;
				Main.camera.x-=smoothness;
            break;
            
	        case 'd':
	        	Main.screen.x+=smoothness;
				Main.camera.x+=smoothness;
            break;
            
	        case 'r':
	        	Main.window.fullRes();
            break;
		}
		
		Main.scene.renderScene();
	}
}
