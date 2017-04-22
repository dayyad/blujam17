package world;

import java.awt.Image;

import world.movement.Collidable;
import world.movement.Collider;

public class Stage implements Animatable, Collidable, Renderable {
	boolean isVisible = false;
	Image sprite;
	
	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public Image getSprite() {
		return null;
	}

	@Override
	public Collider getCollider() {
		return null;
	}

	@Override
	public Animator getAnimator() {
		return null;
	}

}
