package cli.states;

import cli.components.Form;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;
import websummit.Date;

public class ScheduleConference extends State {
	private Form form = new Form();
	private boolean done = false;
	
	public ScheduleConference(StateManager stateManager) {
		super(stateManager);
	}
	
	private void scheduleConference() {
		String stage = form.getValue("Stage");
		String name = form.getValue("Name");
		Date startDate = new Date(form.getValue("StartDate"));
		Date endDate = new Date(form.getValue("EndDate"));
		sm.ws.ScheduleConference(stage, name, startDate, endDate);
	}

	@Override
	public void onEnter() {
		form = new Form();
		done = false;
		
		form.addField("Stage", "In which stage should the conference take place?", Type.STRING);
		form.addField("Name", "What should the name of the conference be?", Type.STRING);
		form.addField("StartDate", "When should the conference begin (yyyy-MM-dd) ?", Type.DATE);
		form.addField("EndDate", "When should the conference end (yyyy-MM-dd) ?", Type.DATE);
	}

	@Override
	public void handleInput(Input input) {
		if (done || input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		
		if (form.isDone()) {
			scheduleConference();
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Events Management > Schedule Conference");
		form.display();
		if (done) Term.print("Conference scheduled. Press any key to go back.", true);
	}
}
