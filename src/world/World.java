package world;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import events.Event;
import main.Observerable;
import main.Subject;

public class World extends Subject implements Observerable {
	//private Player player;
	
	private List<Entity> entities = new ArrayList<>();
	
	public World(){
		super();
	}
	
	/**
	 * @return gets entities that implement collidable
	 */
	
	public Collection<Entity> getCollidableEntities(){
		List<Entity> returnList = new ArrayList<>();
		
		for(Entity e : this.entities){
			if(e instanceof physics.Collidable){
				returnList.add(e);
			}
		}
		
		return null;
	}
	
	/**
	 * @return gets entities that implement renderable
	 */
	public Collection<Entity> getRenderableEntities(){
		List<Entity> returnList = new ArrayList<>();
		
		for(Entity e : this.entities){
			if(e.getClass().isInstance(Renderable.Renderable)){
				returnList.add(e);
			}
		}
		
		return null;
	}
	
	/**
	 * @return gets players entity
	 */
	public Entity getPlayerEntity(){
		//return this.player.getEntity();
		return null;
	}
	
	/**
	 * Gets the collision map for the currently loaded map
	 * @return
	 */
	public Color[][] getCollisionMap(){
		return null;
	}
	
	public Collection<Entity> getEntities(){
		return null;
	}
	
	@Override
	public void update(Event event) {
		switch (event.getType()){
		case TICK :
			break;
		}
	}

	@Override
	protected void initObservers() {
		
	}
}
