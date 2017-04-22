package world;

import java.util.Collection;
import java.util.HashMap;
import java.util.*;

public class Level {
	Stage stage;
	Collection<Entity> entities = new ArrayList<>();
	Map<String, Set<Entity>> entityMap = new HashMap<>();

	public Level(){
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
		return entityMap.get(type);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);

		for (Collection<Entity> col : entityMap.values()) {
			col.remove(entity);
		}
	}
}
