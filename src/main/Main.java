package main;
import ai.AI;
import input.Input;
import physics.Physics;
import renderer.Renderer;
import world.World;

public class Main extends Subject {
	private World world = new World();
	private Physics physics = new Physics(this.world);
	private AI ai = new AI(this.world);
	private Input input = new Input();
	private Renderer renderer = new Renderer(this.world);
	
	public Main(){
		super();
		
	}
	
	
	
	public static void main(String[] args) {
		new Main();
	}



	@Override
	protected void initObservers() {
		this.addObservers(this.physics);
		this.addObservers(this.world);
		this.addObservers(this.ai);
		//this.addObservers(this.renderer);
	}
}
