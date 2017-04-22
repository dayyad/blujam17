package world.movement;

import java.awt.*;

/**
 * Created by mgoo on 22/04/17.
 */
public interface Collidable {
	Color[][] getCollisionMap();
	void setCollisionMap(Color[][] map);
}
