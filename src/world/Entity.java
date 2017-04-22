package world;

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
		//TODO
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
	 * 
	 * @param deltaX
	 * @param deltaY
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
}
