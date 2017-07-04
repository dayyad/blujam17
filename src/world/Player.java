package world;

import main.GameState;
import world.movement.Collidable;

import java.awt.*;
import java.util.List;

public class Player extends Entity implements Collidable, Animatable{

	public double getDamage(){return 10;}
	//Animatable
	boolean isVisible;
	int currentFrame;
	Animator animator;
	Color[][] collisionMap;
	double health = 100;

	public void setHealth(double health){this.health = health;}

	public Player(World world, double x, double y){
		super(world);
		this.animator = new Animator();
		this.rotation = 0;
		this.x = x;
		this.y = y;
	}
	public void removeHealth(double h){
		this.health -= h;
		if (health < 0){
			this.die();
			this.health = 0;
		}
	}

	public void addHealth(double h){
		this.health += h;
	}
	public double getHealth(){return this.health;}
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

	@Override
	public Projectile shoot(){
		Projectile projectile = super.shoot();
		/*projectile.setX(this.getX() + this.getCollisionMap().length/2);
		projectile.setY(this.getY() + this.getCollisionMap()[0].length/2);*/
		return projectile;
	}

	public void die(){
		GameState.instance().die();
	}

}
