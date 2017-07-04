package main;

import ai.AI;
import ecs100.UI;
import events.Events;
import events.Subject;
import input.GameInputHandler;
import input.InputHandler;
import menu.DieMenu;
import menu.MainMenu;
import menu.PauseMenu;
import menu.WinMenu;
import physics.Physics;
import renderer.HUD;
import sound.SoundManager;
import world.Player;
import world.World;
import world.loader.Loader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Subject {

	public static void main(String[] args) {
		new Main();
	}

	/**
	 * sets up the frame and the observers
	 */
	public Main(){
	    // Set up the Swing stuff
		JFrame frame;
		frame = UI.getFrame();
		frame.setVisible(false);
		frame.dispose();

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

		// Set up the globals
        Environment.frame = frame;
        Environment.canvas = canvas;
		Environment.screenWidth = frame.getWidth();
		Environment.screenHeight = frame.getHeight();

		InputHandler inputWrapper = new InputHandler();

		canvas.addKeyListener(inputWrapper);
		canvas.addMouseListener(inputWrapper);
		canvas.addMouseMotionListener(inputWrapper);

		this.notifyObservers(Events.newInitialLoadEvent());
		this.notifyObservers(Events.newMenuUpdate());

		GameState.instance().loadMenu(new MainMenu());
	}
}
