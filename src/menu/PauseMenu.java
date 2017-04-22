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
        super (screenWidth/2 - 100, screenHeight/2 - 100, 200, 200);
        try {
            Image start = ImageIO.read(new File("assets/Menus/start_button.png"));
            Image quit = ImageIO.read(new File("assets/Menus/quit_button.png"));

            menu.ui_elements.Button resumeButton = new menu.ui_elements.Button(this.getX() + 10, this.getY() + 10, this.getWidth() - 20, 30, start, null, null);
            menu.ui_elements.Button quitButton = new menu.ui_elements.Button(this.getX() + 10, this.getY() + 50, this.getWidth() - 20, 30, start, null, null);
            resumeButton.setOnClickEvent(() -> {
                Globals.CurrentMenu = null;
                Globals.gameState = Globals.GameState.IN_GAME;
            });
            quitButton.setOnClickEvent(main::quit);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
