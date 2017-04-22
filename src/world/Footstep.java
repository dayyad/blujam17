package world;

import renderer.Renderable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by mgoo on 23/04/17.
 */
public class Footstep extends Entity implements Renderable {
    BufferedImage sprite;

    public Footstep(double x, double y, double rotation){
        this.setX(x);
        this.setY(y);
        this.setRotation(rotation);
        try {
            sprite = Math.random() > 0.5
                    ? ImageIO.read(new File("assets/Particles/Footprint.png"))
                    : ImageIO.read(new File("assets/Particles/Footprint_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSprite(Image sprite) {

    }

    @Override
    public Image getSprite() {

        return this.sprite;
    }
}
