package menu;

import ecs100.UI;
import main.Globals;
import menu.ui_elements.InteractableItem;

/**
 * Created by mgoo on 22/04/17.
 */
public class InputHandler {
    public void doMouse(String action, double x, double y){
        for (InteractableItem item : Globals.CurrentMenu.getInteractableItems()){
            item.onAction(action, x, y);
        }
        Globals.CurrentMenu.render();
    }

    public void doKey(String key){
        System.out.println(key);
    }

}
