package physics;

import world.Entity;
import world.movement.Collidable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Checks for collisions between entities
 * @author Andrew McGhie
 */
class CollisionManager {
    private Map<Color, CollisionType> collisionTypeMap = new HashMap<>();

    /**
     * The types of collisions that can occour
     */
    public enum CollisionType{
        NEXT_LEVEL, PRE_LEVEL, ENTITY, MAP, WIN_GAME, START_GAME, NO_COLLISION
    }

    /**
     * Sets the initial mapping of colors to types
     */
    CollisionManager(){
        this.collisionTypeMap.put(new Color(255, 0,48), CollisionType.MAP);
        this.collisionTypeMap.put(new Color(240, 0,0), CollisionType.ENTITY);
        this.collisionTypeMap.put(new Color(0, 255,6), CollisionType.NEXT_LEVEL);
        this.collisionTypeMap.put(new Color(0, 199,3), CollisionType.PRE_LEVEL);
        this.collisionTypeMap.put(new Color(107,255,109), CollisionType.WIN_GAME);
        this.collisionTypeMap.put(new Color(0,53,1), CollisionType.START_GAME);
    }

    /**
     * TODO write method to check collision maps of two entites
     * @param Entity1
     * @param Move move amount
     * @param Entity2
     * @return The Collision Type
     */

    /**
     * Checks the collision map against an entity
     * TODO also make private when entity to entity method is written
     * @param x1
     * @param y1
     * @param collisionMap1
     * @param otherEntity
     * @return The Collision Type
     */
    CollisionType checkCollisionMap(double x1, double y1, Color[][] collisionMap1, Entity otherEntity){
        if (!(otherEntity instanceof Collidable))return CollisionType.NO_COLLISION;
        return this.checkCollisionMap(x1, y1, collisionMap1, otherEntity.getX(), otherEntity.getY(), ((Collidable)otherEntity).getCollisionMap());
    }

    /**
     * Compares two collisionMaps and returns the collision type
     * TODO make private when entity to entity method is written
     * @param x1
     * @param y1
     * @param collisionMap1
     * @param x2
     * @param y2
     * @param collisionMap2
     * @return The Collision Type
     */
    CollisionType checkCollisionMap(double x1, double y1, Color[][] collisionMap1, double x2, double y2, Color[][] collisionMap2){
        // Validate the CollisionMaps
        if (collisionMap1 == null || collisionMap2 == null)return CollisionType.NO_COLLISION;
        if (collisionMap1.length == 0 || collisionMap1[0].length == 0 || collisionMap2.length == 0 || collisionMap2[0].length == 0) return CollisionType.NO_COLLISION;;

        // Check that they do overlap
        if (!(x1 + collisionMap1.length > x2 && x1 < x2 + collisionMap2.length
                && y1 + collisionMap1[0].length > y2 && y1 < y2 + collisionMap2[0].length)){
            return CollisionType.NO_COLLISION;
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

        // Checks for overlap.
        int y1_i_start = y1_i;
        int y2_i_start = y2_i;
        for (; x1_i < collisionMap1.length && x2_i < collisionMap2.length; x1_i++, x2_i++){
            for (y1_i = y1_i_start,y2_i = y2_i_start; y1_i < collisionMap1[0].length && y2_i < collisionMap2[0].length; y1_i++, y2_i++){
                // If there is a collision.
                if (collisionTypeMap.containsKey(collisionMap1[x1_i][y1_i]) && collisionTypeMap.containsKey(collisionMap2[x2_i][y2_i])) {
                    if (collisionMap1[x1_i][y1_i].getGreen() > 0){
                        return this.collisionTypeMap.get(collisionMap1[x1_i][y1_i]);
                    } else {
                        return this.collisionTypeMap.get(collisionMap2[x2_i][y2_i]);
                    }
                }
            }
        }
        return CollisionType.NO_COLLISION;
    }
}
