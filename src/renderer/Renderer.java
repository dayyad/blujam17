package renderer;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.NoSuchElementException;

import events.Event;
import events.LevelLoadEvent;
import main.Globals;
import events.Observable;
import world.Stage;
import world.World;

public class Renderer implements Observable {
	private World world;
	private BufferedImage offScreen;

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
				LevelLoadEvent levelLoadEvent = (LevelLoadEvent)e;
				this.world = levelLoadEvent.getWorld();
				break;
		}
	}

	private void render() {
		this.offScreen = new BufferedImage(Globals.mainCanvas.getWidth(), Globals.mainCanvas.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		// Render world

		// There is only ever one stage
		try {
			Renderable stage = (Renderable) world.getEntitiesWithType("Stage").iterator().next();
			drawToOffScreen(stage.getSprite(), stage.getX(), stage.getY(), stage.getRotation());
		} catch (NoSuchElementException e){

		}

			for (world.Entity entity : world.getEntitiesWithType("Renderable")) {
				if (entity instanceof Stage)continue;
				drawToOffScreen(((Renderable) entity).getSprite(), entity.getX(), entity.getY(), entity.getRotation());
			}

			for (world.Entity entity : world.getEntitiesWithType("Animatable")) {
				if (entity instanceof Stage)continue;
				drawToOffScreen(((world.Animatable) entity).getAnimator().nextFrame(), entity.getX(), entity.getY(),
						entity.getRotation());
			}
			if (Globals.hud != null) {
				drawToOffScreen(Globals.hud.getSprite(), Globals.hud.getX(), Globals.hud.getY(), 0);
			}
			if (Globals.CurrentMenu != null){
				drawToOffScreen(Globals.CurrentMenu.getSprite(), Globals.CurrentMenu.getX(), Globals.CurrentMenu.getY(), 0);
			}


		// Swapping current graphics to be off screen image.
		Globals.mainCanvas.getGraphics().drawImage(offScreen, 0, 0, null);
		// Making new image offscreen of same size as current screen.
		this.offScreen = new BufferedImage(Globals.mainCanvas.getWidth(), Globals.mainCanvas.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
	}

	private void drawToOffScreen(Image sprite, double x, double y, double rotation) {
		AffineTransform af = new AffineTransform();
		af.translate(x, y);
		af.rotate(rotation, sprite.getWidth(null) / 2, sprite.getHeight(null) / 2);
		this.offScreen.createGraphics().drawImage(sprite, af, null);
	}
}
