package main;

import ai.AI;
import ecs100.UI;
import events.*;
import input.GameInputHandler;
import input.MenuInputHandler;
import input.InputHandler;
import input.UserActions;
import menu.*;
import physics.Physics;
import renderer.HUD;
import renderer.Renderer;
import sound.SoundManager;
import world.Player;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import world.loader.Loader;

public class Main extends Subject {
	private static final long TICK_SPEED = 15;

	private JFrame frame;

	public static void main(String[] args) {
		new Main();
	}

	/**
	 * sets up the frame and the observers
	 */
	public Main(){
		this.addObserver(this.world);
		this.addObserver(this.renderer);

		// Set up the Swing stuff
		this.frame = UI.getFrame();
		this.frame.setVisible(false);
		this.frame.dispose();

		this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.frame.setUndecorated(true);

		GraphicsDevice device = this.frame.getGraphicsConfiguration().getDevice();
		boolean result = device.isFullScreenSupported();
		if (result){
			device.setFullScreenWindow(this.frame);
		}

		this.frame.setVisible(true);
		JComponent canvas = ((JComponent)UI.theUI.canvas);
		UI.setDivider(0);

		// Set up the globals
		Globals.mainCanvas = canvas;
		Globals.mainMenu = new MainMenu(this, this.frame.getWidth(), this.frame.getHeight());
		Globals.winMenu = new WinMenu(this, this.frame.getWidth(), this.frame.getHeight());
		Globals.dieMenu = new DieMenu(this, this.frame.getWidth(), this.frame.getHeight());

		Globals.CurrentMenu = Globals.mainMenu;
		Globals.pauseMenu = new PauseMenu(this, this.frame.getWidth(), this.frame.getHeight());
		Globals.currentInputHandler = Globals.menuInputHandler;

		InputHandler inputWrapper = new InputHandler();

		canvas.addKeyListener(inputWrapper);
		canvas.addMouseListener(inputWrapper);
		canvas.addMouseMotionListener(inputWrapper);

		this.notifyObservers(Events.newInitialLoadEvent());
		this.notifyObservers(Events.newMenuUpdate());
	}

	/**
	 * closes the frame
	 */
	public void quit(){
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	}

	public void loadNewWorld(String worldName){
		World world = new World();
		world.setCurrentLevel(Loader.loadLevel("worldName"));
		Physics physics = new Physics();
		AI ai = new AI();
		SoundManager soundManager = new SoundManager();
		Globals.currentInputHandler = new GameInputHandler();

		world.addObserver(Globals.currentInputHandler);
		world.addObserver(physics);
		world.addObserver(ai);
		world.addObserver(soundManager);
		ai.addObserver(physics);
		physics.addObserver(soundManager);
		physics.addObserver(physics);
		Globals.currentInputHandler.addObserver(soundManager);
		Globals.currentInputHandler.addObserver(physics);

		world.notifyObservers(Events.newLevelLoadEvent(world));
	}

	/**
	 * Starts the game running
	 * sets up the globals ect.
	 */
	public void start(){
		this.loadNewWorld("1");

        Globals.hud = new HUD((Player)this.world.getEntitiesWithType("Player").iterator().next());
        Globals.gameState = Globals.GameState.IN_GAME;
        Globals.currentInputHandler = this.gameInputHandler;
        Globals.CurrentMenu = null;

		TimerTask tickTask = new TimerTask() {
			@Override
			public void run() {
				if (Globals.gameState == Globals.GameState.IN_GAME) {
					Main.this.notifyObservers(Events.newTickEvent());
				}
				if (Globals.gameState == Globals.GameState.DIE){
				    Main.this.notifyObservers(Events.newDieEvent());
				    this.cancel();
                }
			}
		};
		Timer timer = new Timer();
		timer.schedule(tickTask, TICK_SPEED, TICK_SPEED);
	}
}
