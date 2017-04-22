package menu.ui_elements;

/**
 * Created by mgoo on 22/04/17.
 */
public abstract class InteractableItem extends Item {
    public InteractableItem(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void onClick(int x, int y);
    public abstract void onPress(int x, int y);
    public abstract void onRelease(int x, int y);
    public abstract void onMove(int x, int y);
}
