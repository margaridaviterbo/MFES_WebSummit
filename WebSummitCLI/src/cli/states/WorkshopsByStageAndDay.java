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
import websummit.Workshop;

public class WorkshopsByStageAndDay extends State {
	private Form form = new Form();
	private Table table = new Table();
	
	public WorkshopsByStageAndDay(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();
		
		String stage = form.getValue("Stage");
		Date date = new Date(form.getValue("Date"));

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
	
		VDMSet workshops = sm.ws.GetWorkshopsInStageOfDay(stage, date);
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
		form = new Form();
		form.addField("Stage", "In what stage should the workshops be happening?", Type.STRING);
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
		Term.println("* Main Menu > Schedule > Workshops > Workshops By Stage And Day");
		if (!form.isDone()) form.display();
		else table.display();
	}
}
