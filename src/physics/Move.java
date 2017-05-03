package physics;

import events.Event;
import events.Events;
import main.Command;
import world.Entity;

/**
 * TODO remove this and just use a moveEvent
 */
public class Move extends Command{
	private final double deltaX, deltaY;

	public Move(Entity entity, double deltaX, double deltaY){
		this.setActor(entity);
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public double getDeltaX(){return this.deltaX;}
	public double getDeltaY(){return this.deltaY;}

	@Override
	public void execute() {
		assert this.getActor() != null : "Calling move on a null entity";
		this.getActor().move(deltaX, deltaY);
	}
}
