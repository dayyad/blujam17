package events;

public class Events {
	
	public enum EventType {
		
	}
	
	public Event newEvent(EventType type){
		return new Event(type);
	}
}
