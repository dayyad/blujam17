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
        super (screenWidth/2 - 150, screenHeight/2 - 150, 300, 190);
        try {
            Image start = ImageIO.read(new File("assets/Misc/test.png"));
            Image quit = ImageIO.read(new File("assets/Misc/test.png"));

            menu.ui_elements.Button resumeButton = new menu.ui_elements.Button(10, 10, (int)(this.getWidth()) - 20, 80, start, start);
            menu.ui_elements.Button quitButton = new menu.ui_elements.Button(10, 100, (int)(this.getWidth()) - 20, 80, quit, quit);
            resumeButton.setOnClickEvent(() -> {
                Globals.CurrentMenu = null;
                Globals.gameState = Globals.GameState.IN_GAME;
                Globals.inputHandler = Globals.gameInputHandler;
            });
            quitButton.setOnClickEvent(main::quit);
            this.add(quitButton);
            this.add(resumeButton);

            this.setBackground(ImageIO.read(new File("assets/Misc/test.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
