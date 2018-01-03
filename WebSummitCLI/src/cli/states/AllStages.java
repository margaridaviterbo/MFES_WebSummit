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
import websummit.Stage;

public class AllStages extends State {
	private Table table = new Table();
	
	public AllStages(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();

		TableRow header = new TableRow(true);
		header.addCell("Name", TableCell.MEDIUM);
		header.addCell("Venue", TableCell.SMALL);
		header.addCell("Start", TableCell.SMALL);
		header.addCell("End", TableCell.SMALL);
		table.addRow(header);
	
		VDMSet talks = MapUtil.rng(sm.ws.Stages);
		for (Object obj : talks) {
			Stage stage = (Stage) obj;
			TableRow row = new TableRow();
			row.addCell(stage.Name, TableCell.MEDIUM);
			row.addCell(stage.Venue, TableCell.SMALL);
			row.addCell(stage.Start.toString(), TableCell.SMALL);
			row.addCell(stage.End.toString(), TableCell.SMALL);
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
		Term.println("* Main Menu > Venue Management > All Stages");
		table.display();
	}
}
