package world;

import java.awt.Color;
import java.awt.Image;
import java.util.Collection;
import java.util.List;

import world.movement.Collidable;
import world.movement.Collider;

public class Player extends Entity implements Collidable, Animatable{
	
	//Animatable
	boolean isVisible;
	int currentFrame;
	Animator animator;
	Collider collider;
	Color[][] collisionMap;

	public Player(double x, double y, Image sprite, Collider collider){
		this.animator = new Animator();
		this.rotation = 0;
		this.x = x;
		this.y = y;
		this.collider = collider;
	}

	@Override
	public Color[][] getCollisionMap() {
		return this.collisionMap;
	}

	@Override
	public void setCollisionMap(Color[][] map) {
		this.collisionMap = map;
	}

	@Override
	public void setFrames(List<Image> frames) {
		this.animator.addFrames(frames);
	}

	@Override
	public Animator getAnimator() {
		return this.animator;
	}

	@Override
	public List<Image> getFrames() {
		return this.animator.getFrames();
	}

}
