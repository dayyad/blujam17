package world;

import java.awt.Color;
import java.awt.Image;
import java.util.Collection;

import renderer.Renderable;
import world.movement.Collidable;
import world.movement.Collider;

public class Stage extends Entity implements Collidable, Renderable {
	Image sprite;
	Color[][] collisionMap;
	
	public Stage(Image background, Color[][] collisionMap){
		this.sprite = background;
		this.collisionMap = collisionMap;
	}

	@Override
	public Image getSprite() {
		return this.sprite;
	}
	

	@Override
	public void setSprite(Image sprite) {
		this.sprite = sprite;
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
