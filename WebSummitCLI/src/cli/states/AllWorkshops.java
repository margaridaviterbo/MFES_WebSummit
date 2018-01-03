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
import websummit.Workshop;

public class AllWorkshops extends State {
	private Table table = new Table();
	
	public AllWorkshops(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();

		TableRow header = new TableRow(true);
		header.addCell("Name", TableCell.SMALL);
		header.addCell("Subject", TableCell.SMALL);
		header.addCell("Company", TableCell.SMALL);
		header.addCell("Stage", TableCell.SMALL);
		header.addCell("Vacancies", TableCell.SMALL);
		header.addCell("Date", TableCell.SMALL);
		header.addCell("Start", TableCell.SMALL);
		header.addCell("End", TableCell.SMALL);
		table.addRow(header);
	
		VDMSet workshops = MapUtil.rng(sm.ws.Workshops);
		for (Object obj : workshops) {
			Workshop workshop = (Workshop) obj;
			TableRow row = new TableRow();
			row.addCell(workshop.Name, TableCell.SMALL);
			row.addCell(workshop.Subject, TableCell.SMALL);
			row.addCell(workshop.Company, TableCell.SMALL);
			row.addCell(workshop.Stage, TableCell.SMALL);
			row.addCell(workshop.Vacancies.toString(), TableCell.SMALL);
			row.addCell(workshop.Date.toString(), TableCell.SMALL);
			row.addCell(workshop.Start.toString(), TableCell.SMALL);
			row.addCell(workshop.End.toString(), TableCell.SMALL);
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
		Term.println("* Main Menu > Schedule > Workshops > All Workshops");
		table.display();
	}
}
