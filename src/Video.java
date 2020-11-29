import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Video {
	int frames;
	int fps;
	boolean rendering = false;
	double renderingDone = 0;
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
	
	void renderVideo(int smoothness) {
		rendering = true;
		for(int i=0; i<frames; i++) {
			Main.scene.updateScene();
			Color[][] frame = Main.scene.renderScene();
			if(videoActions.size() > i) {
				if(videoActions.get(i)!=null) {
					videoActions.get(i).run();
				}
			}
			video.add(frame);
			
			renderingDone = (double)i/(double)frames;
			if(Math.floor(renderingDone*100.0) % smoothness == 0) {
				Main.window.draw.repaint();
				System.out.println("Percent Done: "+(renderingDone * 100.0) + "%");
			}
		}
		rendering = false;
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
