package world;

import events.Event;
import events.Observable;
import events.Subject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class World extends Subject implements Observable {

	private Player player;

	// Map fields
	private Level currentLevel;

	public int level = 1;

	public void resetLevels(){this.level = 1;}

	public void setCurrentLevel(Level level){
		this.currentLevel = level;
	}

	public World() {
	}

	// Filters new entities into their relevant places.
	public void addEntity(Entity entity) {
		if (entity instanceof Player) {
			this.player = (Player)entity;
			return;
		}
		currentLevel.addEntity(entity);
	}

	public synchronized Collection<Entity> getEntitiesWithType(String type) {
		if (type.equals("Player") && this.player != null) {
			return Arrays.asList(this.player);
		}
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
			this.notifyObservers(event);
			break;
		}
	}
}
