package cli.statemanager;

public class Input {
	public enum Key { ARROW_UP, ARROW_DOWN, ARROW_LEFT, ARROW_RIGHT, ENTER, ESCAPE, BACKSPACE, OTHER }
	
	private Key type;
	private int value;
	
	public Input(int input) {
		switch (input) {
		case 279165:
			type = Key.ARROW_UP;
			break;
		case 279166:
			type = Key.ARROW_DOWN;
			break;
		case 279167:
			type = Key.ARROW_RIGHT;
			break;
		case 279168:
			type = Key.ARROW_LEFT;
			break;
		case 13:
			type = Key.ENTER;
			break;
		case 27:
			type = Key.ESCAPE;
			break;
		case 127:
			type = Key.BACKSPACE;
			break;
		default:
			type = Key.OTHER;
			break;
		}
		value = input;
	}
	
	public Key getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}
}
