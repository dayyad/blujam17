package input;

import main.GameState;

import java.awt.event.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class InputHandler implements MouseMotionListener, MouseListener, KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        GameState.inputHandler.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        GameState.inputHandler.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        GameState.inputHandler.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameState.inputHandler.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        GameState.inputHandler.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GameState.inputHandler.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        GameState.inputHandler.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        GameState.inputHandler.mouseEntered(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        GameState.inputHandler.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        GameState.inputHandler.mouseMoved(e);
    }
}
