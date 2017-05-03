package input;

import events.Events;
import main.Globals;
import events.Subject;
import renderer.Renderer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by mgoo on 22/04/17.
 */
public class MenuInputHandler implements UserActions {

    private Subject subject;

    public MenuInputHandler(Renderer renderer){
        this.subject = new Subject() {};
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Globals.CurrentMenu.getInteractableItems().forEach((item) -> item.onPress(e.getX(), e.getY()));
        this.subject.notifyObservers(Events.newMenuUpdate());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Globals.CurrentMenu.getInteractableItems().forEach((item) -> item.onRelease(e.getX(), e.getY()));
        this.subject.notifyObservers(Events.newMenuUpdate());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Globals.CurrentMenu.getInteractableItems().forEach((item) -> item.onMove(e.getX(), e.getY()));
        this.subject.notifyObservers(Events.newMenuUpdate());
    }
}
