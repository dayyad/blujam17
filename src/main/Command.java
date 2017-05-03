package main;

import world.Entity;

/**
 * TODO remove everything can be done from Events
 */
public abstract class Command {
    Entity actor;
    public void setActor(Entity actor){this.actor = actor;}
    public Entity getActor(){return this.actor;}
    
    public abstract void execute();
}
