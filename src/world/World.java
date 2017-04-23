package world;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import events.Event;
import main.Observerable;
import main.Subject;
import renderer.Renderable;

public class World extends Subject implements Observerable {

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

	@Override
	protected void initObservers() {

	}
}
