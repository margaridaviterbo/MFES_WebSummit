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
import websummit.Conference;
import websummit.Date;

public class ConferencesByDay extends State {
	private Form form = new Form();
	private Table table = new Table();
	
	public ConferencesByDay(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();
		
		Date day = new Date(form.getValue("Day"));
		
		TableRow header = new TableRow(true);
		header.addCell("Name", TableCell.MEDIUM);
		header.addCell("Stage", TableCell.SMALL);
		header.addCell("Start", TableCell.SMALL);
		header.addCell("End", TableCell.SMALL);
		table.addRow(header);
	
		VDMSet conferences = sm.ws.GetConferencesOfDay(day);
		for (Object obj : conferences) {
			Conference conf = (Conference) obj;
			TableRow row = new TableRow();
			row.addCell(conf.Name, TableCell.MEDIUM);
			row.addCell(conf.Stage, TableCell.SMALL);
			row.addCell(conf.Start.toString(), TableCell.SMALL);
			row.addCell(conf.End.toString(), TableCell.SMALL);
			table.addRow(row);
		}
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		form.addField("Day", "What day should the conferences happen in (yyyy-MM-dd) ?", Type.DATE);
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
		Term.println("* Main Menu > Schedule > Conferences > Conferences By Days");
		if (!form.isDone()) form.display();
		else table.display();
	}
}
