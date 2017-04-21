package ai;
import events.Event;
import events.Events;
import main.Observerable;
import main.Subject;
import physics.Physics;
import world.Entity;
import world.World;;

public class AI extends Subject implements Observerable{
	private final MovementManager movementManager;

	private final World world;
	private final Physics physics;
	
	public AI (World world, Physics physics){
		this.world = world;
		this.physics = physics;

		this.movementManager = new MovementManager(world.getPlayerEntity());
	}
	
	@Override
	public void update(Event event) {
		switch (event.getType()){
			case TICK:
				for (Entity entity : this.world.getNPCs()){
					double[] movement = this.movementManager.getMovement(entity);
					this.notify(Events.newMoveEvent(entity, movement[0], movement[1]));
				}
				break;
		}
	}

	@Override
	protected void initObservers() {
		this.addObservers(this.physics);
	}
}
