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
import world.Renderable;
import world.World;

public class Renderer implements Observerable {
	private final World world;
	private BufferedImage offScreen;
	

	public Renderer(World world) {
		this.offScreen = new BufferedImage(Globals.mainCanvas.getWidth(), Globals.mainCanvas.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		this.world = world;
	}

	@Override
	public void update(Event e) {
		switch (e.getType()) {
		case TICK:
			this.render();
			break;
		}
	}

	private void render() {
		// Render world

		for (world.Entity entity : world.getRenderableEntities()) {
			drawToOffScreen(((Renderable) entity).getSprite(), entity.getX(), entity.getY(), entity.getRotation());
		}

		// Swapping current graphics to be off screen image.
		Globals.mainCanvas.getGraphics().drawImage(offScreen, 0, 0, null);
		// Making new image offscreen of same size as current screen.
		this.offScreen = new BufferedImage(Globals.mainCanvas.getWidth(), Globals.mainCanvas.getHeight(),
				BufferedImage.TYPE_INT_ARGB);

	}

	private void drawToOffScreen(Image sprite, double x, double y, double rotation) {
		// TODO make it draw according to its rotation.

		AffineTransform af = AffineTransform.getRotateInstance(Math.PI);
		BufferedImage bi = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setTransform(af);
		g2d.drawImage(bi, null, 0, 0);
		g2d.dispose();

		// TODO make maybe nicer
		Graphics2D g2 = offScreen.createGraphics();
		g2.drawImage(bi, (int)x, (int) y, null);
	}
}
