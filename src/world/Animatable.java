package renderer;
import java.awt.Image;
import java.util.Collection;

public interface Animatable {
	
	boolean isVisibile();
	public Collection<Image> getFrames();
	int getCurrentFrame();
}
