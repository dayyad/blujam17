package input;

import events.*;
import main.GameState;
import menu.Menu;

import java.awt.event.MouseEvent;

/**
 * Created by mgoo on 22/04/17.
 */
public class MenuInputHandler extends UserActions implements Subjectable{

    private Subject subject;
    private Menu parentMenu;

    public MenuInputHandler(Menu parentMenu){
        this.subject = new Subject() {};
        this.parentMenu = parentMenu;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.parentMenu.getInteractableItems().forEach((item) -> item.onPress(e.getX(), e.getY()));
        this.subject.notifyObservers(Events.newMenuUpdate());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.parentMenu.getInteractableItems().forEach((item) -> item.onRelease(e.getX(), e.getY()));
        this.subject.notifyObservers(Events.newMenuUpdate());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.parentMenu.getInteractableItems().forEach((item) -> item.onMove(e.getX(), e.getY()));
        this.subject.notifyObservers(Events.newMenuUpdate());
    }

    @Override
    public void update(Event event) {
        // Does not take any events
    }

    public void addObserver(Observable observer){
        this.subject.addObserver(observer);
    }
}
