package menu;

import events.Events;
import main.Globals;
import main.Subject;
import main.UserActions;
import renderer.Renderer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by mgoo on 22/04/17.
 */
public class InputHandler extends UserActions{

    private Subject subject;

    public InputHandler(Renderer renderer){
        this.subject = new Subject() {
            @Override
            protected void initObservers() {
                this.addObservers(renderer);
            }
        };
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Unused
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

    @Override
    public void mouseDragged(MouseEvent e) {
        // Unused
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Unused
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Unused
    }
}
