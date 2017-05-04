package physics;

import events.*;
import main.Globals;
import world.*;
import world.loader.Loader;
import world.movement.Collidable;

import java.util.ArrayList;
import java.util.List;

public class Physics extends Subject implements Observable {
	private final CollisionManager collisionManager = new CollisionManager();

	private List<Projectile> projectiles = new ArrayList<>();
	private World world;
	
	public Physics() {
		
	}

	@Override
	public void update(Event e) {
		switch (e.getType()) {
			case SHOOT:
				this.projectiles.add(((Entity)e.getContext()).shoot());
				break;
			case PHYSICS_COLLISION:
				CollisionEvent collisionEvent = (CollisionEvent)e;
				if (collisionEvent.hasPlayer()){
					// If the Collision is between a NPC and the player
					if (collisionEvent.hasNPC()){
						// TODO get the damage from the entity and use that instead of arbitrary 2
						collisionEvent.getPlayer().removeHealth(2);
					}
				}
				if (collisionEvent.hasProjectile() && collisionEvent.hasStage()){
					this.projectiles.remove(collisionEvent.getProjectile());
					this.world.removeEntity(collisionEvent.getProjectile());
				}
				break;
			case MOVE:
				this.move((MoveEvent)e);
				break;
			case TICK:
				for (int i = 0; i < this.projectiles.size(); i++){
					if (this.projectiles.get(i).getX() < 0
							|| this.projectiles.get(i).getY() < 0
							|| this.projectiles.get(i).getX() > Globals.mainCanvas.getWidth()
							|| this.projectiles.get(i).getY() > Globals.mainCanvas.getHeight()){
						this.world.removeEntity(this.projectiles.get(i));
						this.projectiles.remove(i);
						i--;
						continue;
					}
					this.notifyObservers(this.projectiles.get(i).getMovement());
				}
				break;
			case LEVEL_LOAD:
				LevelLoadEvent levelLoadEvent = (LevelLoadEvent)e;
				this.world = levelLoadEvent.getWorld();
		}
	}

	private void move(MoveEvent moveEvent) {
		if (this.validateMove(moveEvent)){
			moveEvent.execute();
		}
	}

	/**
	 * Makes sure that the move is valid ie that there in no collisions where the entity is moving to
	 * TODO make the move parameters part of the move event
	 * @param moveEvent The Move object
	 * @return
	 */
	private boolean validateMove(MoveEvent moveEvent) {
		Entity entity = moveEvent.getEntity();
		double newX = entity.getX() + moveEvent.getX();
		double newY = entity.getY() + moveEvent.getY();
		if (!(entity instanceof Collidable))return true;

		// TODO make this cleaner
		for(Entity otherEntity : this.world.getEntitiesWithType("Collidable")){
			if (entity == otherEntity)continue;

			// Checks that an entity can not collide with its owner
			if (entity instanceof Projectile && ((Projectile)entity).getOwner() == otherEntity)continue;
			if (otherEntity instanceof Projectile && ((Projectile) otherEntity).getOwner() == entity) continue;

			CollisionManager.CollisionType collisionType = this.collisionManager.checkCollisionMap(newX, newY, ((Collidable)entity).getCollisionMap(), otherEntity);

			if (collisionType.equals(CollisionManager.CollisionType.NEXT_LEVEL) && entity instanceof Player){
				this.world.setCurrentLevel(Loader.loadLevel( (++this.world.level) + ""));
				this.world.notifyObservers(Events.newLevelLoadEvent(this.world));
			}
			if (collisionType.equals(CollisionManager.CollisionType.WIN_GAME) && entity instanceof Player){
				Globals.CurrentMenu = Globals.winMenu;
				Globals.currentInputHandler = Globals.menuInputHandler;
				Globals.gameState = Globals.GameState.DIE;
				this.world.resetLevels();
			}

			if (!collisionType.equals(CollisionManager.CollisionType.NO_COLLISION)){
				this.notifyObservers(Events.newCollisionEvent(entity, otherEntity));
				return false;
			}
		}
		return true;
	}
}
