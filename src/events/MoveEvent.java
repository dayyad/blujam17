package events;

import world.Entity;

/**
 * Handles moving the entity
 * @author Andrew McGhie
 */
public class MoveEvent extends Event {
    private final Entity entity;
    private double x,y;

    public MoveEvent(Entity entity, double x, double y) {
        super(Events.EventType.MOVE);
        this.entity = entity;
        this.x = x;
        this.y = y;
    }

    public Entity getEntity(){
        return this.entity;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void execute(){
        this.entity.move(this.x, this.y);
    }
}
