package world;

import renderer.Renderable;
import world.movement.Collidable;

import java.awt.*;

public class Stage extends Entity implements Collidable, Renderable {
	Image sprite;
	Color[][] collisionMap;
	
	public Stage(World world, Image background, Color[][] collisionMap){
		super(world);;
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
