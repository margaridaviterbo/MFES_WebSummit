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
import websummit.Venue;

public class AllVenues extends State {
	private Table table = new Table();
	
	public AllVenues(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();

		TableRow header = new TableRow(true);
		header.addCell("Name", TableCell.MEDIUM);
		header.addCell("Start", TableCell.SMALL);
		header.addCell("End", TableCell.SMALL);
		table.addRow(header);
	
		VDMSet talks = MapUtil.rng(sm.ws.Venues);
		for (Object obj : talks) {
			Venue venue = (Venue) obj;
			TableRow row = new TableRow();
			row.addCell(venue.Name, TableCell.MEDIUM);
			row.addCell(venue.Start.toString(), TableCell.SMALL);
			row.addCell(venue.End.toString(), TableCell.SMALL);
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
		Term.println("* Main Menu > Venue Management > All Venues");
		table.display();
	}
}
