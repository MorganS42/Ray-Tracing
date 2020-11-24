import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
	private static final double smoothnessMove = 50;
	private static final double smoothnessTurn = 0.01;
	
	public void keyPressed(KeyEvent evt) {
		switch(evt.getKeyCode()) {
			case KeyEvent.VK_UP:
				Main.screen.yzr+=smoothnessTurn;
            break;
            
	        case KeyEvent.VK_DOWN:
	        	Main.screen.yzr-=smoothnessTurn;
            break;
            
	        case KeyEvent.VK_LEFT:
	        	Main.screen.xzr+=smoothnessTurn;
            break;
            
	        case KeyEvent.VK_RIGHT :
	        	Main.screen.xzr-=smoothnessTurn;
            break;
            
	        case KeyEvent.VK_SHIFT :
	        	Main.screen.y+=smoothnessMove;
	        	Main.camera.y+=smoothnessMove;
            break;
            
	        case KeyEvent.VK_SPACE :
	        	Main.screen.y-=smoothnessMove;
	        	Main.camera.y-=smoothnessMove;
            break;
		}
		switch(evt.getKeyChar()) {
			case 'w':
				Main.screen.z+=smoothnessMove;
				Main.camera.z+=smoothnessMove;
            break;
            
	        case 's':
	        	Main.screen.z-=smoothnessMove;
				Main.camera.z-=smoothnessMove;
            break;
            
	        case 'a':
	        	Main.screen.x-=smoothnessMove;
				Main.camera.x-=smoothnessMove;
            break;
            
	        case 'd':
	        	Main.screen.x+=smoothnessMove;
				Main.camera.x+=smoothnessMove;
            break;
            
	        case 'r':
	        	Main.window.fullRes();
            break;
		}
		
		Main.scene.renderScene();
	}
}
