package physics;

import events.Event;
import events.Events;
import main.Observerable;
import world.Entity;
import world.World;
import world.movement.Collidable;

public class Physics implements Observerable {
	private final World world;
	private final CollisionManager collisionManager = new CollisionManager();

	public Physics(World world) {
		this.world = world;
	}

	@Override
	public void update(Event e) {
		switch (e.getType()) {
		case MOVE:
			this.move((Move) e.getContext());
			break;

		case TICK:

			break;
		}
	}

	private void move(Move move) {
		if (this.validateMove(move)){
			move.execute();
		}
	}

	private boolean validateMove(Move move) {
		Entity entity = move.getEntity();
		double newX = entity.getX() + move.getDeltaX();
		double newY = entity.getY() + move.getDeltaY();
		if (!(move.getEntity() instanceof Collidable))return true;

		for(Entity otherEntity : this.world.getEntities()){
			if (this.collisionManager.checkCollisionMap(newX, newY, ((Collidable)entity).getCollider().getCollisionMap(), otherEntity)){
				this.notify(Events.newCollisionEvent(entity, otherEntity));
				return false;
			}
		}
		return true;
	}
}
