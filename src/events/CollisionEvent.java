package events;

import world.*;

/**
 * Event for a collision between two entities
 * @author Andrew McGhie
 */
public class CollisionEvent extends Event {

    private final Entity entity1;
    private final Entity entity2;

    public CollisionEvent(Entity entity1, Entity entity2) {
        super(Events.EventType.PHYSICS_COLLISION);
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    public Entity getEntity1(){
        return this.entity1;
    }

    public Entity getEntity2(){
        return this.entity2;
    }

    public boolean hasProjectile(){
        return this.entity1 instanceof Projectile || this.entity2 instanceof Projectile;
    }

    public boolean hasStage(){
        return this.entity1 instanceof Stage || this.entity2 instanceof Stage;
    }

    public boolean hasPlayer(){
        return this.entity1 instanceof Player || this.entity2 instanceof Player;
    }

    public boolean hasNPC(){
        return this.entity1 instanceof NPC || this.entity2 instanceof NPC;
    }

    public Player getPlayer(){
        assert this.hasPlayer(): "The collision should have a player if you call this";
        if (this.entity1 instanceof Player)return (Player)this.entity1;
        if (this.entity2 instanceof Player)return (Player)this.entity2;
        return null;  // Should not reach this.
    }

    public Stage getStage(){
        assert this.hasStage() : "The collision should have a stage if you call this";
        if (this.entity1 instanceof Stage)return (Stage)this.entity1;
        if (this.entity2 instanceof Stage)return (Stage)this.entity2;
        return null;
    }

    /**
     * Gets the Projectile in the collisison
     * Should only be used when there is one projectile in the collision
     * @return
     */
    public Projectile getProjectile(){
        assert this.hasProjectile() : "The collision shoudl have a projectile if you call this";
        if (this.entity1 instanceof Projectile)return (Projectile)this.entity1;
        if (this.entity2 instanceof Projectile)return (Projectile)this.entity2;
        return null;
    }

    /**
     * gets the NPC in a collision
     * Should only be used when there is one NPC in the collision
     * @return
     */
    public NPC getNPC(){
        assert this.hasProjectile() : "The collision should have a NPC if you call this";
        if (this.entity1 instanceof NPC)return (NPC)this.entity1;
        if (this.entity2 instanceof NPC)return (NPC)this.entity2;
        return null;
    }
}
