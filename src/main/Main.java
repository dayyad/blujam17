package main;
import ai.AI;
import ecs100.UI;
import events.Events;
import input.Input;
import menu.InputHandler;
import menu.MainMenu;
import physics.Physics;
import renderer.Renderer;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.peer.ComponentPeer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Subject {
	private static final long TICK_SPEED = 15;


	private World world = new World();
	private Physics physics = new Physics(this.world);
	private AI ai = new AI(this.world, this.physics);
	private Input input = new Input(this.physics);
	private Renderer renderer = new Renderer(this.world);

	private InputHandler menuInput = new InputHandler();
	private UserActions inputWrapper = new UserActions();

	public Main(){
		super();

		JFrame frame = UI.getFrame();
		frame.setVisible(false);
		frame.dispose();

//		frame.removeAll();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
		boolean result = device.isFullScreenSupported();
		if (result){
			device.setFullScreenWindow(frame);
		}

		frame.setVisible(true);
		JComponent canvas = ((JComponent)UI.theUI.canvas);
		UI.setDivider(0);
		System.out.println(canvas.getSize());

		Globals.mainGraphics = (Graphics2D)(canvas.getGraphics());
		Globals.CurrentMenu = new MainMenu(this, frame.getWidth(), frame.getHeight());
		Globals.inputHandler = this.menuInput;
		canvas.addKeyListener(inputWrapper);
		canvas.addMouseListener(inputWrapper);
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	protected void initObservers() {
		this.addObservers(this.physics);
		this.addObservers(this.world);
		this.addObservers(this.ai);
		this.addObservers(this.renderer);
	}

	public void start(){
		Globals.gameState = Globals.GameState.IN_GAME;
		Globals.inputHandler = this.input;

		TimerTask tickTask = new TimerTask() {
			@Override
			public void run() {
				if (Globals.gameState == Globals.GameState.IN_GAME) {
					Main.this.notify(Events.newEvent(Events.EventType.TICK));
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(tickTask, TICK_SPEED, TICK_SPEED);
	}
}
