package world;

import main.Globals;
import world.loader.Loader;

import java.awt.*;

/**
 * @author Vo
 *
 *         Any object that is represented as a physical object in the game.
 *
 */
public abstract class Entity {
	double x;
	double y;

	// Angle the entity is facing.
	double rotation;

	double movementSpeed;

	// Interface replacement fields
	boolean isNPC;

	public Entity() {
		// Placeholder
	}

	/**
	 * Constructor under construction lol, adding fields as they beccome
	 * relevant
	 * 
	 * @return
	 */
	
	//Spin entity to face coordinate
	public void pointTo(double x, double y){
		double padding = 0;
		double adj = 0;
		double opp = 0;
		
		double deltaX = x - this.x;
		double deltaY = y - this.y;
		
		// Check what quarter of the rotation the

		// Top right facing
		if (deltaX >= 0 && deltaY <= 0) {
			padding = 0;
			adj = deltaY;
			opp = deltaX;
		} else if (deltaX >= 0 && deltaY > 0) {
			// Bot right
			padding = Math.PI/2;
			adj = deltaX;
			opp = deltaY;
		} else if (deltaX < 0 && deltaY > 0){
			//bot left
			padding = Math.PI;
			adj = deltaY;
			opp = deltaX;
		} else if (deltaX < 0 && deltaY < 0){
			//top left
			padding = Math.PI * 3/2;
			adj = deltaX;
			opp = deltaY;
		}

		this.rotation = Math.atan(Math.abs(opp)/Math.abs(adj)) + padding + Math.PI;
	}
	
	public void setRotation(double rotation){
		this.rotation = rotation;
	}

	public Entity(boolean isNPC) {
		this.isNPC = isNPC;
	}

	public boolean isNPC() {
		return this.isNPC;
	}

	public void setMovementSpeed(double speed) {
		this.movementSpeed = speed;
	}
	
	public double getMovementSpeed(){
		return this.movementSpeed;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double getRotation(){
		return this.rotation;
	}

	public void move(double deltaX, double deltaY) {
		this.y += deltaY;
		this.x += deltaX;
	}

	/**
	 * moves entity to given position instead of just adding to its current
	 * position.

	 */
	public void moveTo(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public Projectile shoot(){
		Projectile projectile = new Projectile(this.getX(), this.getY(), this.getRotation(), this);
		projectile.setCollisionMap(Loader.collisionMap.get("bullet"));
		Globals.world.addEntity(projectile);
		return projectile;
	}

}
