package cli.states;

import cli.components.Form;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class PublishNewsAboutEvent extends State {
	private Form form = new Form();
	private boolean done = false;

	public PublishNewsAboutEvent(StateManager stateManager) {
		super(stateManager);
	}
	
	private void publishNewsAboutEvent() {
		String author = form.getValue("Author");
		String title = form.getValue("Title");
		String content = form.getValue("Content");
		sm.ws.PublishNewsArcticle(author, title, content);
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		done = false;
		
		form.addField("Author", "What is the name of the author?", Type.STRING);
		form.addField("Title", "What is the title of the article?", Type.STRING);
		form.addField("Content", "What is the content of the article?", Type.STRING);
	}
	
	@Override
	public void handleInput(Input input) {
		if (done || input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		
		if (form.isDone()) {
			publishNewsAboutEvent();
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > News Management > Publish News About Event");
		form.display();
		if (done) Term.print("Article published. Press any key to go back.", true);
	}
}
