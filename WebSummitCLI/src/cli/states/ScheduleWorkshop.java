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

public class ScheduleWorkshop extends State {
	private Form form = new Form();
	private boolean done = false;
	
	public ScheduleWorkshop(StateManager stateManager) {
		super(stateManager);
	}
	
	private void scheduleWorkshop() {
		String name = form.getValue("Name");
		String subject = form.getValue("Subject");
		String stage = form.getValue("Stage");
		String company = form.getValue("Company");
		Date date = new Date(form.getValue("Date"));
		Time startTime = new Time(form.getValue("StartTime"));
		Time endTime = new Time(form.getValue("EndTime"));
		int vacancies = Integer.parseInt(form.getValue("Vacancies"));
		sm.ws.ScheduleWorkshop(name, subject, stage, company, date, startTime, endTime, vacancies);
	}

	@Override
	public void onEnter() {
		form = new Form();
		done = false;
		
		form.addField("Name", "What should the name of the workshop be?", Type.STRING);
		form.addField("Subject", "What will be the subject of the workshop?", Type.STRING);
		form.addField("Stage", "In which stage should the workshop take place?", Type.STRING);
		form.addField("Company", "Which company will be hosting the workshop?", Type.STRING);
		form.addField("Date", "When will the workshop occur (yyyy-MM-dd) ?", Type.DATE);
		form.addField("StartTime", "At what time will the workshop start (HH:mm) ?", Type.TIME);
		form.addField("EndTime", "At what time will the workshop end (HH:mm) ?", Type.TIME);
		form.addField("Vacancies", "How many vacancies will the workshop have?", Type.NUMBER);
	}

	@Override
	public void handleInput(Input input) {
		if (done || input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		
		if (form.isDone()) {
			scheduleWorkshop();
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Events Management > Schedule Workshop");
		form.display();
		if (done) Term.print("Workshop scheduled. Press any key to go back.", true);
	}
}
