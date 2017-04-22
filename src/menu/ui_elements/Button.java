package menu.ui_elements;

import ecs100.UI;

import java.awt.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class Button extends InteractableItem {
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
    public void render() {
        switch(this.state){
            case NORMAL:
                UI.drawImage(this.normal, this.getX(), this.getY(), this.getWidth(), this.getHeight());
                break;
            case HOVER:
                UI.drawImage(this.hover, this.getX(), this.getY(), this.getWidth(), this.getHeight());
                break;
            case PRESSED:
                UI.drawImage(this.pressed, this.getX(), this.getY(), this.getWidth(), this.getHeight());
                break;
        }
    }

    public void onAction(String action, double x, double y){
        System.out.println("Button recived: " + action);
        switch (action) {
            case "pressed":
                if (this.contains((int)x, (int)y)) {
                    this.state = State.PRESSED;
                    this.onClickEvent.click();
                }
                break;
            case "moved":
                if (this.contains((int)x, (int)y)) {
                    this.state = State.HOVER;
                    System.out.println("Set hover state");
                } else {
                    this.state = State.NORMAL;
                    System.out.println("Set Normal State");
                }
                break;
        }
    }

    public void setOnClickEvent(Click onClickEvent){this.onClickEvent = onClickEvent;}

    public interface Click{
        void click();
    }
}
