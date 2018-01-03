package cli.states;

import cli.components.Menu;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class Schedule extends State {
	private Menu menu = new Menu(sm);
	
	public Schedule(StateManager stateManager) {
		super(stateManager);
		
		menu.addOption("Conferences", new ConferenceSchedules(sm));
		menu.addOption("Talks", new TalkSchedules(sm));
		menu.addOption("Workshops", new WorkshopSchedules(sm));
	}

	@Override
	public void handleInput(Input input) {
		menu.handleInput(input);
		if (input.getType() == Key.ESCAPE) sm.popState();
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Schedule");
		menu.display();
	}
}
