package input;

import main.Globals;

import java.awt.event.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class InputHandler implements MouseMotionListener, MouseListener, KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        Globals.currentInputHandler.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Globals.currentInputHandler.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Globals.currentInputHandler.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Globals.currentInputHandler.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Globals.currentInputHandler.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Globals.currentInputHandler.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Globals.currentInputHandler.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Globals.currentInputHandler.mouseEntered(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Globals.currentInputHandler.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Globals.currentInputHandler.mouseMoved(e);
    }
}
