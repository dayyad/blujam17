package world;

import renderer.Renderable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Level {
    Stage stage;
    Collection<Entity> entities = new ArrayList<>();
    Map<String, List<Entity>> entityMap = new ConcurrentHashMap<>();

    public Level() {
        initEntityMap();
    }

    private void initEntityMap() {
        entityMap.put("Collidable", new CopyOnWriteArrayList<>());
        entityMap.put("Renderable", new CopyOnWriteArrayList<>());
        entityMap.put("Animatable", new CopyOnWriteArrayList<>());
        entityMap.put("NPC", new CopyOnWriteArrayList<>());
        entityMap.put("Player", new CopyOnWriteArrayList<>());
        entityMap.put("Stage", new CopyOnWriteArrayList<>());
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
