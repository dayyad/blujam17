package events;

import java.util.ArrayList;
import java.util.Collection;
import events.Events.EventType;
import world.Entity;

public class Event {
	private EventType type;
	private Object context = null;

	/**
	 * Actors, one or more object that event is focused around, can be in
	 * addition to the context
	 */
	private Collection<Object> actors = new ArrayList<>();
	
	public Event(EventType type) {
		this.type = type;
	}

	public Event(EventType type, Object context) {
		this.type = type;
		this.context = context;
	}
	
	/**
	 * 
	 * @param e An entity to add as actor
	 */
	public void addActor(world.Entity e) {
		this.actors.add(e);
	}

	/**
	 * @param e collection of entities to be added as actors
	 */
	public void addActors(Collection<world.Entity> e){
		this.actors.addAll(e);
	}

	public Object getContext() {
		return this.context;
	}

	public Events.EventType getType() {
		return this.type;
	}

}
