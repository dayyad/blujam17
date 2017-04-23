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
    private Sound impact;
    private Sound death;
   // private Sound hit_env;
    private Sound die;

    private boolean playing_footsteps;

    public SoundManager(){
        this.loadFiles();
    }

    private void loadFiles(){
        try {
            this.background = new Sound(new java.io.File("assets/sounds/music.wav"));
            this.footsteps = new Sound(new java.io.File("assets/sounds/footsteps2.wav"));
            this.gunshot = new Sound(new java.io.File("assets/sounds/gunshot.wav"));
            this.impact = new Sound(new java.io.File("assets/sounds/impact.wav"));
            this.death = new Sound(new java.io.File("assets/sounds/death.wav"));
//            this.hit_env = new Sound(new java.io.File("assets/sounds/grunt_hit.wav"));
//            this.die = new Sound(new java.io.File("assets/sounds/grunt_dying.wav"));

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

        this.impact.addLineListener((lineEvent) -> {
            if (lineEvent.getType().equals(LineEvent.Type.STOP)){
                this.impact.stop();
            }
        });

        this.gunshot.addLineListener((lineEvent) -> {
            if (lineEvent.getType().equals(LineEvent.Type.STOP)){
                this.gunshot.stop();
            }
        });
        this.death.addLineListener((lineEvent) -> {
            if (lineEvent.getType().equals(LineEvent.Type.STOP)){
                this.death.stop();
            }
        });



        this.background.setVolume(.3F);
        this.impact.setVolume(0.7F);
        footsteps.setVolume(2);
        footsteps.setGain(10000);
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
                break;
            case STOP:
                if (event.getContext() instanceof Player && this.footsteps.playing()){
                    this.playing_footsteps = false;
                    this.footsteps.stop();
                }
                break;
            case SHOOT:
                this.gunshot.play();
                break;
            case PHYSICS_BULLET_ENTITY_COLLISION:
             //   this.die.play();
                break;
            case PHYSICS_BULLET_MAP_COLLISION:
                this.impact.play();
                break;
            case INITIAL_LOAD:
                this.background.play();
                break;
        }
    }
}
