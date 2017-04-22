package menu.ui_elements;

import menu.ui_elements.Button.State;

import java.awt.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class Toggle extends InteractableItem {
    @Override
    public void onClick(int x, int y) {

    }

    @Override
    public void onPress(int x, int y) {
        if(this.contains(x, y)){
            this.state = State.PRESS;
        }
    }

    @Override
    public void onRelease(int x, int y) {
        if (this.contains(x, y)){
            this.state = State.NORMAL;
            this.dataToggle = !this.dataToggle;

        }
    }

    @Override
    public void onMove(int x, int y) {
        if (this.contains(x, y)) {
            this.state = State.HOVER;
        }
    }

    @Override
    public void render(Graphics g) {
        switch (this.state){
            case NORMAL:
                this.drawNormal(g);
                break;
            case HOVER:
                if (hover == null){
                    this.drawNormal(g);
                } else {
                    g.drawImage(this.hover, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
                }
                break;
            case PRESS:
                if (pressed == null){
                    this.drawNormal(g);
                } else {
                    g.drawImage(this.pressed, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
                }
                break;
        }
    }

    public enum State{
        NORMAL,HOVER,PRESS
    }
    private boolean dataToggle = true;
    private State state = State.NORMAL;
    private Image on, off, hover, pressed;

    public Toggle(int x, int y, int width, int height, Image on, Image off) {
        super(x, y, width, height);
        this.off = off;
        this.on = on;
    }

    public Toggle(int x, int y, int width, int height, Image on, Image off, Image hover, Image pressed) {
        super(x, y, width, height);
        this.off = off;
        this.on = on;
        this.hover = hover;
        this.pressed = pressed;
    }

    public Toggle(int x, int y, int width, int height, Image on, Image off, Image hover, Image pressed, boolean initialState){
        this(x, y, width, height, on, off, hover, pressed);
        this.dataToggle = initialState;
    }

    private void drawNormal(Graphics g){
        if (this.dataToggle) {
            g.drawImage(this.on, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        } else {
            g.drawImage(this.off, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        }
    }
}
