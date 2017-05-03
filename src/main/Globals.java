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

    // TODO do not store all the menues here instantiate them as needed
    public static Menu pauseMenu;
    public static Menu mainMenu;
    public static Menu dieMenu;
    public static Menu winMenu;

    // TODO do not store all the input handlers here instantiate them as needed
    public static UserActions gameInputHandler;
    public static UserActions menuInputHandler;

    /// TODO no again
    public static HUD hud;
}
