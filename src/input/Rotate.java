package input;

import main.Command;

/**
 * Created by mgoo on 23/04/17.
 */
public class Rotate extends Command {
    private double x, y;
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    @Override
    public void execute() {
        this.getActor().pointTo(this.x, this.y);
    }
}
