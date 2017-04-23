package world;

import physics.Move;
import renderer.Renderable;
import world.movement.Collidable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by mgoo on 23/04/17.
 */
public class Projectile extends Entity implements Renderable, Collidable {
    public static double PROJECTILE_SPEED = 20;

    Color[][] collisionMap;

    BufferedImage sprite;
    Entity owner;

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    public Projectile(double x, double y, double rotation, Entity owner){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.owner = owner;
        try {
            sprite = Math.random() > 0.5
                    ? ImageIO.read(new File("assets/Particles/bullet.png"))
                    : ImageIO.read(new File("assets/Particles/bullet_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Entity getOwner(){return this.owner;}
    @Override
    public void setSprite(Image sprite) {

    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    @Override
    public Color[][] getCollisionMap() {
        return this.collisionMap;
    }

    @Override
    public void setCollisionMap(Color[][] map) {
        this.collisionMap = map;
    }

    public Move getMovement(){
        double deltaX = -(PROJECTILE_SPEED * Math.sin(this.getRotation()));
        double deltaY = PROJECTILE_SPEED * Math.cos(this.getRotation());
        return new Move(this, deltaX, deltaY);
    }
}
