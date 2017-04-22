package world;

import java.awt.Image;
import java.util.Collection;

import world.movement.Collidable;
import world.movement.Collider;

public class Stage extends Entity implements Animatable, Collidable, Renderable {
	boolean isVisible = false;
	Image sprite;

	public Stage(Image sprite){
		this.sprite = sprite;
	}
	
	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public Image getSprite() {
		return this.sprite;
	}

	@Override
	public Collider getCollider() {
		return null;
	}

	@Override
	public Animator getAnimator() {
		return null;
	}

	@Override
	public Collection<Image> getFrames() {
		return null;
	}

}
