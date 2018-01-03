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
import websummit.Talk;

public class TalksByDay extends State {
	private Form form = new Form();
	private Table table = new Table();
	
	public TalksByDay(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();
		
		Date date = new Date(form.getValue("Date"));

		TableRow header = new TableRow(true);
		header.addCell("Title", TableCell.MEDIUM);
		header.addCell("Speaker", TableCell.SMALL);
		header.addCell("Conference", TableCell.SMALL);
		header.addCell("Date", TableCell.SMALL);
		header.addCell("Start", TableCell.SMALL);
		header.addCell("End", TableCell.SMALL);
		table.addRow(header);
	
		VDMSet talks = sm.ws.GetTalksOfDay(date);
		for (Object obj : talks) {
			Talk talk = (Talk) obj;
			TableRow row = new TableRow();
			row.addCell(talk.Title, TableCell.MEDIUM);
			row.addCell(talk.Speaker, TableCell.SMALL);
			row.addCell(talk.Conference, TableCell.SMALL);
			row.addCell(talk.Date.toString(), TableCell.SMALL);
			row.addCell(talk.Start.toString(), TableCell.SMALL);
			row.addCell(talk.End.toString(), TableCell.SMALL);
			table.addRow(row);
		}
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		form.addField("Date", "What day should the talks happen in (yyyy-MM-dd) ?", Type.DATE);
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
		Term.println("* Main Menu > Schedule > Talks > Talks By Day");
		if (!form.isDone()) form.display();
		else table.display();
	}
}
