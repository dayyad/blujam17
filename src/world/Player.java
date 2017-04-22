package world;

import java.awt.Color;
import java.awt.Image;
import java.util.Collection;

import world.movement.Collidable;
import world.movement.Collider;

public class Player extends Entity implements Collidable, Animatable{
	
	//Animatable
	boolean isVisible;
	int currentFrame;
	Animator animator;
	Collider collider;
	Collection<Image> frames;
	Color[][] collisionMap;

	public Player(double x, double y, Image sprite, Collider collider){
		this.rotation = 0;
		this.x = x;
		this.y = y;
		this.collider = collider;
	}
	
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
