package main;

import ai.AI;
import ecs100.UI;
import events.*;
import input.Input;
import menu.InputHandler;
import menu.MainMenu;
import menu.PauseMenu;
import physics.Physics;
import renderer.Renderer;
import sound.SoundManager;
import world.NPC;
import world.Player;
import world.Stage;
import world.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import world.loader.Loader;

public class Main extends Subject {
	private static final long TICK_SPEED = 15;

	public World world = new World();
	private Physics physics = new Physics(this.world);
	private AI ai = new AI(this.world, this.physics);
	private Renderer renderer = new Renderer(this.world);
	private SoundManager soundManager = new SoundManager();

	private Input input = new Input();
	private InputHandler menuInput = new InputHandler(this.renderer);

	private UserActions inputWrapper = new UserActions();

	private JFrame frame;

	public Main(){
		super();

		this.addObservers(this.physics);
		this.addObservers(this.world);
		this.addObservers(this.ai);
		this.addObservers(this.renderer);
		this.addObservers(this.input);
		this.addObservers(this.soundManager);

		this.physics.addObservers(this.soundManager);
		this.ai.addObservers(this.physics);
		this.world.addObservers(this.soundManager);
		this.input.addObservers(this.physics);
		this.input.addObservers(this.soundManager);

		this.world.addObservers(this.input);
		this.world.addObservers(this.ai);


		//this.soundManager.update(Events.newLoadEvent());

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

		Globals.mainCanvas = canvas;
		Globals.mainMenu = new MainMenu(this, this.frame.getWidth(), this.frame.getHeight());
		Globals.CurrentMenu = Globals.mainMenu;
		Globals.pauseMenu = new PauseMenu(this, this.frame.getWidth(), this.frame.getHeight());
		Globals.menuInputHandler = this.menuInput;
		Globals.gameInputHandler = this.input;
		Globals.inputHandler = Globals.menuInputHandler;
		canvas.addKeyListener(inputWrapper);
		canvas.addMouseListener(inputWrapper);
		canvas.addMouseMotionListener(inputWrapper);

		this.notifyObservers(Events.newInitialLoadEvent());

	}

	public void quit(){
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	protected void initObservers() {
	}

	public void start(){
		Globals.gameState = Globals.GameState.IN_GAME;
		Globals.inputHandler = this.input;
		Globals.CurrentMenu = null;

		/*Loader.loadLevel(this.world, "1");
		this.world.notifyObservers(Events.newLoadEvent(this.world));*/

		try {
			Stage entity = new Stage(ImageIO.read(new File("assets/Maps/map_0.jpg")), null);

			NPC npc = new NPC();
			npc.setFrames(new ArrayList<Image>() {
				{
					this.add(ImageIO.read(new File("assets/Animations/Walk/mob_2_walk_1.png")));
					this.add(ImageIO.read(new File("assets/Animations/Walk/mob_2_walk_1.png")));
					this.add(ImageIO.read(new File("assets/mobs/mob_2.png")));
				}
			});
			npc.move(500, 500);
			npc.setMovementSpeed(2);
			Player player = new Player(300, 300, null);
			player.setFrames(new ArrayList<Image>() {
				{
					this.add(ImageIO.read(new File("assets/Animations/Walk/human_walk_1.png")));
					this.add(ImageIO.read(new File("assets/Animations/Walk/human_walk_2.png")));
					this.add(ImageIO.read(new File("assets/mobs/human.png")));
				}
			});
			player.setMovementSpeed(3);
			this.world.addEntity(entity);
			this.world.addEntity(npc);
			this.world.addEntity(player);
			this.world.notifyObservers(Events.newLoadEvent(this.world));
		} catch (IOException e) {
			e.printStackTrace();
		}

		TimerTask tickTask = new TimerTask() {
			@Override
			public void run() {
				if (Globals.gameState == Globals.GameState.IN_GAME) {
					Main.this.notifyObservers(Events.newTickEvent());
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(tickTask, TICK_SPEED, TICK_SPEED);
	}
}
