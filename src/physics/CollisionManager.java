package physics;

import world.Entity;
import world.movement.Collidable;

import java.awt.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class CollisionManager {
    public static final Color COLLISION_COLOR = new Color(255,0,0);

    public CollisionManager(){

    }

    boolean checkCollisionMap(double x1, double y1, Color[][] collisionMap1, Entity otherEntity){
        if (!(otherEntity instanceof Collidable))return false;
        return this.checkCollisionMap(x1, y1, collisionMap1, otherEntity.getX(), otherEntity.getY(), ((Collidable)otherEntity).getCollisionMap());
    }

    boolean checkCollisionMap(double x1, double y1, Color[][] collisionMap1, double x2, double y2, Color[][] collisionMap2){
        // Validate the CollisionMaps
        if (collisionMap1 == null || collisionMap2 == null)return false;
        if (collisionMap1.length == 0 || collisionMap1[0].length == 0 || collisionMap2.length == 0 || collisionMap2[0].length == 0) return false;

        // Check that they do overlap
        if (!(x1 + collisionMap1.length > x2 && x1 < x2 + collisionMap2.length
                && y1 + collisionMap1[0].length > y2 && y1 < y2 + collisionMap2[0].length)){
            return false;
        }

        // Check for collision
        int xDiff = (int)(x2 - x1);
        int yDiff = (int)(y2 - y1);
        int x1_i,x2_i,y1_i,y2_i;
        if (xDiff > 0) {
            x1_i = xDiff;
            x2_i = 0;
        } else {
            x1_i = 0;
            x2_i = Math.abs(xDiff);
        }
        if (yDiff > 0){
            y1_i = yDiff;
            y2_i = 0;
        } else {
            y1_i = 0;
            y2_i = Math.abs(yDiff);
        }

        for (x1_i = x1_i, x2_i = x2_i; x1_i < collisionMap1.length && x2_i < collisionMap2.length; x1_i++, x2_i++){
            for (y1_i = y1_i,y2_i = y2_i; y1_i < collisionMap1[0].length && y2_i < collisionMap2[0].length; y1_i++, y2_i++){
                if (collisionMap1[x1_i][y1_i].equals(COLLISION_COLOR) && collisionMap2[x2_i][y2_i].equals(COLLISION_COLOR)){
                    return true;
                }
            }
        }
        return false;
    }
}
