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
		PHYSICS_COLLISION,
		
		//Network
		
		//
	}
	
	public static Event newEvent(EventType type){
		return new Event(type);
	}
	
	public static Event newEvent(EventType type, Object context){
		return new Event(type, context);
	}
	
	public static Event newCollisionEvent(world.Entity e1, world.Entity e2){
		Event tempEvent = new Event(EventType.PHYSICS_COLLISION);
		tempEvent.addActor(e1);
		tempEvent.addActor(e2);
		return tempEvent;
	}
	
	public static Event newTickEvent(World context){
		return new Event(EventType.TICK, context);
	}
}
