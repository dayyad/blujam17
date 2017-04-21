package ai;
import events.Event;
import main.Observerable;
import physics.Physics;
import world.World;;

public class AI implements Observerable{
	private final World world;
	private final Physics physics;
	
	public AI (World world, Physics physics){
		this.world = world;
		this.physics = physics;
	}
	
	@Override
	public void update(Event event) {
		
	}
}
