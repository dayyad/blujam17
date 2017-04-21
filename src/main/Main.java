package main;
import ai.AI;
import renderer.Renderer;

public class Main extends Subject {
	

	
	
	public Main(){
		this.addObservers(new AI());
		this.addObservers(new Renderer());
		{
			this.notify(Events.newEvent(events.Event.type.))
		}
	}
	
	
	
	public static void main(String[] args) {
		new Main();
	}
}
