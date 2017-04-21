package events;

import world.World;
public class Events {
	
	public enum EventType {
		
		//Generic
		MOVE,
		TICK,
		
		//Synchronisation
		SYNC_WORLD,
		SYNC_PHYSICS,
		
		//Spawning/Instantiation
		
		//Physics
		
		
		//Network
		
		//
	}
	
	public static Event newEvent(EventType type){
		return new Event(type);
	}
	
	public static Event newEvent(EventType type, Object context){
		return new Event(type, context);
	}
	
	public static Event newTickEvent(World context){
		return new Event(EventType.TICK, context);
	}
}
