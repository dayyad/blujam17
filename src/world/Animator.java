package world;
import java.awt.Image;
import java.util.*;

public class Animator {
	
	int currentFrame = 0;
	final List<Image> frames = new ArrayList<>();

	/**
	 * Big OG class that deals with animation of all entities
	 */

	public Animator(List<Image> frames) {
		this.frames.addAll(frames); 
	}
	
	public Image nextFrame(){
		Image returnImage = frames.get(currentFrame % frames.size());
		currentFrame++;
		return returnImage;
	}

	public Collection<Image> getFrames(){
		return this.frames;
	}
}
