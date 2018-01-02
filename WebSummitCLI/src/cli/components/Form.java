package cli.components;

import java.util.HashMap;
import java.util.Map;

import cli.components.FormField.Type;
import cli.statemanager.Input;

public class Form {
	public Map<String, FormField> fields = new HashMap<>();
	private Map<Integer, String> lookup = new HashMap<>();
	private int field = 0;
	private boolean done = false;
	
	public void addField(String key, String prompt, Type type) {
		FormField field = new FormField(prompt, type);
		lookup.put(fields.size(), key);
		fields.put(key, field);
	}
	
	private void nextField() {
		field++;
	} 
	
	private boolean noMoreFields() {
		return field >= fields.size();
	}
	
	public boolean isDone() {
		return done;
	}
	
	public String getValue(String key) {
		return fields.get(key).getValue();
	}
	
	public void handleInput(Input input) {
		if (isDone()) return;
		FormField currentField = fields.get(lookup.get(field));
		currentField.handleInput(input);
		if (currentField.isDone()) nextField();
		if (noMoreFields()) done = true;
	}
	
	public void display() {
		int len = noMoreFields() ? fields.size() : field + 1; 
		for (int i = 0; i < len; ++i) {
			FormField currentField = fields.get(lookup.get(i));
			currentField.display(i == field);
		}
	}
}
