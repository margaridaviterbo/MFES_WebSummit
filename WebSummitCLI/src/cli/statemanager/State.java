package cli.statemanager;

import java.util.LinkedList;
import java.util.Queue;

public abstract class State {
	private Queue<Input> inputs = new LinkedList<>();
	protected StateManager sm = new StateManager();
	
	public State(StateManager stateManager) {
		sm = stateManager;
	}
	
	public final void update() {
		display();
		scanInput();
		handleInput(lastInput());
	};
	
	private final void scanInput() {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(System.in.read());
			while (System.in.available() > 0) {
				sb.append(System.in.read());
			}
			int input = Integer.parseInt(sb.toString());
			inputs.add(new Input(input));
		} catch (Exception e) {}
	}
	
	private final Input lastInput() {
		return inputs.remove();
	}
	
	public void onEnter() {}
	
	public abstract void handleInput(Input input);
	
	public abstract void display();
}
