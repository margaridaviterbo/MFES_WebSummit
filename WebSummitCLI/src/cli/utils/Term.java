package cli.utils;

public class Term {
	public static final void init() {
		String[] raw = {"/bin/sh", "-c", "stty raw </dev/tty"};
		String[] echo = {"/bin/sh", "-c", "stty -echo </dev/tty"};
	    try {
			Runtime.getRuntime().exec(raw).waitFor();
			Runtime.getRuntime().exec(echo).waitFor();
		} catch(Exception e) {}
	}
	
	public static final void close() {
		String[] raw = {"/bin/sh", "-c", "stty cooked </dev/tty"};
		String[] echo = {"/bin/sh", "-c", "stty echo </dev/tty"};
		try {
			Runtime.getRuntime().exec(raw).waitFor();
			Runtime.getRuntime().exec(echo).waitFor();
		} catch(Exception e) {}
		resetColor();
		clear();
	}
	
	private static final void reverseColor() {
		System.out.print((char) 27 + "[7m");
	}
	
	private static final void resetColor() {
		System.out.print((char) 27 + "[0m");
	}
	
	public static final void print(String message) {
		print(message, false);
	}
	
	public static final void print(String message, boolean hightlight) {
		if (hightlight) reverseColor();
		System.out.print(message);
		resetColor();
	}
	
	public static final void println(String message) {
		println(message, false);
	}
	
	public static final void println(String message, boolean highlight) {
		print(message, highlight);
		System.out.print("\r\n");
	}
	
	public static final void clear() {
		System.out.print("\033[H\033[2J");  
	    System.out.flush();
	}
}
