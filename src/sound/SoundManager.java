package sound;


import ecs100.Sound;
import events.Event;
import main.Observerable;
import physics.Move;
import world.Player;

import javax.sound.sampled.LineEvent;
import java.io.IOException;

/**
 * Created by mgoo on 22/04/17.
 */
public class SoundManager implements Observerable{
    private Sound background;
    private Sound footsteps;
    private Sound gunshot;

    private boolean playing_footsteps;

    public SoundManager(){
        this.loadFiles();
    }

    private void loadFiles(){
        try {
            this.background = new Sound(new java.io.File("assets/sounds/kateye_2.wav"));
            this.footsteps = new Sound(new java.io.File("assets/sounds/footsteps2.wav"));
            this.gunshot = new Sound(new java.io.File("assets/sounds/gunshot.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.footsteps.addLineListener((lineEvent) -> {
            if (lineEvent.getType().equals(LineEvent.Type.STOP) && this.playing_footsteps){
                this.footsteps.stop();
                this.footsteps.play();
            }
        });

        this.background.addLineListener((lineEvent) -> {
            if (lineEvent.getType().equals(LineEvent.Type.STOP)){
                this.background.stop();
                this.background.play();
            }
        });

        footsteps.setVolume(1);
        footsteps.setGain(30);
    }

    @Override
    public void update(Event event) {
        switch (event.getType()){
            case MOVE:
                if (((Move)event.getContext()).getActor() instanceof Player){
                    if (!this.footsteps.playing()){
                        this.footsteps.play();
                    }
                    this.playing_footsteps = true;
                }
            case STOP:
                if (event.getContext() instanceof Player && this.footsteps.playing()){
                    this.playing_footsteps = false;
                    this.footsteps.stop();
                }
            case PHYSICS_COLLISION:
                break;
            case INITIAL_LOAD:
                this.background.play();
                break;
        }
    }
}
