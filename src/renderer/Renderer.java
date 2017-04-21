package renderer;

import events.Event;
import main.Observerable;
import world.World;


public class Renderer implements Observerable{
	private final World world;
	
	public Renderer(World world){
		this.world = world;
	}
	
	@Override
	public void update(Event e){
		switch (e.getType()){
			case TICK:
				this.render();
			break;
		}
	}
	
	private void render(){
		// Render world
	}
}
