package physics;

import world.Entity;
import world.movement.Collidable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mgoo on 22/04/17.
 */
public class CollisionManager {
    public Map<Color, CollisionType> collisionTypeMap = new HashMap<>();

    public enum CollisionType{
        NEXT_LEVEL, PRE_LEVEL, ENTITY, MAP, WIN_GAME, START_GAME, NO_COLLISION
    }

    public CollisionManager(){
        this.collisionTypeMap.put(new Color(255, 0,48), CollisionType.MAP);
        this.collisionTypeMap.put(new Color(240, 0,0), CollisionType.ENTITY);
        this.collisionTypeMap.put(new Color(0, 255,6), CollisionType.NEXT_LEVEL);
        this.collisionTypeMap.put(new Color(0, 199,3), CollisionType.PRE_LEVEL);
        this.collisionTypeMap.put(new Color(107,255,109), CollisionType.WIN_GAME);
        this.collisionTypeMap.put(new Color(0,53,1), CollisionType.START_GAME);
    }

    boolean checkCollision(double x1, double y1, Color[][] collisionMap, double xl1, double yl1, double xl2, double yl2){
        double grad = Math.abs(yl1 - yl2)/Math.abs(xl1 - xl2);
        for (double x = xl1, y = yl1; x <= xl2; x++,y+=grad){
            if (x-x1 >= 0 && y-y1 >= 0 && x-x1 < collisionMap.length && y-y1 < collisionMap[0].length){
                if (collisionTypeMap.containsKey(collisionMap[(int)(x-x1)][(int)(y-y1)])){
                    // colleuisuioatadjal
                    return true;
                }
            }
        }
        // NO coluisuison
        return false;
    }

    CollisionType checkCollisionMap(double x1, double y1, Color[][] collisionMap1, Entity otherEntity){
        if (!(otherEntity instanceof Collidable))return CollisionType.NO_COLLISION;
        return this.checkCollisionMap(x1, y1, collisionMap1, otherEntity.getX(), otherEntity.getY(), ((Collidable)otherEntity).getCollisionMap());
    }

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

        //System.out.println("entity at { " + x1 + " , " + y1 + " }");
        //System.out.println("entity collision { " + collisionMap1.length + " , " + collisionMap1[0].length + " }");
        //System.out.println("entity collision { " + collisionMap2.length + " , " + collisionMap2[0].length + " }");
        int x1_i_start = x1_i;
        int x2_i_start = x2_i;
        int y1_i_start = y1_i;
        int y2_i_start = y2_i;
        for (x1_i = x1_i_start, x2_i = x2_i_start; x1_i < collisionMap1.length && x2_i < collisionMap2.length; x1_i++, x2_i++){
            for (y1_i = y1_i_start,y2_i = y2_i_start; y1_i < collisionMap1[0].length && y2_i < collisionMap2[0].length; y1_i++, y2_i++){
                //System.out.println("checking { " + x2_i + " , " + y2_i + " } against { " + x1_i + " , " + y1_i + " }");

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

    /*private Color[][] getRotatedCollisionMap(Color[][] collisionMap, double theta){
        new
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                double posX=x-width/2,posY=y-height/2;
                double angle1=(Math.toDegrees(Math.atan(Math.abs(posX/posY))));
                double Hypo=Math.sqrt((posX*posX)+(posY*posY));
                if (posX<0){
                    if (posY<0)angle1-=angle; //yea
                    if (posY>=0)angle1+=angle;
                }
                if (posX>=0){
                    if (posY<0)angle1+=angle;
                    if (posY>=0)angle1-=angle; //yea
                }
                double newposX=(Hypo*Math.sin(Math.toRadians(angle1)));
                double newposY=(Hypo*Math.cos(Math.toRadians(angle1)));
                if (posX<0)newposX*=-1;
                if (posY<0)newposY*=-1;
                try{
                    tempImg[x][y]=permImg[(int) Math.round(width/2+newposX)][(int) Math.round(height/2+newposY)];
                } catch (Exception e){}
            }
        }
        this.draw();
    }*/
}
