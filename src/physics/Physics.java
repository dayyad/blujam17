package physics;

import events.Event;
import events.Events;
import main.Globals;
import main.Observerable;
import main.Subject;
import world.*;
import world.loader.Loader;
import world.movement.Collidable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Physics extends Subject implements Observerable {
	private final World world;
	private final CollisionManager collisionManager = new CollisionManager();

	private List<Projectile> projectiles = new ArrayList<>();

	public Physics(World world) {
		this.world = world;
	}

	@Override
	public void update(Event e) {
		switch (e.getType()) {
			case SHOOT:
				this.projectiles.add(((Entity)e.getContext()).shoot());
				break;
			case PHYSICS_BULLET_COLLISION:
			case PHYSICS_BULLET_ENTITY_COLLISION:
			case PHYSICS_BULLET_MAP_COLLISION:
				this.projectiles.remove((Projectile)e.getContext());
				Globals.world.removeEntity((Projectile)e.getContext());
				break;
			case PHYSICS_PLAYER_MOB_COLLISION:
				((Player)((Entity[])e.getContext())[0]).removeHealth(2);
				break;
			case MOVE:
				this.move((Move) e.getContext());
				break;

			case TICK:
				for (int i = 0; i < this.projectiles.size(); i++){
					if (this.projectiles.get(i).getX() < 0
							|| this.projectiles.get(i).getY() < 0
							|| this.projectiles.get(i).getX() > Globals.mainCanvas.getWidth()
							|| this.projectiles.get(i).getY() > Globals.mainCanvas.getHeight()){
						Globals.world.removeEntity(this.projectiles.get(i));
						this.projectiles.remove(i);
						i--;
						continue;
					}
					this.notifyObservers(Events.newMoveEvent(this.projectiles.get(i).getMovement()));
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
			if (move.getActor() == otherEntity)continue;
			if (entity instanceof Projectile && ((Projectile)move.getActor()).getOwner() == otherEntity)continue;
			if (otherEntity instanceof Projectile && ((Projectile) otherEntity).getOwner() == entity) continue;

			CollisionManager.CollisionType collisionType = this.collisionManager.checkCollisionMap(newX, newY, ((Collidable)entity).getCollisionMap(), otherEntity);

			if (collisionType.equals(CollisionManager.CollisionType.NEXT_LEVEL) && entity instanceof Player){
				this.world.setCurrentLevel(Loader.loadLevel( (++this.world.level) + ""));
				this.world.notifyObservers(Events.newLoadEvent(this.world));
			}
			if (collisionType.equals(CollisionManager.CollisionType.WIN_GAME) && entity instanceof Player){
				Globals.CurrentMenu = Globals.winMenu;
				Globals.inputHandler = Globals.menuInputHandler;
				Globals.gameState = Globals.GameState.DIE;
				Globals.world.resetLevels();
			}

			if (!collisionType.equals(CollisionManager.CollisionType.NO_COLLISION)){
				if (entity instanceof Projectile){
					if (otherEntity instanceof Stage){
						this.notifyObservers(Events.newBulletMapCollision((Projectile)entity));
					} else if (otherEntity instanceof NPC){
						this.notifyObservers(Events.newBulletEntityCollision((Projectile)entity));
						((NPC)otherEntity).damage(((Player)((Projectile)entity).getOwner()).getDamage());
					} else {
						this.notifyObservers(Events.newBulletEntityCollision((Projectile)entity));
					}
				}
				if (otherEntity instanceof Projectile) {
					if (entity instanceof Stage) {
						this.notifyObservers(Events.newBulletMapCollision((Projectile) otherEntity));
					} else if (entity instanceof NPC){
						this.notifyObservers(Events.newBulletEntityCollision((Projectile)otherEntity));
						((NPC)entity).damage(((Player)((Projectile)otherEntity).getOwner()).getDamage());
					} else {
						this.notifyObservers(Events.newBulletEntityCollision((Projectile)otherEntity));
					}
				}

				if (entity instanceof Player && otherEntity instanceof NPC) {
					this.notifyObservers(Events.newPlayerMobCollision((Player)entity, (NPC)otherEntity));
				}
				if (entity instanceof NPC && otherEntity instanceof Player){
					this.notifyObservers(Events.newPlayerMobCollision((Player)otherEntity, (NPC)entity));
				}
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
