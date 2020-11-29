public class Action {
	ActionType action;
	double amount;
	Action(ActionType action, double amount) {
		this.action = action;
		this.amount = amount;
	}
	void run() {
		switch(action) {
			case Forward:
				Main.screen.z+=amount;
				Main.camera.z+=amount;
			break;
			case Backward:
				Main.screen.z-=amount;
				Main.camera.z-=amount;
			break;
			case Left:
				Main.screen.x+=amount;
				Main.camera.x+=amount;
			break;
			case Right:
				Main.screen.x-=amount;
				Main.camera.x-=amount;
			break;
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
	FrameActions(Action ... actions) {
		this.actions = actions;
	}
	void run() {
		for(int i=0; i<actions.length; i++) {
			actions[i].run();
		}
	}
}

enum ActionType {
	Forward,
	Backward,
	Left,
	Right
}