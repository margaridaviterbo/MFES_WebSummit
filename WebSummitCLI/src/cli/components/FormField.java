package cli.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cli.statemanager.Input;
import cli.utils.Term;

public class FormField {
	public static enum Type { NUMBER, STRING, DATE, TIME, CHOICE }
	private Type type;
	private boolean done = false;
	private String prompt = "";
	private String value = "";
	private String error = "";
	private List<String> choices = new ArrayList<>();
	private int choice = 0;
	
	public FormField(String p, Type t) {
		prompt = p;
		type = t;
	}
	
	public void addChoice(String choice) {
		if (!choices.contains(choice)) choices.add(choice);
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
	
	private static final boolean isValidChoice(String val, List<String> choices) {
		return choices.contains(val);
	}
	
	private boolean isValid() {
		if (type == Type.NUMBER) return isValidNumber(value);
		if (type == Type.STRING) return isValidString(value);
		if (type == Type.DATE) return isValidDate(value);
		if (type == Type.TIME) return isValidTime(value);
		if (type == Type.CHOICE) return isValidChoice(value, choices);
		return false;
	}
	
	public boolean isDone() {
		return done;
	}
	
	private void previousChoice() {
		if (choice > 0) choice--;
	}
	
	private void nextChoice() {
		if (choice < choices.size() - 1) choice++;
	}
	
	public void handleInput(Input input) {
		switch (input.getType()) {
		case ENTER:
			if (type == Type.CHOICE) value = choices.get(choice);
			if (isValid()) done = true;
			else error = "Bad input.";
			break;
		case BACKSPACE:
			if (value.length() > 0 && type != Type.CHOICE) value = value.substring(0, value.length() - 1);
			break;
		case ARROW_LEFT:
			if (type == Type.CHOICE) previousChoice();
			break;
		case ARROW_RIGHT:
			if (type == Type.CHOICE) nextChoice();
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
		if (type == Type.CHOICE) {
			Term.print("  ");
			for (int i = 0; i < choices.size(); ++i) {
				Term.print(choices.get(i), i == choice);
				Term.print("  ");
			}
			Term.println("");
		} else {
			Term.println(" " + value);
		}
		if (!error.isEmpty()) Term.println("\t\t" + error, true);
	}
	
	public String getValue() {
		return value;
	}
}
