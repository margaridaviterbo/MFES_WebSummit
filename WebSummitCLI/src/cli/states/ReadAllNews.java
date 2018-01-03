package cli.states;

import org.overture.codegen.runtime.VDMSet;

import cli.components.Table;
import cli.components.TableRow;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;
import websummit.News;

public class ReadAllNews extends State {
	private static final int widths[] = { 20, 30, 80 }; 

	private Table table = new Table();
	
	public ReadAllNews(StateManager stateManager) {
		super(stateManager);
	}
	
	@Override
	public void onEnter() {
		table = new Table();
		
		TableRow header = new TableRow(true);
		header.addCell("Author", widths[0]);
		header.addCell("Title", widths[1]);
		header.addCell("Content", widths[2]);
		table.addRow(header);
	
		VDMSet news = sm.ws.GetAllNews();
		for (Object obj : news) {
			News article = (News) obj;
			TableRow row = new TableRow();
			row.addCell(article.Author, widths[0]);
			row.addCell(article.Title, widths[1]);
			row.addCell(article.Content, widths[2]);
			table.addRow(row);
		}
	}

	@Override
	public void handleInput(Input input) {
		if (input.getType() == Key.ESCAPE) sm.popState();
	}

	@Override
	public void display() {
		Term.clear();
		Term.println("* Main Menu > News Management > Read All News");
		table.display();
	}
}
