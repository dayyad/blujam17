import events.Events;
import main.Main;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import renderer.Renderer;
import world.Entity;
import world.Player;
import world.Stage;
import world.World;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


/**
 * Created by mgoo on 22/04/17.
 */
public class RendererTest {


    @Test
    public void testRenderEntity(){
        Main main = new Main();
        World world = new World();
        Renderer renderer = new Renderer(world);
        Entity entity;
        try {
            entity = new Stage(ImageIO.read(new File("assets/Maps/map_0.jpg")));
            world.addRenderableEntity(new Player());
            world.addRenderableEntity(entity);
            while(true){
                renderer.update(Events.newTickEvent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
