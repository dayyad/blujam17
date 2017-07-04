package world;

import main.GameState;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class NPC extends Entity implements world.movement.Collidable, Animatable {

    // Do move that auto sets facing angle.
    Collection<Image> frames;
    Color[][] collisionMap;
    Animator animator = new Animator();
    boolean infected = false;
    double health = 100;
    String deathSprite;

    public NPC(World world) {
        super(world);
    }

    public String getDeathSprite(){return this.deathSprite;}
    public void setDeathSprite(String deathSprite){this.deathSprite = deathSprite;}

    public void damage(double amt){
        this.health -= amt;
        if (this.health <= 0)this.die();
    }
    @Override
    public void move(double deltaX, double deltaY) {
        if (Math.random() > 0.9) {
            this.world.addEntity(new Footstep(this.world, this.getX() + (Math.random()*20 - 10), this.getY() + (Math.random()*20 -10), this.getRotation()));
        }

        double padding = 0;
        double adj = 0;
        double opp = 0;

        // Check what quarter of the rotation the

        // Top right facing
        if (deltaX >= 0 && deltaY <= 0) {
            padding = 0;
            adj = deltaY;
            opp = deltaX;
        } else if (deltaX >= 0 && deltaY > 0) {
            // Bot right
            padding = Math.PI / 2;
            adj = deltaX;
            opp = deltaY;
        } else if (deltaX < 0 && deltaY > 0) {
            //bot left
            padding = Math.PI;
            adj = deltaY;
            opp = deltaX;
        } else if (deltaX < 0 && deltaY < 0) {
            //top left
            padding = Math.PI * 3 / 2;
            adj = deltaX;
            opp = deltaY;
        }

        this.rotation = Math.atan(Math.abs(opp) / Math.abs(adj)) + padding + Math.PI;
        this.x += deltaX;
        this.y += deltaY;
    }

    public boolean isInfected() {
        return this.infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    @Override
    public void setFrames(List<Image> frames) {
        this.animator.addFrames(frames);
    }

    @Override
    public List<Image> getFrames() {
        return this.animator.getFrames();
    }

    @Override
    public Color[][] getCollisionMap() {
        return this.collisionMap;
    }

    @Override
    public void setCollisionMap(Color[][] map) {
        this.collisionMap = map;
    }

    @Override
    public Animator getAnimator() {
        return this.animator;
    }

    public void die(){
        this.world.addEntity(new StaticSprite(this.world, this.getX(), this.getY(), this.getRotation(), this.deathSprite));
        this.world.removeEntity(this);
    }


}
