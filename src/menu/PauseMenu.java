package menu;

import main.Globals;
import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by mgoo on 22/04/17.
 */
public class PauseMenu extends Menu{
    public PauseMenu(Main main, int screenWidth, int screenHeight){
        super (0, 0, screenWidth, screenHeight);
        try {
            Image restart = ImageIO.read(new File("assets/Menus/resume_button.png"));
            Image restart_hover = ImageIO.read(new File("assets/Menus/resume_button_hover.png"));
            Image quit = ImageIO.read(new File("assets/Menus/quit_button.png"));
            Image quit_hover = ImageIO.read(new File("assets/Menus/quit_button_hover.png"));

            menu.ui_elements.Button resumeButton = new menu.ui_elements.Button(screenWidth/2 - 200, 500, 400, 80, restart, restart_hover);
            menu.ui_elements.Button quitButton = new menu.ui_elements.Button(screenWidth/2 - 200, 600, 400, 80, quit, quit_hover);
            resumeButton.setOnClickEvent(() -> {
                Globals.CurrentMenu = null;
                Globals.gameState = Globals.GameState.IN_GAME;
                Globals.inputHandler = Globals.gameInputHandler;
            });
            quitButton.setOnClickEvent(main::quit);
            this.add(quitButton);
            this.add(resumeButton);

            this.setBackground(ImageIO.read(new File("assets/Menus/pause_screen.jpg")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
