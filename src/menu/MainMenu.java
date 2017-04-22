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
public class MainMenu extends Menu{

    public MainMenu(Main main, int screenWidth, int screenHeight){
        super(0,0, screenWidth, screenHeight);
        try {
            Image testImage = ImageIO.read(new File("assets/Misc/test.png"));

            Image start = ImageIO.read(new File("assets/Menus/start_button.png"));
            Image start_hover = ImageIO.read(new File("assets/Menus/start_button_hover.png"));
            menu.ui_elements.Button startButton = new menu.ui_elements.Button(1275, 800, 400, 75, start , start_hover);
            startButton.setOnClickEvent(main::start);
            this.add(startButton);

            Image quit = ImageIO.read(new File("assets/Menus/quit_button.png"));
            Image quit_hover = ImageIO.read(new File("assets/Menus/quit_button_hover.png"));
            menu.ui_elements.Button quitButton = new menu.ui_elements.Button(1275,925,400,75, quit, quit_hover);
            quitButton.setOnClickEvent(main::quit);
            this.add(quitButton);

            Image volumeOn = ImageIO.read(new File("assets/Misc/volume_on.png"));
            Image volumeOff = ImageIO.read(new File("assets/Misc/volume_off.png"));
            menu.ui_elements.Toggle volumeButton = new menu.ui_elements.Toggle(10, 10, 40, 40, volumeOn, volumeOff);
            this.add(volumeButton);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.setBackground(ImageIO.read(new File("assets/Menus/menu_screen.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
