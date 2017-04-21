package renderer;

import java.awt.Graphics;

import world.Entity;

abstract class Renderable {
	boolean isVisible;
	Entity entity;
	
	abstract void render(Graphics g);
}
