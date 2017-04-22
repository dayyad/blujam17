package world.movement;

/**
 * For pathing AIs movements to.
 * @author Vo
 */

public class MovementNode {
	final double x;
	final double y;
	
	//Is an AI here.
	boolean occupied = false;
	
	private static int idCount = 0;
	public final int ID;
	
	public MovementNode(double x, double y){
		this.x = x;
		this.y = y;
		this.ID = idCount++;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public int getID(){
		return this.ID;
	}
	
}
