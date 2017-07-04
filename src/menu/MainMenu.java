package menu;

import main.Environment;
import main.GameState;
import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * defines the Main menue
 * @author Andrew McGhie
 */
public class MainMenu extends Menu{

    public MainMenu(){
        super(0,0, Environment.screenWidth, Environment.screenHeight);
        try {
            Image start = ImageIO.read(new File("assets/Menus/start_button.png"));
            Image start_hover = ImageIO.read(new File("assets/Menus/start_button_hover.png"));
            menu.ui_elements.Button startButton = new menu.ui_elements.Button(1275, 800, 400, 75, start , start_hover);
            startButton.setOnClickEvent(() -> GameState.instance().startGame());
            this.add(startButton);

            Image quit = ImageIO.read(new File("assets/Menus/quit_button.png"));
            Image quit_hover = ImageIO.read(new File("assets/Menus/quit_button_hover.png"));
            menu.ui_elements.Button quitButton = new menu.ui_elements.Button(1275,925,400,75, quit, quit_hover);
            quitButton.setOnClickEvent(() -> GameState.instance().exit());
            this.add(quitButton);


            this.setBackground(ImageIO.read(new File("assets/Menus/menu_screen.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
