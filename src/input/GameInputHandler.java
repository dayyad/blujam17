package input;

import events.*;
import main.GameState;
import world.Player;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class GameInputHandler extends UserActions {
	private static final int MOUSE_MOVE = 0;
	private static final int MOUSE_CLICK = 1;

	private enum Action{
		ROTATE, PAUSE, MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT
	}

	private Player player;

	private Map<Integer, Boolean> keyPressedMap = new HashMap<>();
	private Map<Action, Integer> keyBindings = new HashMap<>();
	
	public GameInputHandler(){
		keyBindings.put(Action.MOVE_UP, KeyEvent.VK_W);
		keyBindings.put(Action.MOVE_LEFT, KeyEvent.VK_A);
		keyBindings.put(Action.MOVE_DOWN, KeyEvent.VK_S);
		keyBindings.put(Action.MOVE_RIGHT, KeyEvent.VK_D);

		keyPressedMap.put(KeyEvent.VK_W, false);
		keyPressedMap.put(KeyEvent.VK_A, false);
		keyPressedMap.put(KeyEvent.VK_S, false);
		keyPressedMap.put(KeyEvent.VK_D, false);

		keyBindings.put(Action.PAUSE, KeyEvent.VK_ESCAPE);

		keyBindings.put(Action.ROTATE, MOUSE_MOVE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.keyBindings.values().contains(e.getKeyCode()))return;
		if (this.keyBindings.get(Action.PAUSE).equals(e.getKeyCode())){
			GameState.instance().pause();
			this.notifyObservers(Events.newMenuUpdate());
		} else {
			this.keyPressedMap.put(e.getKeyCode(), true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!this.keyBindings.values().contains(e.getKeyCode()))return;
		this.keyPressedMap.put(e.getKeyCode(), false);
	}

	@Override
	public synchronized void mousePressed(MouseEvent e) {
		this.notifyObservers(Events.newShootEvent(this.player));
	}

	@Override
	public void mouseMoved(MouseEvent e){
		if (this.keyBindings.get(Action.ROTATE) == MOUSE_MOVE){
			this.player.pointTo(e.getX(), e.getY());
		}
	}

	@Override
	public void update(Event event) {
		switch (event.getType()){
			case TICK:
				double deltaX = 0, deltaY = 0;
				if (this.keyPressedMap.get(this.keyBindings.get(Action.MOVE_DOWN))){
					deltaY++;
				}
				if (this.keyPressedMap.get(this.keyBindings.get(Action.MOVE_UP))){
					deltaY--;
				}
				if (this.keyPressedMap.get(this.keyBindings.get(Action.MOVE_LEFT))){
					deltaX--;
				}
				if (this.keyPressedMap.get(this.keyBindings.get(Action.MOVE_RIGHT))){
					deltaX++;
				}

				if (deltaX == 0 && deltaY == 0){
					this.notifyObservers(Events.newStopEvent(this.player));
				} else if (deltaX == 0 ^ deltaY == 0){
					this.notifyObservers(Events.newMoveEvent(this.player, deltaX * this.player.getMovementSpeed(), deltaY * this.player.getMovementSpeed()));
				} else {
					double hyp = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
					deltaX = (this.player.getMovementSpeed() / hyp * deltaX);
					deltaY = (this.player.getMovementSpeed() / hyp * deltaY);
					this.notifyObservers(Events.newMoveEvent(this.player, deltaX, deltaY));
				}
				break;
			case LEVEL_LOAD:
				LevelLoadEvent levelLoadEvent = (LevelLoadEvent)event;
				if(levelLoadEvent.getWorld().getEntitiesWithType("Player").size() == 0)break;
				this.player =  (Player)(levelLoadEvent.getWorld().getEntitiesWithType("Player").iterator().next());
				break;
		}
	}
}
