package cli.states;

import cli.components.Menu;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class NewsManagement extends State {
	private Menu menu = new Menu(sm);
	
	public NewsManagement(StateManager stateManager) {
		super(stateManager);
		
		menu.addOption("Publish news about the event", new PublishNewsAboutEvent(sm));
		menu.addOption("Publish news about a conference", new PublishNewsAboutConference(sm));
		menu.addOption("Read all news", new ReadAllNews(sm));
	}
	
	@Override
	public void handleInput(Input input) {
		if (input.getType() == Key.ESCAPE) sm.popState();
		menu.handleInput(input);
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > News Management");
		menu.display();
	}
}
