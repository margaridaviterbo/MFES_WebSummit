package cli.components;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<TableRow> rows = new ArrayList<>();
	
	public void addRow(TableRow row) {
		rows.add(row);
	}
	
	public void display() {
		for (TableRow row : rows) {
			row.display();
		}
	}
}
