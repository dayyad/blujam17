package world.loader;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ai.*;
import events.*;
import input.*;
import main.*;
import network.*;
import physics.*;
import renderer.*;
import sound.*;
import world.*;

public class Loader {

	/**
	 * Example file layout
	 * type:npc,infected:true,positionX:200,positionY:300,health:100,maxSpeed:10
	 * 
	 * @param number
	 * @return
	 */

	public static Level loadLevel(String number) {
		File entityFile = new File("level_" + number + "_entities.txt");
		try {
			Scanner scanner = new Scanner(entityFile);
			while (scanner.hasNextLine()) {
				
				//All fields that could go into an entity.
				String type;
				boolean infected;
				
				String[] tokens = scanner.nextLine().split(",");
				for(String token : tokens){
					String[] subTokens = token.split(":");
					//Sort into various variable fields.
					switch(subTokens[0]){
					
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Level stagedLevel = new Level();
		return stagedLevel;
	}

	public static Map<String, Image> loadSprites() {
		Map<String, Image> imageMap = new HashMap<>();

		return imageMap;
	}

}
