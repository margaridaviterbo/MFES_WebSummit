package cli.states;

import cli.components.Form;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;
import websummit.Date;
import websummit.Time;

public class ScheduleTalk extends State {
	private Form form = new Form();
	private boolean done = false;
	
	public ScheduleTalk(StateManager stateManager) {
		super(stateManager);
	}
	
	private void scheduleTalk() {
		String conference = form.getValue("Conference");
		String title = form.getValue("Title");
		String speaker = form.getValue("Speaker");
		Date date = new Date(form.getValue("Date"));
		Time startTime = new Time(form.getValue("StartTime"));
		Time endTime = new Time(form.getValue("EndTime"));
		sm.ws.ScheduleTalk(conference, title, speaker, date, startTime, endTime);
	}

	@Override
	public void onEnter() {
		form = new Form();
		done = false;
		
		form.addField("Conference", "In which conference should the talk take place?", Type.STRING);
		form.addField("Title", "What should the title of the talk be?", Type.STRING);
		form.addField("Speaker", "Who will be the speaker?", Type.STRING);
		form.addField("Date", "When will the talk take place (yyyy-MM-dd) ?", Type.DATE);
		form.addField("StartTime", "At what time will the talk begin?", Type.TIME);
		form.addField("EndTime", "At what time will the talk end?", Type.TIME);
	}

	@Override
	public void handleInput(Input input) {
		if (done || input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		
		if (form.isDone()) {
			scheduleTalk();
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Events Management > Schedule Talk");
		form.display();
		if (done) Term.print("Talk scheduled. Press any key to go back.", true);
	}
}
