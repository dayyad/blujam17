package ai;
import events.Event;
import main.Observerable;
import world.World;;

public class AI implements Observerable{
	World world;
	
	public AI (World world){
		this.world = world;
	}
	
	@Override
	public void update(Event event) {
		
	}
}
