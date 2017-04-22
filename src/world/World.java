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
	// private Player player;

	// Holds EVERY entitiy
	private Set<Entity> entities = new HashSet<>();

	/**
	 * Used to further sub classify entities.
	 * 
	 * KEY: Collidable, Renderable, Animatable, NPC
	 * 
	 */

	private Map<String, Set<Entity>> entityMap = new HashMap<>();
	// Map fields
	private Level currentLevel;

	public World() {
		super();
		// creates empty lists for all entity interfaces
		initEntityMap();
	}

	private void initEntityMap() {
		entityMap.put("Collidable", new HashSet<Entity>());
		entityMap.put("Renderable", new HashSet<Entity>());
		entityMap.put("Animatable", new HashSet<Entity>());
		entityMap.put("NPC", new HashSet<Entity>());
		entityMap.put("Player", new HashSet<Entity>());
		entityMap.put("Stage", new HashSet<Entity>());
	}

	// Filters new entities into their relevant places.
	public void addEntity(Entity entity) {

		if (entity instanceof Player) {
			entityMap.get("Player").add(entity);
		}

		if (entity instanceof Stage) {
			entityMap.get("Stage").add(entity);
		}

		if (entity instanceof NPC) {
			entityMap.get("NPC").add(entity);
		}

		if (entity instanceof Renderable) {
			entityMap.get("Renderable").add(entity);
		}

		if (entity instanceof Animatable) {
			entityMap.get("Animatable").add(entity);
		}

		if (entity instanceof world.movement.Collidable) {
			entityMap.get("Collidable").add(entity);
		}

		entities.add(entity);
	}

	public Collection<Entity> getEntitiesWithType(String type) {
		Set<Entity> returnEnts = new HashSet<>();
		returnEnts.addAll(entityMap.get(type));
		if(currentLevel != null){returnEnts.addAll(currentLevel.getEntitiesWithType(type));}
		return returnEnts;
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);

		for (Collection<Entity> col : entityMap.values()) {
			col.remove(entity);
		}
		if (currentLevel != null) {
			currentLevel.removeEntity(entity);
		}
	}

	public boolean hasEntity(Entity lookup) {
		for (Entity e : entities) {
			if (e == lookup) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the collision map for the currently loaded map
	 * 
	 * @return
	 */
	public Color[][] getStageCollisionMap() {
		return null;
	}

	public Collection<Entity> getEntities() {
		return this.entities;
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
