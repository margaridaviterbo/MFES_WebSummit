package cli.components;

import java.util.ArrayList;
import java.util.List;

import cli.utils.Term;

public class TableRow {
	private List<TableCell> cells = new ArrayList<>();
	private boolean header = false;
	
	public TableRow() {}
	
	public TableRow(boolean h) {
		header = h;
	}
	
	public void addCell(String value, int width) {
		cells.add(new TableCell(value, width));
	}
	
	public void display() {
		for (TableCell cell : cells) {
			cell.display(header);
			Term.print(" ");
		}
		Term.println("");
	}
}
