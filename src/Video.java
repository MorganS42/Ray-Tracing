import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Video {
	int frames;
	int fps;
	public ArrayList<Color[][]> video;
	public ArrayList<FrameActions> videoActions;
	Video(int frames, int fps) {
		this.frames = frames;
		this.fps = fps;
		video = new ArrayList<Color[][]>();
		videoActions = new ArrayList<FrameActions>();
	}
	Video(int frames, int fps, ArrayList<ContinuousFrameActions> videoActions) {
		this(frames, fps);
		for(ContinuousFrameActions actions : videoActions) {
			for(int i=0; i<actions.duration; i++) {
				this.videoActions.add(actions.actions);
			}
		}
	}
	
	void renderVideo() {
		for(int i=0; i<frames; i++) {
			Main.scene.updateScene();
			Color[][] frame = Main.scene.renderScene();
			if(videoActions.size() > i) {
				if(videoActions.get(i)!=null) {
					videoActions.get(i).run();
				}
			}
			video.add(frame);
			
			System.out.println("Percent Done: "+((double)i/(double)frames * 100.0) + "%");
		}
	}
	
	void playVideo() {
		for(Color[][] frame : video) {
			for(int x=0; x<Window.width; x++) {
				for(int y=0; y<Window.height; y++) {
					Window.screen[x][y] = frame[x][y];
				}
			}
			Main.window.draw.repaint();
			try {
				TimeUnit.MILLISECONDS.sleep((long) (1000.0/(double)fps));
			} 
			catch (InterruptedException e) {
				System.out.println("Video Error: Timer Failed");
			}
		}
	}
}

class ContinuousFrameActions {
	FrameActions actions;
	int duration;
	ContinuousFrameActions(FrameActions actions, int duration) {
		this.actions = actions;
		this.duration = duration;
	}
}

class FrameActions {
	Action[] actions;
	FrameActions(Action[] actions) {
		this.actions = actions;
	}
	void run() {
		for(int i=0; i<actions.length; i++) {
			actions[i].run();
		}
	}
}
