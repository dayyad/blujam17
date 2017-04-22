package ai;
import events.Event;
import events.Events;
import main.Observerable;
import main.Subject;
import physics.Physics;
import world.Entity;
import world.World;;

public class AI extends Subject implements Observerable{
	private MovementManager movementManager;

	private final World world;
	private final Physics physics;
	
	public AI (World world, Physics physics){
		this.world = world;
		this.physics = physics;
	}
	
	@Override
	public void update(Event event) {
		switch (event.getType()){
			case TICK:
				for (Entity entity : this.world.getEntitiesWithType("NPC")){
					double[] movement = this.movementManager.getMovement(entity);
					System.out.println("{ " + movement[0] + " , "  + movement[1] + " }");
					this.notifyObservers(Events.newMoveEvent(entity, movement[0], movement[1]));
				}
				break;
			case LOAD:
				this.movementManager = new MovementManager(((World)event.getContext()).getEntitiesWithType("Player").iterator().next());
//				this.movementManager = new MovementManager(null);
				break;
		}
	}

	@Override
	protected void initObservers() {
		this.addObservers(this.physics);
	}
}
