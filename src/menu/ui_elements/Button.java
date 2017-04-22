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
            System.out.println("hovering");
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

    public Button(int x, int y, int width, int height, Image normal){
        super(x, y, width, height);
        this.normal = normal;
        this.hover = normal;
        this.pressed = normal;
    }

    public Button(int x, int y, int width, int height, Image normal, Image hover){
        super(x, y, width, height);
        this.normal = normal;
        this.hover = hover;
    }

    public Button(int x, int y, int width, int height, Image normal, Image hover, Image pressed){
        super(x, y, width, height);
        this.normal = normal;
        this.hover = hover;
        this.pressed = pressed;
    }

    public void setNormalImg(Image normal){this.normal = normal;}
    public void setHoverImg(Image hover){this.hover = hover;}
    public void setPressedImg(Image pressed){this.pressed = pressed;}

    @Override
    public Image getImage() {
        switch(this.state){
            case NORMAL:
                if (this.normal != null){
                    return this.normal;
                }
                break;
            case HOVER:
                if (this.hover == null){
                    if (this.normal != null){
                         return this.normal;
                    }
                } else {
                    return this.hover;
                }
                break;
            case PRESSED:
                if (this.hover == null){
                    if (this.normal != null){
                        return this.normal;
                    }
                } else {
                    return this.pressed;
                }
                break;
        }
        return null;
    }



    public void setOnClickEvent(Click onClickEvent){this.onClickEvent = onClickEvent;}

    public interface Click{
        void click();
    }
}
