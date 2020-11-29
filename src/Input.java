import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
	private static final double smoothnessMove = 50;
	private static final double smoothnessTurn = 0.05;
	
	public void keyPressed(KeyEvent evt) {
		Action turn = null;
		Action move = null;
		switch(evt.getKeyCode()) {
			case KeyEvent.VK_UP:
				Main.screen.yzr+=smoothnessTurn;
            break;
            
	        case KeyEvent.VK_DOWN:
	        	Main.screen.yzr-=smoothnessTurn;
            break;
            
	        case KeyEvent.VK_LEFT:
	        	Main.screen.xzr-=smoothnessTurn;
            break;
            
	        case KeyEvent.VK_RIGHT :
	        	Main.screen.xzr+=smoothnessTurn;
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
				move = new Action(ActionType.Forward, smoothnessMove);
            break;
            	
	        case 's':
	        	move = new Action(ActionType.Backward, smoothnessMove);
            break;
            
	        case 'a':
	        	move = new Action(ActionType.Left, smoothnessMove);
            break;
            
	        case 'd':
	        	move = new Action(ActionType.Right, smoothnessMove);
            break;
            
	        case 'r':
	        	Main.window.fullRes();
            break;
		}
		if(move!=null) {
			move.run();
		}
		if(turn!=null) {
			turn.run();
		}
		Main.scene.renderScene();
	}
}
