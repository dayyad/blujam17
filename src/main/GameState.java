package main;

import ai.AI;
import events.Events;
import events.Subject;
import input.GameInputHandler;
import input.InputHandler;
import input.UserActions;
import menu.DieMenu;
import menu.Menu;
import menu.PauseMenu;
import menu.WinMenu;
import physics.Physics;
import renderer.*;
import renderer.Renderer;
import sound.SoundManager;
import world.Player;
import world.World;
import world.loader.Loader;

import javax.swing.*;
import java.util.Timer;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * Singleton
 * Handles the current game state and the changing of the game state
 * @author Andrew McGhie
 */
public class GameState {
    public static GameState instance(){return GameState.instance;}
    private static GameState instance = new GameState();

    private GameState(){}

    public enum GameState_enum{
        IN_MENU, IN_GAME, PAUSED, LOADING
    }

    private GameState_enum gameState = GameState_enum.IN_MENU;


    public static UserActions inputHandler;

    // Modules
    private Physics physics;
    private AI ai;
    private SoundManager soundManager;
    private GameInputHandler gameInputHandler;
    private World world;
    private renderer.Renderer gameRenderer = new renderer.Renderer();

    private GameLoop gameloop;

    /// TODO do not store here
    public static HUD hud;

    public void startGame() {
        this.resetModules();
        this.world.setCurrentLevel(Loader.loadLevel(this.world, "1"));
        world.notifyObservers(Events.newLevelLoadEvent(this.world));
        GameState.inputHandler = this.gameInputHandler;

        // TODO needs to go
        GameState.hud = new HUD((Player)this.world.getEntitiesWithType("Player").iterator().next());

        this.gameloop = new GameLoop();
        this.gameloop.addObserver(this.world);
        this.gameloop.addObserver(this.gameRenderer);
        this.gameloop.addObserver(gameInputHandler);
        this.gameloop.startLoop();

        this.gameState = GameState_enum.IN_GAME;
    }

    private void resetModules() {
        // TODO add renderer here ie. need a different renderer object for rendering each thing maybe?? would make the currentMenu stuff nicer (partially done)
        this.world = new World();
        this.physics = new Physics();
        this.ai = new AI();
        this.soundManager = new SoundManager();
        this.gameInputHandler = new GameInputHandler();

        world.addObserver(gameRenderer);
        world.addObserver(gameInputHandler);
        world.addObserver(physics);
        world.addObserver(ai);
        world.addObserver(soundManager);
        ai.addObserver(physics);
        physics.addObserver(soundManager);
        physics.addObserver(physics);
        gameInputHandler.addObserver(soundManager);
        gameInputHandler.addObserver(physics);
    }

    /**
     * Stops the Game and returns to menu
     */
    private void stopGame() {
        assert this.gameState == GameState_enum.IN_GAME || this.gameState == GameState_enum.PAUSED : "Cannot stop game if game not running";
        this.gameloop.stopLoop();
        this.gameloop = null;
    }

    /**
     * Aborts and immediately exits
     */
    public void exit() {
        Environment.frame.dispatchEvent(new WindowEvent(Environment.frame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Pauses the game and switches to the pause menu
     */
    public void pause() {
        this.loadMenu(new PauseMenu());
        this.gameState = GameState_enum.PAUSED;
    }

    public void die() {
        this.stopGame();
        this.loadMenu(new DieMenu());
        this.gameState = GameState_enum.IN_MENU;
    }

    public void win() {
        this.stopGame();
        this.loadMenu(new WinMenu());
        this.gameState = GameState_enum.IN_MENU;
    }

    /**
     * Resumes from a paused start
     */
    public void resume() {
        assert this.gameState == GameState_enum.PAUSED : "Cannot resume if not paused";
        GameState.inputHandler = this.gameInputHandler;
        this.gameState = GameState_enum.IN_GAME;
    }

    /**
     * Loads a menu and sets the input handler
     * @param menu
     */
    public void loadMenu(Menu menu){
        GameState.inputHandler = menu.makeInputHandler();
        GameState.inputHandler.addObserver(new Renderer(menu));
        GameState.inputHandler.notifyObservers(Events.newMenuUpdate());
    }

    private class GameLoop extends Subject {
        private static final long TICK_SPEED = 15;
        private final Timer timer = new Timer();

        void startLoop(){
            TimerTask tickTask = new TimerTask() {
                @Override
                public void run() {
                    if (GameState.instance().gameState == GameState.GameState_enum.IN_GAME) {
                        notifyObservers(Events.newTickEvent());
                    }
                }
            };
            this.timer.schedule(tickTask, TICK_SPEED, TICK_SPEED);
        }

        void stopLoop(){
            this.timer.cancel();
        }
    }
}
