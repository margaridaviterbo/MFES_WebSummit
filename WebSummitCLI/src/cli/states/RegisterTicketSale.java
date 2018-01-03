package cli.states;

import java.util.HashMap;
import java.util.Map;

import cli.components.Form;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class RegisterTicketSale extends State {
	private static final Map<String, Object> types = new HashMap<>();
	static {
		types.put("Attendee", websummit.quotes.AttendeeQuote.getInstance());
		types.put("Company", websummit.quotes.CompanyQuote.getInstance());
	}
	
	private Form form = new Form();
	private boolean done = false;

	public RegisterTicketSale(StateManager stateManager) {
		super(stateManager);
	}
	
	private void registerTicketSale() {
		String name = form.getValue("Name");
		String type = form.getValue("Type");
		sm.ws.SellTicket(name, types.get(type));
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		done = false;
		
		form.addField("Name", "What is the name of the attendee?", Type.STRING);
		form.addField("Type", "What is the ticket type?", Type.CHOICE);
		form.getField("Type").addChoice("Attendee");
		form.getField("Type").addChoice("Company");
	}
	
	@Override
	public void handleInput(Input input) {
		if (done || input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		
		if (form.isDone()) {
			registerTicketSale();
			done = true;
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Attendance Control > Register Ticket Sale ");
		form.display();
		if (done) Term.print("Ticket sale recorded. Press any key to go back.", true);
	}
}
