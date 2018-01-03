package cli.components;

import cli.utils.Term;

public class TableCell {
	public static final int SMALL = 20;
	public static final int MEDIUM = 40;
	public static final int LARGE = 80;
	
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
