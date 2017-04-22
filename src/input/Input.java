package input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import events.Event;
import events.Events;
import main.*;
import physics.Move;
import physics.Physics;
import world.Entity;
import world.World;

public class Input extends UserActions implements Observerable{
	private static final int MOUSE_MOVE = 0;
	private static final int MOUSE_CLICK = 1;

	private Subject subject;
	private Map<Integer, Boolean> keyPressedMap = new HashMap<>();
	private Map<Integer, Command> keyBindings = new HashMap<>();
	
	public Input(Physics physics){
		super();
		keyBindings.put(KeyEvent.VK_W, new Move(0, -2.5));
		keyBindings.put(KeyEvent.VK_A, new Move(-2.5, 0));
		keyBindings.put(KeyEvent.VK_S, new Move( 0, 2.5));
		keyBindings.put(KeyEvent.VK_D, new Move( 2.5, 0));

		keyBindings.put(MOUSE_MOVE, new Rotate());

		this.subject = new Subject() {
			@Override
			protected void initObservers() {
				this.addObservers(physics);
			}
		};
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Recived keyTyped");

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.keyBindings.keySet().contains(e.getKeyCode()))return;
		this.keyPressedMap.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!this.keyBindings.keySet().contains(e.getKeyCode()))return;
		this.keyPressedMap.put(e.getKeyCode(), false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e){
		Rotate rotateCommand = (Rotate)this.keyBindings.get(MOUSE_MOVE);
		rotateCommand.setX(e.getX());
		rotateCommand.setY(e.getY());
		rotateCommand.execute();
	}

	@Override
	public void mouseDragged(MouseEvent e){

	}

	@Override
	public void update(Event event) {
		switch (event.getType()){
			case TICK:
				for (Integer key : this.keyPressedMap.keySet()){
					if (this.keyPressedMap.get(key)){
						subject.notifyObservers(Events.newMoveEvent((Move)this.keyBindings.get(key)));
					}
				}
				break;
			case LOAD:
				Entity player = ((World)event.getContext()).getEntitiesWithType("Player").iterator().next();
				this.keyBindings.values().stream().forEach((value) -> {
					value.setActor(player);
				});
				break;
		}
	}
}
