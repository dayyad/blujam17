package events;

import physics.Move;
import world.Entity;
import world.World;

public class Events {
	
	public enum EventType {
		
		//Generic
		MOVE,
		TICK,
		LOAD,
		INITIAL_LOAD,
		LOAD_LEVEL,
		MENU_UPDATE,
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

	public static Event newInitialLoadEvent(){
		return Events.newEvent(EventType.INITIAL_LOAD);
	}

	public static Event newLoadLevelEvent() {return Events.newEvent(EventType.LOAD_LEVEL);}

	public static Event newLoadEvent(World world) {return Events.newEvent(EventType.LOAD, world);}
	
	public static Event newCollisionEvent(world.Entity e1, world.Entity e2){
		Event tempEvent = new Event(EventType.PHYSICS_COLLISION);
		tempEvent.addActor(e1);
		tempEvent.addActor(e2);
		return tempEvent;
	}
	
	public static Event newTickEvent(){
		return new Event(EventType.TICK);
	}

	public static Event newMoveEvent(Entity entity, double x, double y){
		Event moveEvent = new Event(EventType.MOVE, new Move(entity, x, y));
		moveEvent.addActor(entity);
		return moveEvent;
	}

	public static Event newMoveEvent(Move move){
		assert move.getActor() != null : "The entity should not be null here";
		Event moveEvent = new Event(EventType.MOVE, move);
		moveEvent.addActor(move.getActor());
		return moveEvent;
	}

	public static Event newMenuUpdate(){
		return Events.newEvent(EventType.MENU_UPDATE);
	}
}
