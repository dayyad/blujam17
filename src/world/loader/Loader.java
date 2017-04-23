package world.loader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

import ai.*;
import events.*;
import input.*;
import main.*;
import network.*;
import physics.*;
import renderer.*;
import sound.*;
import world.*;

import javax.imageio.ImageIO;

public class Loader {

    public static Map<String, Image> spriteMap = new HashMap<>();
    public static Map<String, List<Image>> animationMap = new HashMap<>();
    public static Map<String, Color[][]> collisionMap = new HashMap<>();

    /**
     * To add a new type, add the type to the switch case
     * <p>
     * Example file layout
     * type:npc,infected:true,positionX:200,positionY:300,health:100,maxSpeed:10
     *
     * @param number
     * @return
     */

    public static Level loadLevel(String number) {
        File entityFile = new File("assets/leveldata/level_" + number + "_entities.dat");
        Level newLevel = new Level();

        spriteMap = new HashMap<>();
        animationMap = new HashMap<>();
        collisionMap = new HashMap<>();

        Loader.loadAssets(number, spriteMap, animationMap, collisionMap);

        try {
            Scanner scanner = new Scanner(entityFile);
            while (scanner.hasNextLine()) {

                // All fields that could go into an entity.
                String type = "";

                String[] tokens = scanner.nextLine().split(",");

                // Decideds what class its going to try to build dependant on
                // the type
                type = tokens[0].split(":")[1];

                Map<String, String> fieldMap = new HashMap<>();

                // Populates table
                for (String token : tokens) {
                    String[] subToken = token.split(":");
                    fieldMap.put(subToken[0], subToken[1]);
                }

                Entity newEntity = null;

                switch (type) {
                    case "NPC":
                        double x = Double.parseDouble(fieldMap.get("positionX"));
                        double y = Double.parseDouble(fieldMap.get("positionY"));

                        newEntity = new NPC();
                        newEntity.setMovementSpeed(Double.parseDouble(fieldMap.get("MaxSpeed")));
                        newEntity.setX(x);
                        newEntity.setY(y);
                        ((NPC) newEntity).setFrames(animationMap.get(fieldMap.get("animationName")));
                        ((NPC) newEntity).setCollisionMap(collisionMap.get(fieldMap.get("collisionName")));
                        break;
                    case "Stage":
                        String name = fieldMap.get("Name");
                        newEntity = new Stage(spriteMap.get(name), collisionMap.get(name));
                        break;
                    case "Player":
                        x = Double.parseDouble(fieldMap.get("positionX"));
                        y = Double.parseDouble(fieldMap.get("positionY"));
                        newEntity = new Player(x, y);
                        newEntity.setMovementSpeed(Double.parseDouble(fieldMap.get("MaxSpeed")));
                        ((Player) newEntity).setFrames(animationMap.get("Player"));
                        ((Player) newEntity).setCollisionMap(collisionMap.get("Player"));
                        break;
                }
                newLevel.addEntity(newEntity);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

        }

        return newLevel;
    }

    /**
     * Fills in 2 maps with all the animation details.
     *
     * ensure full file path is provided in the asset files
     *  @param map the number representing current level
     * @param spriteMap
     * @param animationMap @return
     * @param collisionMap
     */

    private static void loadAssets(String map, Map<String, Image> spriteMap, Map<String, List<Image>> animationMap, Map<String, Color[][]> collisionMap) {
        File spriteFile = new File("assets/leveldata/level_" + map + "_assets.dat");

        try (Scanner scanner = new Scanner(spriteFile)) {

            //Type:Renderable ClassType:Class, imagename.png
            //Type:Animatable NPC, frame:image1.png,frame:image2.png
            while (scanner.hasNextLine()){
                String[] chunk = scanner.nextLine().split(",");
                String type = chunk[0].split(":")[1];
                String entityType = chunk[1].split(":")[1];

                if(type.equals("Animatable")){
                    List<Image> frames = new ArrayList<>();
                    for(int i = 2; i < chunk.length; i ++){
                        Image img = ImageIO.read(new File(chunk[i].split(":")[1]));
                        frames.add(img);
                    }
                    animationMap.put(entityType, frames);
                } else if (type.equals("Renderable")){
                    Image img = ImageIO.read(new File(chunk[2].split(":")[1]));
                    spriteMap.put(entityType, img);
                } else if (type.equals("Collidable")){
                    Image img;
                    if (chunk.length > 3 && chunk[3].equals("scale")) {
                        img = ImageIO.read(new File(chunk[2].split(":")[1])).getScaledInstance(Globals.mainCanvas.getWidth(), Globals.mainCanvas.getHeight(), Image.SCALE_SMOOTH);
                    } else {
                        img = ImageIO.read(new File(chunk[2].split(":")[1]));
                    }
                    BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    bimg.createGraphics().drawImage(img, 0, 0, null);
                    Color[][] collision = new Color[bimg.getWidth()][bimg.getHeight()];
                    for(int x = 0; x < bimg.getWidth(); x++){
                        for(int y = 0; y < bimg.getHeight(); y++) {
                            int clr = bimg.getRGB(x, y);
                            int red = (clr & 0x00ff0000) >> 16;
                            int green = (clr & 0x0000ff00) >> 8;
                            int blue = clr & 0x000000ff;
                            collision[x][y] = new Color(red, green, blue);
                        }
                    }
                    collisionMap.put(entityType, collision);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
