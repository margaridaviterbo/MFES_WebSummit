package cli.states;

import cli.components.Menu;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class EventsManagement extends State {
	private Menu menu = new Menu(sm);

	public EventsManagement(StateManager stateManager) {
		super(stateManager);
		
		menu.addOption("Schedule conference", new ScheduleConference(sm));
		menu.addOption("Schedule talk", new ScheduleTalk(sm));
	}

	@Override
	public void handleInput(Input input) {
		menu.handleInput(input);
		if (input.getType() == Key.ESCAPE) sm.popState();
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Events Management");
		menu.display();
	}
}
