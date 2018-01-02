package cli.states;

import cli.components.Form;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.utils.Term;
import websummit.Date;
import cli.statemanager.Input.Key;

public class ScheduleVenue extends State {
	private Form form = new Form();
	private boolean done = false;

	public ScheduleVenue(StateManager stateManager) {
		super(stateManager);
		
		form.addField("Name", "What is the name of the venue?", Type.STRING);
		form.addField("StartDate", "What is the start date (yyyy-MM-dd) ?", Type.DATE);
		form.addField("EndDate", "What is the end date (yyyy-MM-dd) ?", Type.DATE);
		form.addField("Rent", "How much have you paid for the booking?", Type.NUMBER);
	}

	@Override
	public void handleInput(Input input) {
		if (done) sm.changeState(new VenueManagement(sm));
		form.handleInput(input);
		if (input.getType() == Key.ESCAPE) sm.popState();
		if (form.isDone()) {
			String name = form.getValue("Name");
			Date startDate = new Date(form.getValue("StartDate"));
			Date endDate = new Date(form.getValue("EndDate"));
			Integer rent = Integer.parseInt(form.getValue("Rent"));
			sm.ws.ScheduleVenue(name, startDate, endDate, rent);
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Venue Management > Schedule Venue");
		form.display();
		if (done) Term.print("Venue scheduled. Press any key to go back.", true);
	}

}
