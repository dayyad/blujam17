package events;

public class Events {
	
	public enum EventType {
		MOVE,
		TICK,
		
		UPDATE_WORLD,
		UPDATE_PHYSICS
	}
	
	public static Event newEvent(EventType type){
		return new Event(type);
	}
	
	public static Event newEvent(EventType type, Object context){
		return new Event(type);
	}
}
