package renderer;

import events.Event;
import events.LevelLoadEvent;
import events.Observable;
import main.Environment;
import main.GameState;
import world.Stage;
import world.World;
import menu.Menu;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Renderer implements Observable {
    private World world;
    private BufferedImage offScreen;
    private Optional<Menu> menu = Optional.empty();

    public Renderer(Menu menu) {
        this.menu = Optional.of(menu);
    }

    public Renderer() {

    }

    @Override
    public void update(Event e) {
        switch (e.getType()) {
            case TICK:
                this.render();
                break;
            case MENU_UPDATE:
                this.render();
                break;
            case LEVEL_LOAD:
                LevelLoadEvent levelLoadEvent = (LevelLoadEvent) e;
                this.world = levelLoadEvent.getWorld();
                break;
        }
    }

    private void render() {
        this.offScreen = new BufferedImage(Environment.screenWidth, Environment.screenHeight,
                BufferedImage.TYPE_INT_ARGB);

        if (!menu.isPresent()) {
            // Render world

            // There is only ever one stage
            try {
                Renderable stage = (Renderable) world.getEntitiesWithType("Stage").iterator().next();
                drawToOffScreen(stage.getSprite(), stage.getX(), stage.getY(), stage.getRotation());
            } catch (NoSuchElementException e) {

            }

            for (world.Entity entity : world.getEntitiesWithType("Renderable")) {
                if (entity instanceof Stage) continue;
                drawToOffScreen(((Renderable) entity).getSprite(), entity.getX(), entity.getY(), entity.getRotation());
            }

            for (world.Entity entity : world.getEntitiesWithType("Animatable")) {
                if (entity instanceof Stage) continue;
                drawToOffScreen(((world.Animatable) entity).getAnimator().nextFrame(), entity.getX(), entity.getY(),
                        entity.getRotation());
            }
            if (GameState.hud != null) {
                drawToOffScreen(GameState.hud.getSprite(), GameState.hud.getX(), GameState.hud.getY(), 0);
            }
        }
        menu.ifPresent(menu -> drawToOffScreen(menu.getSprite(), menu.getX(), menu.getY(), 0));


        // Swapping current graphics to be off screen image.
        Environment.canvas.getGraphics().drawImage(offScreen, 0, 0, null);
        // Making new image offscreen of same size as current screen.
        this.offScreen = new BufferedImage(Environment.canvas.getWidth(), Environment.canvas.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
    }

    private void drawToOffScreen(Image sprite, double x, double y, double rotation) {
        AffineTransform af = new AffineTransform();
        af.translate(x, y);
        af.rotate(rotation, sprite.getWidth(null) / 2, sprite.getHeight(null) / 2);
        this.offScreen.createGraphics().drawImage(sprite, af, null);
    }
}
