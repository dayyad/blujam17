package world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import events.Event;
import events.Observable;
import events.Subject;

public class World extends Subject implements Observable {

	// Map fields
	private Level currentLevel;
	public int level = 1;

	public void resetLevels(){this.level = 1;}

	public void setCurrentLevel(Level level){
		this.currentLevel = level;
	}

	public World() {
		super();
	}

	// Filters new entities into their relevant places.
	public void addEntity(Entity entity) {
		currentLevel.addEntity(entity);
	}

	public synchronized Collection<Entity> getEntitiesWithType(String type) {
		if (currentLevel == null)return new ArrayList<>();
		return currentLevel.getEntitiesWithType(type);
	}

	public void removeEntity(Entity entity) {
		currentLevel.removeEntity(entity);
	}

	/**
	 * Gets the collision map for the currently loaded map
	 * 
	 * @return
	 */
	public Color[][] getStageCollisionMap() {
		return null;
	}


	@Override
	public void update(Event event) {
		switch (event.getType()) {
		case TICK:
			break;
		}
	}
}
