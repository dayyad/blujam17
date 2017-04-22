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

	public Player(){

	}
	
	//Collidable
	Collection<Image> frames;

	@Override
	public Animator getAnimator() {
		return null;
	}

	@Override
	public Collection<Image> getFrames() {
		return this.frames;
	}

	@Override
	public Collider getCollider() {
		return null;
	}
}
