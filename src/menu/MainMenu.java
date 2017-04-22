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
            Image baseImage = ImageIO.read(new File("assets/Misc/start_2.jpg"));
            Image hoveImage = ImageIO.read(new File("assets/Misc/end_2.jpg"));
            Image pressedImage = ImageIO.read(new File("assets/Misc/crystal.png"));
            menu.ui_elements.Button startButton = new menu.ui_elements.Button(10,10,50,50, baseImage, hoveImage, pressedImage);
            startButton.setOnClickEvent(main::start);

            this.add(startButton);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.setBackground(ImageIO.read(new File("assets/Maps/map_1.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        render(Globals.mainCanvas.getGraphics());
    }

}
