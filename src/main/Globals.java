package main;

import menu.Menu;
import renderer.HUD;
import world.World;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mgoo on 22/04/17.
 */
public class Globals {
    public enum GameState{
        IN_MENU, IN_GAME, PAUSED, LOADING
    }

    public static GameState gameState = GameState.IN_MENU;
    public static Menu CurrentMenu;
    public static JComponent mainCanvas;
    public static UserActions inputHandler;

    public static World world;

    public static Menu pauseMenu;
    public static Menu mainMenu;

    public static UserActions gameInputHandler;
    public static UserActions menuInputHandler;

    public static HUD hud;
}
