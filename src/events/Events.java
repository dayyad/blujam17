package events;

import physics.Move;
import world.Entity;
import world.NPC;
import world.Player;
import world.Projectile;
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
	public static Event newLevelLoadEvent(World world) {return new LevelLoadEvent(world);}


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

	public static Event newStopEvent(Entity entity){
		return Events.newEvent(EventType.STOP, entity);
	}

	public static Event newShootEvent(Entity entity){
		return new Event(EventType.SHOOT, entity);
	}
}
