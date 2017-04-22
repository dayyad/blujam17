package world;

import java.awt.Color;
import java.awt.Image;
import java.util.Collection;

import world.movement.Collidable;

public class Player extends Collidable, renderer.Animatable{
	
	//Animatable
	boolean isVisible;
	int currentFrame;
	
	//Collidable
	Collection<Image> frames;
	
	@Override
	public boolean isVisibile() {
		return this.isVisible;
	}
	@Override
	public Collection<Image> getFrames() {
		return this.frames;
	}
	@Override
	public int getCurrentFrame() {
		return this.currentFrame;
	}
	@Override
	public Color[][] getCollisionMap() {
		return this.collisionMap;
	}

}
