package ai;

import world.Entity;

/**
 * Created by mgoo on 22/04/17.
 */
public class MovementManager {

    double[] getMovement(Entity entity){
        double xMovement = (Math.random() * entity.getMovementSpeed());
        double yMovement = Math.sqrt(Math.pow(entity.getMovementSpeed(), 2) - Math.pow(xMovement, 2));
        return new double[]{xMovement, yMovement};
    }
}
