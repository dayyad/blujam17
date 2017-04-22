package menu;

import ecs100.UI;
import menu.ui_elements.InteractableItem;
import menu.ui_elements.Item;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mgoo on 22/04/17.
 */
public class Menu {
    private int x = 0,y =0, width, height;
    private List<Item> items = new ArrayList<>();
    private List<InteractableItem> interactableItems = new ArrayList<>();
    private Image background;

    public Menu(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}

    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}

    public void setBackground(BufferedImage background){this.background = background;}
    public void add(Item item){
        this.items.add(item);
        if (item instanceof InteractableItem){
            this.interactableItems.add((InteractableItem)item);
        }
    }
    public List<InteractableItem> getInteractableItems(){return this.interactableItems;}

    public void render(){
        UI.drawImage(this.background, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        for (Item item : this.items){
            item.render();
        }
    }
}
