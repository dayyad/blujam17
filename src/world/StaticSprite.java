package world;

import renderer.Renderable;
import world.loader.Loader;

import java.awt.*;

/**
 * Created by mgoo on 23/04/17.
 */
public class StaticSprite extends Entity implements Renderable {
    private Image sprite;

    public StaticSprite(double x, double y, double rotation, String sprite){
        this.setX(x);
        this.setY(y);
        this.setRotation(rotation);
        this.setSprite(Loader.spriteMap.get(sprite));
    }

    @Override
    public void setSprite(Image sprite) {
        if (sprite == null)System.out.println("Tht esprite was null");
        this.sprite = sprite;
    }

    @Override
    public Image getSprite() {
        return this.sprite;
    }
}
