package events;

import world.Entity;
import world.World;

/**
 * This is essentually an event factory
 * TODO make separate objects for different events
 * @author Andrew McGhie
 */
public class Events {
	
	public enum EventType {
		
		//Generic
		MOVE,
		STOP,
		TICK,
		LEVEL_LOAD,
		INITIAL_LOAD,
		MENU_UPDATE,
		SHOOT,
		DIE,
		//Spawning/Instantiation
		
		//Physics
		PHYSICS_COLLISION,

	}
	public static LevelLoadEvent newLevelLoadEvent(World world) {
		return new LevelLoadEvent(world);
	}

	public static MoveEvent newMoveEvent(Entity entity, double x, double y){
		return new MoveEvent(entity, x, y);
	}

	public static CollisionEvent newCollisionEvent(world.Entity e1, world.Entity e2){
		return new CollisionEvent(e1, e2);
	}


	public static Event newDieEvent(){
		return Events.newEvent(EventType.DIE);
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
	

	
	public static Event newTickEvent(){
		return new Event(EventType.TICK);
	}



	public static Event newMenuUpdate(){
		return Events.newEvent(EventType.MENU_UPDATE);
	}

	public static Event newStopEvent(Entity entity){
		return Events.newEvent(EventType.STOP, entity);
	}

	public static Event newShootEvent(Entity entity){
		return new Event(EventType.SHOOT, entity);
	}
}
