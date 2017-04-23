package ai;

import menu.PauseMenu;
import world.Entity;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mgoo on 22/04/17.
 */
public class MovementManager {
    private static final double AI_VIEW_DISATNCE = 300;

    public enum state{CHASING, WALKING, STOPPED}

    private Map<Entity, state> entityStateMap = new HashMap<>();
    private Map<Entity, Point> entityTargetMap = new HashMap<>();
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
        double xDiff =  this.playerEntity.getX() - entity.getX();
        double yDiff = this.playerEntity.getY() - entity.getY();
        double hyp = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        double xMove = (entity.getMovementSpeed()/hyp) * xDiff;
        double yMove = (entity.getMovementSpeed()/hyp) * yDiff;
        return new double[]{xMove, yMove};
    }

    private boolean closeEnough(Point p, Entity e){
        return (e.getY() < p.getY() + 5 && e.getY() > p.getY() - 5
                && e.getX() < p.getX() + 5 && e.getX() > p.getX() - 5);
    }

    private double[] getWalkingMovement(Entity entity){
        if (this.entityTargetMap.get(entity) == null || this.closeEnough(this.entityTargetMap.get(entity), entity) || Math.random() > 0.99){
            double newX = entity.getX() + (Math.random() * 150 - 75);
            double newY = entity.getY() + (Math.random() * 150 - 75);
            this.entityTargetMap.put(entity, new Point((int)newX, (int)newY));
        }
        Point target = this.entityTargetMap.get(entity);
        double deltaX = target.getX() - entity.getX();
        double deltaY = target.getY() - entity.getY();
        double hyp = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        double xMovement = entity.getMovementSpeed()/hyp * deltaX;
        double yMovement = entity.getMovementSpeed()/hyp * deltaY;
        return new double[]{xMovement, yMovement};
    }

    private void updateState(Entity entity){
        double xDiff = entity.getX() - this.playerEntity.getX();
        double yDiff = entity.getY() - this.playerEntity.getY();
        double hyp = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        if (hyp < AI_VIEW_DISATNCE){
            this.entityStateMap.put(entity, state.CHASING);
        } else if (this.entityStateMap.get(entity) == state.CHASING){
            this.entityStateMap.put(entity, state.WALKING);
        }
    }
}
