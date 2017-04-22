package main;

import input.Input;
import menu.InputHandler;

import java.awt.event.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class UserActions implements MouseMotionListener, MouseListener, KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        Globals.inputHandler.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Globals.inputHandler.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Globals.inputHandler.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Globals.inputHandler.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Globals.inputHandler.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Globals.inputHandler.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Globals.inputHandler.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Globals.inputHandler.mouseEntered(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Globals.inputHandler.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Globals.inputHandler.mouseMoved(e);
    }
}
