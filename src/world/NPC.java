package world;

public class NPC extends Entity {

	// Do move that auto sets facing angle.

	@Override
	public void move(double deltaX, double deltaY) {
		double padding = 0;
		double adj = 0;
		double opp = 0;
		
		// Check what quarter of the rotation the

		// Top right facing
		if (deltaX >= 0 && deltaY >= 0) {
			padding = 0;
			adj = deltaY;
			opp = deltaX;
		} else if (deltaX >= 0 && deltaY < 0) {
			// Bot right
			padding = Math.PI/2;
			adj = deltaX;
			opp = deltaY;
		} else if (deltaX <0 && deltaY < 0){
			//bot left
			padding = Math.PI;
			adj = deltaY;
			opp = deltaX;
		} else if (deltaX < 0 && deltaY > 0){
			//top left
			padding = Math.PI * 2;
			adj = deltaX;
			opp = deltaY;
		}
		
		this.heading = Math.atan(opp/adj) + padding;
		this.x += deltaX;
		this.y += deltaY;
	}
}
