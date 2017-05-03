package events;

import world.World;

/**
 * This is an event for when a world is loaded
 * @author Andrew McGhie
 */
public class LevelLoadEvent extends Event {
    private final World world;

    public LevelLoadEvent(World world) {
        super(Events.EventType.LEVEL_LOAD);
        this.world = world;
    }

    public World getWorld(){
        return this.world;
    }
}
