package renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import events.Event;
import main.Globals;
import main.Observerable;
import world.World;

public class Renderer implements Observerable {
	private final World world;
	private BufferedImage offScreen;

	public Renderer(World world) {
		this.world = world;
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
		}
	}

	private void render() {
		this.offScreen = new BufferedImage(Globals.mainCanvas.getWidth(), Globals.mainCanvas.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		// Render world

			for (world.Entity entity : world.getEntitiesWithType("Renderable")) {
				drawToOffScreen(((Renderable) entity).getSprite(), entity.getX(), entity.getY(), entity.getRotation());
			}

			for (world.Entity entity : world.getEntitiesWithType("Animatable")) {
				drawToOffScreen(((world.Animatable) entity).getAnimator().nextFrame(), entity.getX(), entity.getY(),
						entity.getRotation());
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
