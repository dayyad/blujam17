package menu;

import main.Globals;
import main.UserActions;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by mgoo on 22/04/17.
 */
public class InputHandler extends UserActions{
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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Globals.CurrentMenu.getInteractableItems().forEach((item) -> item.onRelease(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Globals.CurrentMenu.getInteractableItems().forEach((item) -> item.onMove(e.getX(), e.getY()));
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
