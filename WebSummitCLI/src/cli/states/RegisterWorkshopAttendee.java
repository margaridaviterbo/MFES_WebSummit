package cli.states;

import cli.components.Form;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class RegisterWorkshopAttendee extends State {
	private Form form = new Form();
	private boolean done = false;

	public RegisterWorkshopAttendee(StateManager stateManager) {
		super(stateManager);
	}
	
	private void registerWorkshopAttendee() {
		String name = form.getValue("Name");
		String workshop = form.getValue("Workshop");
		sm.ws.RegisterAttendeeToWorkshop(name, workshop);
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		done = false;
		
		form.addField("Name", "What is the name of the attendee?", Type.STRING);
		form.addField("Workshop", "What is the name of the workshop?", Type.STRING);
	}
	
	@Override
	public void handleInput(Input input) {
		if (done || input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		
		if (form.isDone()) {
			registerWorkshopAttendee();
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Attendance Control > Register Workshop Attendee ");
		form.display();
		if (done) Term.print("Attendee registered to workshop. Press any key to go back.", true);
	}
}
