package menu.ui_elements;

import ecs100.UI;

import java.awt.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class Button extends InteractableItem {
    @Override
    public void onClick(int x, int y) {
        // Unused
    }

    @Override
    public void onPress(int x, int y) {
        if (this.contains(x, y)){
            this.state = State.PRESSED;
            this.onClickEvent.click();
        }
    }

    @Override
    public void onRelease(int x, int y) {
        if (this.contains(x, y)){
            this.state = State.NORMAL;
        }
    }

    @Override
    public void onMove(int x, int y) {
        if (this.contains(x, y)){
            this.state = State.HOVER;
        } else {
            this.state = State.NORMAL;
        }
    }

    public enum State{
        NORMAL, HOVER, PRESSED
    }

    private Image normal, hover, pressed;
    private State state = State.NORMAL;
    private Click onClickEvent;

    public Button(int x, int y, int width, int height, Image normal, Image hover, Image pressed){
        super(x, y, width, height);
        this.normal = normal;
        this.hover = hover;
        this.pressed = pressed;
    }

    @Override
    public void render(Graphics g) {
        switch(this.state){
            case NORMAL:
                g.drawImage(this.normal, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
                break;
            case HOVER:
                g.drawImage(this.hover, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
                break;
            case PRESSED:
                g.drawImage(this.pressed, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
                break;
        }
    }



    public void setOnClickEvent(Click onClickEvent){this.onClickEvent = onClickEvent;}

    public interface Click{
        void click();
    }
}
