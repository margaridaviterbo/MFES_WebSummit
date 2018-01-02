package cli.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import cli.statemanager.Input;
import cli.utils.Term;

public class FormField {
	public static enum Type { NUMBER, STRING, DATE, TIME }
	private Type type;
	private boolean done = false;
	private String prompt = "";
	private String value = "";
	private String error = "";
	
	public FormField(String p, Type t) {
		prompt = p;
		type = t;
	}
	
	private static final boolean isValidNumber(String val) {
		try {
			Float.parseFloat(val);
		} catch (Exception e) { 
			return false;
		}
		return true;
	}
	
	private static final boolean isValidString(String val) {
		return val.length() > 0;
	}
	
	private static final boolean isValidDate(String val) {
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
			parser.parse(val);
		} catch (ParseException e) {
			return false;
		}
        return true;
	}
	
	private static final boolean isValidTime(String val) {
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		try {
			parser.parse(val);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	private boolean isValid() {
		if (type == FormField.Type.NUMBER) return isValidNumber(value);
		if (type == FormField.Type.STRING) return isValidString(value);
		if (type == FormField.Type.DATE) return isValidDate(value);
		if (type == FormField.Type.TIME) return isValidTime(value);
		return false;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void handleInput(Input input) {
		switch (input.getType()) {
		case ENTER:
			if (isValid()) done = true;
			else error = "Bad input.";
			break;
		case BACKSPACE:
			value = value.substring(0, value.length() - 1);
			break;
		default:
			if (!error.isEmpty()) error = "";
			value += (char)input.getValue();
		}
	}
	
	public void display() {
		display(false);
	}
	
	public void display(boolean active) {
		Term.print("\t" + prompt, active);
		Term.println(" " + value);
		if (!error.isEmpty()) Term.println("\t\t" + error, true);
	}
	
	public String getValue() {
		return value;
	}
}
