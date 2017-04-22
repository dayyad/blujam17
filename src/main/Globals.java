package main;

import menu.Menu;

import javax.swing.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class Globals {
    public enum GameState{
        IN_MENU, IN_GAME, PAUSED, LOADING
    }

    public static GameState gameState = GameState.IN_MENU;
    public static Menu CurrentMenu;
    public static JPanel mainPanel;
    public static UserActions inputHandler;
}
