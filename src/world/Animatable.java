package world;
import java.awt.Image;
import java.util.Collection;

public interface Animatable {
	
	Collection<Image> getFrames();
	void setFrames(Collection<Image> frames);
	
	/**
	 * boolean isVisibile();
	 * public Collection<Image> getFrames();
	 * int getCurrentFrame();
	 * 
	 * holds following
	 * @return
	 */
	
}
