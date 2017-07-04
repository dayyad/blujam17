package renderer;


import java.awt.Image;

public interface Renderable {

	void setSprite(Image sprite);
	Image getSprite();
	double getX();
	double getY();
	double getRotation();
}
