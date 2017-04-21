package input;

import events.Event;
import events.Events;
import main.Subject;

public class Input extends Subject{
	//LISTERns
	
	public Input(){
		
		setDependicies();
	}
	
	private void mouseDown(){
		Move move = new Move();
		Event e = Events.newEvent(Events.EventType.MOVE, move);
		this.notify(e);
	}
	
	private void setDependicies(){
		
	}
}
