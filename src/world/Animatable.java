package world;
import java.awt.Image;
import java.util.Collection;
import java.util.List;

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
