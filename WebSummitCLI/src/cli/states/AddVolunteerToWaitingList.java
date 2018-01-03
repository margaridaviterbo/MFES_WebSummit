package cli.states;

import cli.components.Form;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class AddVolunteerToWaitingList extends State {
	private Form form = new Form();
	private boolean done = false;

	public AddVolunteerToWaitingList(StateManager stateManager) {
		super(stateManager);
	}
	
	private void addToWaitingList() {
		String name = form.getValue("Name");
		sm.ws.AddToWaitingList(name);
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		done = false;
		
		form.addField("Name", "What is the name of the would-be volunteer?", Type.STRING);
	}
	
	@Override
	public void handleInput(Input input) {
		if (done || input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		
		if (form.isDone()) {
			addToWaitingList();
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Attendance Control > Add Volunteer To Waiting List ");
		form.display();
		if (done) Term.print("Would-be volunteer added to the waiting list. Press any key to go back.", true);
	}
}
