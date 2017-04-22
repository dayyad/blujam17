package ai;

import world.Entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mgoo on 22/04/17.
 */
public class MovementManager {
    private static final double AI_VIEW_DISATNCE = 100;

    public enum state{CHASING, WALKING, STOPPED}

    private Map<Entity, state> entityStateMap = new HashMap<>();
    private final Entity playerEntity;

    MovementManager(Entity playerEntity){
        this.playerEntity = playerEntity;
    }

    double[] getMovement(Entity entity){
        this.entityStateMap.computeIfAbsent(entity, (key) -> this.entityStateMap.put(key, state.WALKING));
        this.updateState(entity);
        switch (this.entityStateMap.get(entity)){
            case CHASING:
                return this.getChasingMovement(entity);
            case STOPPED:
                return new double[]{0,0};
            default:
            case WALKING:
                return this.getWalkingMovement(entity);
        }
    }

    private double[] getChasingMovement(Entity entity){
        double xDiff = entity.getX() - this.playerEntity.getX();
        double yDiff = entity.getY() - this.playerEntity.getY();
        double hyp = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        double xMove = (entity.getMovementSpeed()/hyp) * xDiff;
        double yMove = (entity.getMovementSpeed()/hyp) * yDiff;
        System.out.println("{ "+xMove+" , " + yMove+" }");
        return new double[]{xMove, yMove};
    }

    private double[] getWalkingMovement(Entity entity){
        double xMovement = (Math.random() * entity.getMovementSpeed());
        double yMovement = Math.sqrt(Math.pow(entity.getMovementSpeed(), 2) - Math.pow(xMovement, 2));
        //return new double[]{xMovement - (xMovement/2), yMovement - (yMovement/2)};
        return new double[]{1,1};
    }

    private void updateState(Entity entity){
        double xDiff = entity.getX() - this.playerEntity.getX();
        double yDiff = entity.getY() - this.playerEntity.getY();
        double hyp = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        if (hyp < AI_VIEW_DISATNCE)this.entityStateMap.put(entity, state.CHASING);
    }
}
