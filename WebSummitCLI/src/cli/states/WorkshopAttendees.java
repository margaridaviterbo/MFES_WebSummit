package cli.states;

import org.overture.codegen.runtime.VDMSet;

import cli.components.Form;
import cli.components.Table;
import cli.components.TableCell;
import cli.components.TableRow;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class WorkshopAttendees extends State {
	private Form form = new Form();
	private Table table = new Table();
	
	public WorkshopAttendees(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();
		
		String workshop = form.getValue("Workshop");

		TableRow header = new TableRow(true);
		header.addCell("Name", TableCell.SMALL);
		table.addRow(header);
	
		VDMSet attendees = sm.ws.GetAttendeesOfWorkshop(workshop);
		for (Object obj : attendees) {
			String attendee = (String) obj;
			TableRow row = new TableRow();
			row.addCell(attendee, TableCell.SMALL);
			table.addRow(row);
		}
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		form.addField("Workshop", "What is the name of the workshop?", Type.STRING);
	}

	@Override
	public void handleInput(Input input) {
		if (input.getType() == Key.ESCAPE) sm.popState();
		form.handleInput(input);
		if (form.isDone()) {
			buildTable();
		}
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Attendance Control > Workshop Attendees");
		if (!form.isDone()) form.display();
		else table.display();
	}
}
