package world;

import java.awt.Image;
import java.util.Collection;

import events.Event;
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
	
	/**
	 * Gets the collision map for the currently loaded map
	 * @return
	 */
	public Image getCollisionMap(){
		return null;
	}
	
	public Collection<Entity> getEntities(){
		return null;
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
