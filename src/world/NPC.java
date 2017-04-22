package world;

import java.awt.Color;
import java.awt.Image;
import java.util.Collection;

public class NPC extends Entity implements world.movement.Collidable, Animatable {

	// Do move that auto sets facing angle.
	Collection<Image> frames;
	Color[][] collisionMap;

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
		
		this.rotation = Math.atan(opp/adj) + padding;
		this.x += deltaX;
		this.y += deltaY;
	}
	
	@Override
	public void setFrames(Collection<Image> frames){
		this.frames = frames;
	}

	@Override
	public Collection<Image> getFrames() {
		return this.frames;
	}

	@Override
	public Color[][] getCollisionMap() {
		return this.collisionMap;
	}

	@Override
	public void setCollisionMap(Color[][] map) {
		this.collisionMap = map;
	}

	
}
