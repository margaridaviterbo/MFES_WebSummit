package cli.states;

import cli.components.Menu;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class AttendanceControl extends State {
	private Menu menu = new Menu(sm);

	public AttendanceControl(StateManager stateManager) {
		super(stateManager);
		
		menu.addOption("Register ticket sale", new RegisterTicketSale(sm));
		menu.addOption("Register attendee", new RegisterAttendee(sm));
		menu.addOption("Add volunteer to waiting list", new AddVolunteerToWaitingList(sm));
		menu.addOption("Accept volunteer", new AcceptVolunteer(sm));
	}

	@Override
	public void handleInput(Input input) {
		menu.handleInput(input);
		if (input.getType() == Key.ESCAPE) sm.popState();
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Attendance Control");
		menu.display();
	}
}
