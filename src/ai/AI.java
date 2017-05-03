package ai;

import events.Event;
import events.Events;
import events.Observable;
import events.Subject;
import physics.Physics;
import world.Entity;
import world.World;;

/**
 * Manages the AI entities in the world
 * TODO set the AI to move towards a target and choose new target if collision
 * @author Andrew McGhie
 */
public class AI extends Subject implements Observable {
	private MovementManager movementManager;

	private final World world;
	private final Physics physics;

	// TODO either create on level load or no world or physics dependancy
	public AI (World world, Physics physics){
		this.world = world;
		this.physics = physics;
		this.addObserver(this.physics);
	}
	
	@Override
	public void update(Event event) {
		switch (event.getType()){
			case TICK:
				// This calculates where all the AI characters should move and notifies observers
				for (Entity entity : this.world.getEntitiesWithType("NPC")){
					double[] movement = this.movementManager.getMovement(entity);
					this.notifyObservers(Events.newMoveEvent(entity, movement[0], movement[1]));
				}
				break;
			case LOAD:
				this.movementManager = new MovementManager(((World)event.getContext()).getEntitiesWithType("Player").iterator().next());
				break;
		}
	}
}
