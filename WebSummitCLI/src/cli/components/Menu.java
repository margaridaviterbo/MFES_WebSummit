package cli.components;

import java.util.HashMap;
import java.util.Map;

import cli.statemanager.Input;
import cli.statemanager.State;
import cli.statemanager.StateManager;
import cli.statemanager.Input.Key;
import cli.utils.Term;

public class Menu {
	private int option = 0;
	private Map<String, State> options = new HashMap<>();
	private StateManager sm;
	
	public Menu(StateManager stateManager) {
		sm = stateManager;
	}
	
	private void previousOption() {
		if (option > 0) option--;
	}
	
	private void nextOption() {
		if (option < options.size() - 1) option++;
	}
	
	public void addOption(String key, State value) {
		options.put(key, value);
	}
	
	private State getSelectedOption() {
		String[] keys = options.keySet().toArray(new String[options.size()]);
		for (int i = 0; i < keys.length; ++i) {
			if (option == i) return options.get(keys[i]); 
		}
		return null;
	}
	
	private void selectOption() {
		State next = getSelectedOption();
		sm.pushState(next);
	}
	
	public void handleInput(Input input) {
		if (input.getType() == Key.ARROW_UP) previousOption();
		if (input.getType() == Key.ARROW_DOWN) nextOption();
		if (input.getType() == Key.ENTER) selectOption();
	}
	
	public void display() {
		String[] keys = options.keySet().toArray(new String[options.size()]);
		for (int i = 0; i < keys.length; ++i) {
			if (option == i) Term.println("\t" + keys[i], true);
			else Term.println("\t" + keys[i]);
		}
	}
}
