package cli.states;

import cli.components.Form;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class AcceptVolunteer extends State {
	private Form form = new Form();
	private boolean done = false;

	public AcceptVolunteer(StateManager stateManager) {
		super(stateManager);
	}
	
	private void acceptVolunteer() {
		String name = form.getValue("Name");
		sm.ws.AcceptVolunteer(name);
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		done = false;
		
		form.addField("Name", "What is the name of the volunteer?", Type.STRING);
	}
	
	@Override
	public void handleInput(Input input) {
		if (done || input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		
		if (form.isDone()) {
			acceptVolunteer();
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Attendance Control > Accept Volunteer ");
		form.display();
		if (done) Term.print("Volunteer accepted. Press any key to go back.", true);
	}
}
