package world;
import java.awt.Image;
import java.util.Collection;

public interface Animatable {
	
	Animator getAnimator();
	Collection<Image> getFrames();
	
	/**
	 * boolean isVisibile();
	 * public Collection<Image> getFrames();
	 * int getCurrentFrame();
	 * 
	 * holds following
	 * @return
	 */
	
}
