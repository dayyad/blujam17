package events;

import events.Events.EventType;

public class Event {
	EventType type;
	Object context;	
	
	public Event(EventType type){
		this.type = type;
		this.context = null;
	}
	
	public Event(EventType type, Object context){
		this.type = type;
		this.context = context;
	}
	
}
