package cli.states;

import cli.components.Menu;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class TalkSchedules extends State {
	private Menu menu = new Menu(sm);

	public TalkSchedules(StateManager stateManager) {
		super(stateManager);
		
		menu.addOption("View all talks", new AllTalks(sm));
		menu.addOption("View talks by day", new TalksByDay(sm));
		menu.addOption("View talks by time", new TalksByTime(sm));
		menu.addOption("View talks by speaker", new TalksBySpeaker(sm));
		menu.addOption("View talks by conference and day", new TalksByConferenceAndDay(sm));
	}

	@Override
	public void handleInput(Input input) {
		menu.handleInput(input);
		if (input.getType() == Key.ESCAPE) sm.popState();
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Schedule > Talks");
		menu.display();
	}
}
