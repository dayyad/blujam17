package sound;


import ecs100.Sound;
import events.Event;
import main.Observerable;

import java.io.IOException;

/**
 * Created by mgoo on 22/04/17.
 */
public class SoundManager implements Observerable{
    private Sound background;

    public SoundManager(){
        this.loadFiles();
    }

    private void loadFiles(){
        try {
            this.background = new Sound(new java.io.File("assets/kateye_2.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        switch (event.getType()){
            case PHYSICS_COLLISION:
                break;
            case INITIAL_LOAD:
                this.background.play();
                break;
        }
    }
}
