package world;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Animator {
	int hzElapsed = 0;
	int currentFrame = 0;
	int interval = 30; //In frames
	List<Image> frames = new ArrayList<>();

	/**
	 * Big OG class that deals with animation of all entities
	 */

	public Animator(){
		
	}
	
	public Animator(List<Image> frames) {
		this.frames.addAll(frames); 
	}
	
	public Image nextFrame(){
		hzElapsed ++;
		if(hzElapsed > interval){
			 hzElapsed = 0;
			Image returnImage = frames.get(currentFrame % frames.size());
			currentFrame++;
			
			//Stops nonstop increasing
			if(currentFrame % frames.size() == 0){
				currentFrame = 0;
			}
			return returnImage;
		}
		return frames.get(currentFrame % frames.size());
	}
	
	public void addFrames(List<Image> frames){
		this.frames = frames;
	}
	
	/**
	 * 
	 * @param hz to wait before changing frame.
	 */
	public void setInterval(int hz){
		this.interval = hz;
	}

	public List<Image> getFrames(){
		return this.frames;
	}
}
