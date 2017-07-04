package world;

import java.awt.*;
import java.util.List;

/**
 * TODO remove and just use renderable
 */
public interface Animatable {
	
	List<Image> getFrames();
	void setFrames(List<Image> frames);
	Animator getAnimator();
	
	/**
	 * boolean isVisibile();
	 * public Collection<Image> getFrames();
	 * int getCurrentFrame();
	 * 
	 * holds following
	 * @return
	 */
	
}
