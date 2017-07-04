package world;

import renderer.Renderable;
import world.loader.Loader;

import java.awt.*;

/**
 * Created by mgoo on 23/04/17.
 */
public class StaticSprite extends Entity implements Renderable {
    private Image sprite;

    public StaticSprite(World world, double x, double y, double rotation, String sprite){
        super(world);
        this.setX(x);
        this.setY(y);
        this.setRotation(rotation);
        this.setSprite(Loader.spriteMap.get(sprite));
    }

    @Override
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    @Override
    public Image getSprite() {
        return this.sprite;
    }
}
