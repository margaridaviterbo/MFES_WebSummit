package cli.components;

import cli.utils.Term;

public class TableCell {
	private String value;
	
	public TableCell(String val, int width) {
		value = val;
		if (value.length() >= width) {
			value = value.substring(0, width - 3);
			value += "...";
		} else {
			while (value.length() < width) {
				value += " ";
			}
		}
	}
	
	public void display(boolean highlight) {
		Term.print(value, highlight);
	}
}
