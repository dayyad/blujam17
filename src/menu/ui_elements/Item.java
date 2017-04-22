package menu.ui_elements;

import java.awt.*;

/**
 * Created by mgoo on 22/04/17.
 */
public abstract class Item {
    private int x = 0,y =0, width, height;
    private menu.Menu parent;

    public void setParent(menu.Menu parent){this.parent = parent;}

    public Item(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean contains(int x, int y){
        return (x >= this.getX() + this.parent.getX() && x <= this.getX() + this.parent.getX() + this.getWidth()
                && y >= this.getY() + this.parent.getY() && y <= this.getY() + this.parent.getY() + this.getHeight());
    }

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}

    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}

    public abstract Image getImage();
}
