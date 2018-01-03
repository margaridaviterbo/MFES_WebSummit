package cli.states;

import org.overture.codegen.runtime.MapUtil;
import org.overture.codegen.runtime.VDMSet;

import cli.components.Table;
import cli.components.TableCell;
import cli.components.TableRow;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;
import websummit.Talk;

public class AllTalks extends State {
	private Table table = new Table();
	
	public AllTalks(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();

		TableRow header = new TableRow(true);
		header.addCell("Title", TableCell.MEDIUM);
		header.addCell("Speaker", TableCell.SMALL);
		header.addCell("Conference", TableCell.SMALL);
		header.addCell("Date", TableCell.SMALL);
		header.addCell("Start", TableCell.SMALL);
		header.addCell("End", TableCell.SMALL);
		table.addRow(header);
	
		VDMSet talks = MapUtil.rng(sm.ws.Talks);
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
		buildTable();
	}

	@Override
	public void handleInput(Input input) {
		if (input.getType() == Key.ESCAPE) sm.popState();
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Schedule > Talks > All Talks");
		table.display();
	}
}
