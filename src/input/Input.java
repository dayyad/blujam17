package input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import events.Event;
import events.Events;
import main.Globals;
import main.Subject;
import main.UserActions;
import physics.Move;
import physics.Physics;
import world.World;

public class Input extends UserActions{
	private Subject subject;
	
	public Input(Physics physics){
		super();
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
		subject.notify(Events.moveEvent(new Move(Globals.player, 10, 10)));
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

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
}
