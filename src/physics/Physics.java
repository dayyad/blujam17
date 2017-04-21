package physics;

import input.Move;
import events.Event;
import main.Observerable;

public class Physics implements Observerable{
	
	public Physics (){
		
	}
	
	@Override
	public void update(Event e){
		switch (e.getType()){
			case MOVE:
				this.move((Move)e.getContext());
			break;
		}
		
	}
	
	private void move(Move move){
		//Move if valid
	}
	
	private void validateMove(){
		
	}
}
