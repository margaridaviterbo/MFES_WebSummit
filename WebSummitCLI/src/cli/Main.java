package cli;

import cli.statemanager.StateManager;
import cli.states.MainMenu;
import cli.utils.Term;

public class Main {
	public static boolean running = true;
	
	public static void main(String[] args) {
		Term.init();
		StateManager sm = new StateManager();
		sm.pushState(new MainMenu(sm));
		while (running) {
			sm.peekState().update();
		}
		Term.close();
	}
}
