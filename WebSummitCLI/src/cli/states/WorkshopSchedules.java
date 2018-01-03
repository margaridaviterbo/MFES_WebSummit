package cli.states;

import cli.components.Menu;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class WorkshopSchedules extends State {
	private Menu menu = new Menu(sm);

	public WorkshopSchedules(StateManager stateManager) {
		super(stateManager);
		
		menu.addOption("View all workshops", new AllWorkshops(sm));
		menu.addOption("View workshops by day", new WorkshopsByDay(sm));
		menu.addOption("View workshops by time", new WorkshopsByTime(sm));
		menu.addOption("View workshops by stage", new WorkshopsByStage(sm));
		menu.addOption("View workshops by stage and day", new WorkshopsByStageAndDay(sm));
	}

	@Override
	public void handleInput(Input input) {
		menu.handleInput(input);
		if (input.getType() == Key.ESCAPE) sm.popState();
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Schedule > Workshops");
		menu.display();
	}
}
