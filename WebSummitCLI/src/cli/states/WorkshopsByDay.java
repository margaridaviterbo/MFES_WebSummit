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
import websummit.Date;

public class WorkshopsByDay extends State {
	private Form form = new Form();
	private Table table = new Table();
	
	public WorkshopsByDay(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();
		
		Date date = new Date(form.getValue("Date"));

		TableRow header = new TableRow(true);
		header.addCell("Name", TableCell.SMALL);
		table.addRow(header);
	
		VDMSet workshops = sm.ws.GetWorkshopsOfDay(date);
		for (Object obj : workshops) {
			String workshop = (String) obj;
			TableRow row = new TableRow();
			row.addCell(workshop, TableCell.SMALL);
			table.addRow(row);
		}
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		form.addField("Date", "What day should the workshops happen in (yyyy-MM-dd) ?", Type.DATE);
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
		Term.println("* Main Menu > Schedule > Workshops > Workshops By Day");
		if (!form.isDone()) form.display();
		else table.display();
	}
}
