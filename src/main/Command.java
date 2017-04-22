package main;

import world.Entity;

/**
 * Created by mgoo on 22/04/17.
 */
public abstract class Command {
    Entity actor;
    public void setActor(Entity actor){this.actor = actor;}
    public Entity getActor(){return this.actor;}
    
    public abstract void execute();
}
