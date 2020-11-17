package stickman.Entities;

public class Tree extends Entity {

    private static final String imagePath = "/tree.png";

    public Tree (double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.layer = Layer.FOREGROUND;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Entity newInstance() {
        return new Tree(this.getXPos(),this.getYPos(), this.width, this.height);
    }

}
