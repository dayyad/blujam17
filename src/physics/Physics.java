package physics;

import events.Event;
import events.Events;
import main.Observerable;
import main.Subject;
import world.Entity;
import world.Projectile;
import world.World;
import world.movement.Collidable;

import java.util.ArrayList;
import java.util.Collection;

public class Physics extends Subject implements Observerable {
	private final World world;
	private final CollisionManager collisionManager = new CollisionManager();

	private Collection<Projectile> projectiles = new ArrayList<>();

	public Physics(World world) {
		this.world = world;
	}

	@Override
	public void update(Event e) {
		switch (e.getType()) {
			case SHOOT:
				this.projectiles.add(((Entity)e.getContext()).shoot());
				break;
			case MOVE:
				this.move((Move) e.getContext());
				break;

			case TICK:
				for (Projectile projectile : this.projectiles){
					this.notifyObservers(Events.newMoveEvent(projectile.getMovement()));
				}
				break;
		}
	}

	private void move(Move move) {
		if (this.validateMove(move)){
			move.execute();
		}
	}

	private boolean validateMove(Move move) {
		Entity entity = move.getActor();
		double newX = entity.getX() + move.getDeltaX();
		double newY = entity.getY() + move.getDeltaY();
		if (!(move.getActor() instanceof Collidable))return true;

		for(Entity otherEntity : this.world.getEntitiesWithType("Collidable")){
			if (this.collisionManager.checkCollisionMap(newX, newY, ((Collidable)entity).getCollisionMap(), otherEntity)){
				this.notifyObservers(Events.newCollisionEvent(entity, otherEntity));
				return false;
			}
		}
		return true;
	}

	@Override
	protected void initObservers() {

	}
}
