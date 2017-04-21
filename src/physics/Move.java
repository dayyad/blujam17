package physics;

import events.Event;
import events.Events;
import main.Command;
import world.Entity;

public class Move extends Command{
	private final double deltaX, deltaY;
	private final Entity entity;
	
	public Move(Entity entity, double deltaX, double deltaY){
		this.entity = entity;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public double getDeltaX(){return this.deltaX;}
	public double getDeltaY(){return this.deltaY;}
	public Entity getEntity(){return this.entity;}

	@Override
	public void execute() {
		this.entity.setX(entity.getX() + this.getDeltaX());
		this.entity.setY(entity.getY() + this.getDeltaY());
	}
}
