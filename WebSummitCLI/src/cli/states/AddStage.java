package cli.states;

import cli.components.Form;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class AddStage extends State {
	private Form form = new Form();
	private boolean done = false;

	public AddStage(StateManager stateManager) {
		super(stateManager);
	}
	
	private void addStage() {
		String venue = form.getValue("Venue");
		String stage = form.getValue("Stage");
		sm.ws.AddStage(venue, stage);
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		done = false;
		
		form.addField("Venue", "In which venue do you want to create a stage?", Type.STRING);
		form.addField("Stage", "What should the name of the stage be?", Type.STRING);
	}

	@Override
	public void handleInput(Input input) {
		if (done || input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		
		if (form.isDone()) {
			addStage();
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Venue Management > Add Stage");
		form.display();
		if (done) Term.print("Stage created. Press any key to go back.", true);
	}

}
