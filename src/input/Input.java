package input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import events.Event;
import events.Events;
import main.Subject;
import physics.Physics;
import world.World;

public class Input extends Subject{
	//LISTERns
	private final World world;
	private final Physics physics;
	
	public Input(World world, Physics physics){
		super();
		this.world = world;
		this.physics = physics;
	}
	
	public void onKeyDown(KeyEvent e){
		// Handle Key Down Event
	}
	
	public void onKeyUp(KeyEvent e){
		// Handle Key up event
	}
	
	public void onMouseMove(MouseEvent e){
		// Handle Mouse Move event
	}
	
	public void onMouseClick(MouseEvent e){
		// Handle Mouse click event
	}
	
	private void mouseDown(){
		Move move = new Move();
		Event e = Events.newEvent(Events.EventType.MOVE, move);
		this.notify(e);
	}

	@Override
	protected void initObservers() {
		this.addObservers(this.physics);
	}
}
