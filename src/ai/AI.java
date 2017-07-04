package ai;

import events.Event;
import events.LevelLoadEvent;
import events.Observable;
import events.Subject;
import world.Entity;
import world.World;

/**
 * Manages the AI entities in the world
 * TODO set the AI to move towards a target and choose new target if collision
 * @author Andrew McGhie
 */
public class AI extends Subject implements Observable {
	private MovementManager movementManager;
	private World world;
	
	@Override
	public void update(Event event) {
		switch (event.getType()){
			case TICK:
				// This calculates where all the AI characters should move and notifies observers
				for (Entity entity : this.world.getEntitiesWithType("NPC")){
					this.notifyObservers(this.movementManager.getMovement(entity));
				}
				break;
			case LEVEL_LOAD:
				LevelLoadEvent levelLoadEvent = (LevelLoadEvent)event;
				this.world = levelLoadEvent.getWorld();
				if(this.world.getEntitiesWithType("Player").size() == 0)break;
				this.movementManager = new MovementManager(this.world.getEntitiesWithType("Player").iterator().next());
				break;
		}
	}
}
