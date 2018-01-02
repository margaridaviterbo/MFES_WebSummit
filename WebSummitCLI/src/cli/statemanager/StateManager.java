package cli.statemanager;

import java.util.Stack;

import org.overture.codegen.runtime.MapUtil;
import org.overture.codegen.runtime.Maplet;
import org.overture.codegen.runtime.Utils;
import org.overture.codegen.runtime.VDMMap;

import websummit.WebSummit;

public class StateManager {
	private Stack<State> states = new Stack<>();
	
	private VDMMap TicketStock = MapUtil.map(
			new Maplet(websummit.quotes.CompanyQuote.getInstance(), 100L),
			new Maplet(websummit.quotes.AttendeeQuote.getInstance(), 1000L),
			new Maplet(websummit.quotes.VolunteerQuote.getInstance(), 200L));
	private VDMMap TicketPrice = MapUtil.map(
			new Maplet(websummit.quotes.CompanyQuote.getInstance(), 500L),
			new Maplet(websummit.quotes.AttendeeQuote.getInstance(), 250L));
	public WebSummit ws = new WebSummit(Utils.copy(TicketStock), Utils.copy(TicketPrice));
	
	public void pushState(State state) {
		state.onEnter();
		states.push(state);
	}
	
	public void popState() {
		states.pop();
	}
	
	public void changeState(State state) {
		if (!states.empty()) popState();
		pushState(state);
	}
	
	public State peekState() {
		if (states.empty()) return null;
		return states.peek();
	}
}
