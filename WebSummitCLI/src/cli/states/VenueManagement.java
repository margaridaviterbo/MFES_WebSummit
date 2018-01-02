package cli.states;

import cli.components.Menu;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class VenueManagement extends State {
	private Menu menu = new Menu(sm);
	
	public VenueManagement(StateManager stateManager) {
		super(stateManager);
		
		menu.addOption("Schedule a venue", new ScheduleVenue(sm));
	}

	@Override
	public void handleInput(Input input) {
		menu.handleInput(input);
		if (input.getType() == Key.ESCAPE) sm.popState();
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Venue Management");
		menu.display();
	}
}
