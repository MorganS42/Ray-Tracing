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
				Main.screen.x-=amount;
				Main.camera.x-=amount;
			break;
			case Right:
				Main.screen.x+=amount;
				Main.camera.x+=amount;
			break;
		}
	}
}

enum ActionType {
	Forward,
	Backward,
	Left,
	Right
}