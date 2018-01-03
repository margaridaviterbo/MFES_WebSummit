package cli.states;

import org.overture.codegen.runtime.VDMSet;

import cli.components.Form;
import cli.components.Table;
import cli.components.TableRow;
import cli.components.FormField.Type;
import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;
import websummit.News;

public class ReadNewsAboutConference extends State {
	private static final int widths[] = { 20, 20, 30, 80 }; 

	private Form form = new Form();
	private Table table = new Table();
	
	public ReadNewsAboutConference(StateManager stateManager) {
		super(stateManager);
	}
	
	private void buildTable() {
		table = new Table();
		
		String conf = form.getValue("Conf");
		
		TableRow header = new TableRow(true);
		header.addCell("Author", widths[0]);
		header.addCell("Conference", widths[1]);
		header.addCell("Title", widths[2]);
		header.addCell("Content", widths[3]);
		table.addRow(header);
	
		VDMSet news = sm.ws.GetNewsAboutConf(conf);
		for (Object obj : news) {
			News article = (News) obj;
			TableRow row = new TableRow();
			row.addCell(article.Author, widths[0]);
			row.addCell(article.Conference, widths[1]);
			row.addCell(article.Title, widths[2]);
			row.addCell(article.Content, widths[3]);
			table.addRow(row);
		}
	}
	
	@Override
	public void onEnter() {
		form = new Form();
		form.addField("Conf", "What is the name of the conference?", Type.STRING);
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
		Term.println("* Main Menu > News Management > Read News About Conference");
		if (!form.isDone()) form.display();
		else table.display();
	}
}
