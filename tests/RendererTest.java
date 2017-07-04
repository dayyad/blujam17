import events.Events;
import main.Main;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import world.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by mgoo on 22/04/17.
 */
public class RendererTest {


    @Test
    public void testRenderEntity(){
       /* Main main = new Main();

        Entity entity;
        try {
            entity = new Stage(ImageIO.read(new File("assets/Maps/map_0.jpg")), null);

            NPC npc = new NPC();
            npc.setFrames(new ArrayList<Image>(){
                {
                    this.add(ImageIO.read(new File("assets/Animations/Walk/mob_2_walk_1.png")));
                    this.add(ImageIO.read(new File("assets/Animations/Walk/mob_2_walk_1.png")));
                    this.add(ImageIO.read(new File("assets/mobs/mob_2.png")));
                }
            });
            npc.move(500,500);
            npc.setMovementSpeed(2);
            *//*Player player = new Player(300, 300, null, null);
            player.setFrames(new ArrayList<Image>(){
                {
                    this.add(ImageIO.read(new File("assets/Animations/Walk/human_walk_1.png")));
                    this.add(ImageIO.read(new File("assets/Animations/Walk/human_walk_2.png")));
                    this.add(ImageIO.read(new File("assets/mobs/human.png")));
                }
            });
            main.world.addEntity(player);*//*
            main.world.addEntity(npc);
            main.world.addEntity(entity);
            main.world.notifyObservers(Events.newLoadEvent(main.world));
            main.start();
            while(true){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
