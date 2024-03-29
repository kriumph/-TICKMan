package stickman.Entities;

import stickman.levels.Level;
import stickman.levels.LevelImp;
import stickman.strategy.*;

public class Slime extends Entity {

    private static final String imagePath_a = "slime_a.png";
    private static final String imagePath_b = "slime_b.png";
    private String currentPath;
    private final Colour colour;
    private SlimeStrategy slimeStrategy;
    boolean path_a;
    private final int UPDATE_FREQUENCY_INDEX = 16;
    protected int currentFrame = 0;

    public enum Colour {
        BLUE("/slimes/blue/"),
        GREEN("/slimes/green/"),
        PURPLE("/slimes/purple/"),
        RED("/slimes/red/"),
        YELLOW("/slimes/yellow/");

        public final String colour;

        private Colour(String colour) {
            this.colour = colour;
        }
    }

    public Slime (double xPos, double yPos, Slime.Colour colour, SlimeStrategy slimeStrategy) {
        this.xVel = 0.0;
        this.colour = colour;
        this.currentPath = colour.colour + imagePath_a;
        this.path_a = true;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = 53;
        this.height = 40;
        this.layer = Layer.FOREGROUND;
        this.slimeStrategy = slimeStrategy;
    }

    @Override
    public String getImagePath() {
        if (currentFrame > UPDATE_FREQUENCY_INDEX) {
            if (path_a) {
                path_a = false;
                currentPath = colour.colour + imagePath_b;
            } else {
                path_a = true;
                currentPath = colour.colour + imagePath_a;
            }
            currentFrame = 0;
        } else {
            currentFrame += 1;
        }
        return currentPath;
    }

    @Override
    public Entity newInstance() {
        return new Slime(this.getXPos(),this.getYPos(), this.colour, this.slimeStrategy);
    }

    public void think(LevelImp level){
        slimeStrategy.move(this, level);
    }

}
