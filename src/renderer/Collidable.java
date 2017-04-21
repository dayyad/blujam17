package renderer;

import world.Entity;

import java.awt.*;

/**
 * Created by mgoo on 22/04/17.
 */
public abstract class Collidable extends Entity {
    protected Color[][] collisionMap;
    public abstract Color[][] getCollisionMap();
}
