package world;

import events.Event;
import events.Events;
import main.Observerable;
import main.Subject;

public class World extends Subject implements Observerable {
	//private Player player;
	
	public World(){
		super();
	}
	
	/**
	 * @return gets players entity
	 */
	public Entity getPlayerEntity(){
		//return this.player.getEntity();
		return null;
	}

	public void removedBlock(){
		Event e = Events.newEvent(Events.EventType.UPDATE_WORLD, this);
		notify(e);
	}
	
	@Override
	public void update(Event event) {
		switch (event.getType()){
		
		case TICK :
			break;
		}
	}

	@Override
	protected void initObservers() {
		
	}
}
