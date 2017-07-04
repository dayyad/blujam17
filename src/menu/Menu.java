package menu;

import input.MenuInputHandler;
import input.UserActions;
import menu.ui_elements.InteractableItem;
import menu.ui_elements.Item;
import renderer.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * The base class that is used to defines differnt menus
 * that can take up differnt portions of the screen
 * TODO possibly make a 2D overlay class and have to HUD here
 * @author Andrew McGhie
 */
public abstract class Menu implements Renderable {
    private int x = 0,y =0, width, height;
    private List<Item> items = new ArrayList<>();
    private List<InteractableItem> interactableItems = new ArrayList<>();
    private BufferedImage background;

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

    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}

    void setBackground(BufferedImage background){
        Image img = background.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        BufferedImage image = new BufferedImage(img.getWidth(null), img.getWidth(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D img_graphics = image.createGraphics();
        img_graphics.drawImage(img, 0, 0, null);
        this.background = image;
    }

    /**
     * Adds an Item to the Menu.
     * @param item
     */
    void add(Item item){
        this.items.add(item);
        item.setParent(this);
    }

    /**
     * {@inheritDoc}
     */
    void add(InteractableItem item) {
        this.add((Item)item);
        this.interactableItems.add(item);
    }

    /**
     * Makes a new input handler to replace the current global input handler
     * TODO Should only be used once per menu
     * @return
     */
    public UserActions makeInputHandler() {
        return new MenuInputHandler(this);
    }

    /**
     * Gets a list of all the items that a user can iteract with
     * @return
     */
    public List<InteractableItem> getInteractableItems(){
        return this.interactableItems;
    }



    @Override
    public Image getSprite(){
        BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        img.createGraphics().drawImage(this.background, 0, 0, null);
        for (Item item : this.items){
            img.createGraphics().drawImage(item.getImage(), item.getX(), item.getY(), item.getWidth(), item.getHeight(), null);
        }
        return img;
    }

    @Override
    public double getRotation(){
        return Math.PI;
    }

    @Override
    public double getX(){
        return this.x;
    }

    @Override
    public double getY(){
        return this.y;
    }

    @Override
    public void setSprite(Image sprite){
        throw new RuntimeException("THIS IS NOT SUPPORTED");
    }
}
