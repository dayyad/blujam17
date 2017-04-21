package physics;

import input.Move;
import events.Event;
import main.Observerable;
import world.World;

public class Physics implements Observerable {
	World world;

	public Physics(World world) {
		this.world = world;
	}

	@Override
	public void update(Event e) {
		switch (e.getType()) {
		case MOVE:
			this.move((Move) e.getContext());
			break;

		case TICK:
			this.world = (World) e.getContext();
			break;
		}
	}

	private void move(Move move) {
		// Move if valid
	}

	private void validateMove() {
		// CHECKING FOR COLLISSION
		if (this.world != null) {
			// perform some checking using world.
		}
	}
}
