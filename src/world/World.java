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

public class World extends Subject implements Observerable {
	// private Player player;

	// Holds EVERY entitiy
	private Set<Entity> entities = new HashSet<>();

	/**
	 *  Used to further sub classify entities.
	 *  
	 *  KEY:
	 *  Collidable, Renderable, Animatable, NPC
	 *  
	 */

	private Map<String, Set<Entity>> entityMap = new HashMap<>();

	//Map fields
	
	
	
	
	public World() {
		super();
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
	 * called whenever adding new entity of this type to world.
	 * 
	 * checks if entity is present in other data structures and adds to them if
	 * not.
	 *
	 */
	
	public void addCollidableEntity(Entity e) {
		entities.add(e);
		entityMap.get("Collidable").add(e);
	}
	
	public void addRenderableEntity(Entity e){
		entities.add(e);
		entityMap.computeIfAbsent("Renderable", (key) -> entityMap.put(key, new HashSet<>()));
		entityMap.get("Renderable").add(e);
	}

	/**
	 * @return gets entities that implement collidable
	 */

	public Collection<Entity> getCollidableEntities() {
		List<Entity> returnList = new ArrayList<>();

		for (Entity e : this.entities) {
			if (e instanceof world.movement.Collidable) {
				returnList.add(e);
			}
		}

		return returnList;
	}

	/**
	 * @return gets entities that implement renderable
	 */
	public Collection<Entity> getRenderableEntities() {
		return entityMap.get("Renderable");
	}

	public Collection<Entity> getNPCS() {
		List<Entity> returnList = new ArrayList<>();

		for (Entity e : this.entities) {
			if (e instanceof world.NPC) {
				returnList.add(e);
			}
		}

		return returnList;
	}

	/**
	 * @return gets players entity
	 */
	public Entity getPlayerEntity() {
		// return this.player.getEntity();
		return null;
	}

	/**
	 * Gets the collision map for the currently loaded map
	 * 
	 * @return
	 */
	public Color[][] getCollisionMap() {
		return null;
	}

	public Collection<Entity> getEntities() {
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
