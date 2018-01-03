package cli.states;

import cli.Main;
import cli.components.Menu;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class MainMenu extends State {
	private Menu menu = new Menu(sm);
	
	public MainMenu(StateManager stateManager) {
		super(stateManager);		
		menu.addOption("Venue management", new VenueManagement(sm));
		menu.addOption("Events management", new EventsManagement(sm));
		menu.addOption("Attendance control", new AttendanceControl(sm));
		menu.addOption("News management", new NewsManagement(sm));
		menu.addOption("Schedule", new Schedule(sm));
	}
	
	@Override
	public void handleInput(Input input) {
		if (input.getType() == Key.ESCAPE) Main.running = false;
		menu.handleInput(input);
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu");
		menu.display();
	}
}
