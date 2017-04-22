package world;

import java.awt.Color;
import java.awt.Image;
import java.util.Collection;
import java.util.List;

public class NPC extends Entity implements world.movement.Collidable, Animatable {

    // Do move that auto sets facing angle.
    Collection<Image> frames;
    Color[][] collisionMap;
    Animator animator = new Animator();
    boolean infected = false;
    double health;

    @Override
    public void move(double deltaX, double deltaY) {
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


}
