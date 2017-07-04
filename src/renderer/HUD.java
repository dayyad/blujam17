package renderer;

import main.Environment;
import main.GameState;
import world.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by mgoo on 23/04/17.
 */
public class HUD implements Renderable{

    private static final int HP_BAR_WIDTH = 300, HP_BAR_HEIGHT = 50;

    private Player player;
    private Image[] sprites = new Image[11];
    public HUD(Player player){
        this.player = player;
        try{
            sprites[0] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_0.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[1] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_10.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[2] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_20.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[3] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_30.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[4] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_40.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[5] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_50.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[6] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_60.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[7] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_70.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[8] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_80.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[9] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_90.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
            sprites[10] = ImageIO.read(new File("assets/Hud/Hp/hud_hp_100.png")).getScaledInstance(HP_BAR_WIDTH, HP_BAR_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPlayer(Player player){
        this.player = player;
    }

    @Override
    public void setSprite(Image sprite) {

    }

    @Override
    public Image getSprite() {
        return sprites[(int)(this.player.getHealth() / 10)];
    }

    @Override
    public double getX() {
        return 20;
    }

    @Override
    public double getY() {
        return Environment.canvas.getHeight() - 100;
    }

    @Override
    public double getRotation() {
        return 0;
    }
}
