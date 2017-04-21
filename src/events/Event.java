package events;

import events.Events.EventType;

public class Event {
	private EventType type;
	private Object context;	
	
	public Event(EventType type){
		this.type = type;
		this.context = null;
	}
	
	public Event(EventType type, Object context){
		this.type = type;
		this.context = context;
	}
	
	public Object getContext(){
		return this.context;
	}
	
	public Events.EventType getType(){
		return this.type;
	}
	
}
