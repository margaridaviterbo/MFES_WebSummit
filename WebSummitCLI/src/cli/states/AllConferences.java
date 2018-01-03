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
import websummit.Conference;

public class AllConferences extends State {
	private Table table = new Table();
	
	public AllConferences(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();

		TableRow header = new TableRow(true);
		header.addCell("Name", TableCell.MEDIUM);
		header.addCell("Stage", TableCell.SMALL);
		header.addCell("Start", TableCell.SMALL);
		header.addCell("End", TableCell.SMALL);
		table.addRow(header);
	
		VDMSet conferences = MapUtil.rng(sm.ws.Conferences);
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
		buildTable();
	}

	@Override
	public void handleInput(Input input) {
		if (input.getType() == Key.ESCAPE) sm.popState();
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > Schedule > Conferences > All Conferences");
		table.display();
	}
}
