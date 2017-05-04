package main;

import input.InputHandler;
import input.UserActions;
import menu.Menu;
import renderer.HUD;
import world.World;

import javax.swing.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class Globals {
    public enum GameState{
        IN_MENU, IN_GAME, PAUSED, LOADING, DIE
    }

    public static GameState gameState = GameState.IN_MENU;
    public static Menu CurrentMenu;
    public static JComponent mainCanvas;
    public static UserActions currentInputHandler;

    public static World world;

    /// TODO do not store here
    public static HUD hud;
}
