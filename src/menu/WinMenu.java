package menu;

import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by mgoo on 23/04/17.
 */
public class WinMenu extends Menu {

    public WinMenu(Main main, int screenWidth, int screenHeight) {
        super(0,0, screenWidth, screenHeight);
        try {
            Image start = ImageIO.read(new File("assets/Menus/start_button.png"));
            Image start_hover = ImageIO.read(new File("assets/Menus/start_button_hover.png"));
            menu.ui_elements.Button startButton = new menu.ui_elements.Button(1275, 500, 400, 75, start , start_hover);
            startButton.setOnClickEvent(main::start);
            this.add(startButton);

            Image quit = ImageIO.read(new File("assets/Menus/quit_button.png"));
            Image quit_hover = ImageIO.read(new File("assets/Menus/quit_button_hover.png"));
            menu.ui_elements.Button quitButton = new menu.ui_elements.Button(1275,625,400,75, quit, quit_hover);
            quitButton.setOnClickEvent(main::quit);
            this.add(quitButton);


            this.setBackground(ImageIO.read(new File("assets/Menus/end_screen.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}