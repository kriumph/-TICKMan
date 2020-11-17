package stickman.Entities;

public class GameOverBanner extends Entity {

    //similar structure like YouWinBanner
    private static final String imagePath = "/gameover.png";

    public GameOverBanner(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.layer = Layer.FOREGROUND;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Entity newInstance() {
        return new GameOverBanner(this.getXPos(),this.getYPos());
    }

}
