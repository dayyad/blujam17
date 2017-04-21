package sound;


import ecs100.Sound;
import events.Event;
import main.Observerable;

import java.io.IOException;

/**
 * Created by mgoo on 22/04/17.
 */
public class SoundManager implements Observerable{
    private Sound collision;

    public SoundManager(){
        this.loadFiles();
    }

    private void loadFiles(){
        try {
            this.collision = new Sound(new java.io.File("assets/sound/test.mp4"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        switch (event.getType()){
            case PHYSICS_COLLISION:
                if (this.collision != null)this.collision.play();
                break;
        }
    }
}
