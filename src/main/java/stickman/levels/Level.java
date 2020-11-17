package stickman.levels;
import stickman.Entities.Entity;
import stickman.view.BackgroundDrawer;

import java.util.ArrayList;
import java.util.List;

public interface Level {
    List<Entity> getEntities();
    void setEntities(List<Entity> newEntities);
    BackgroundDrawer getBGDrawer();
    double getHeight();
    double getWidth();
    void tick();
    double getFloorHeight();
    double getHeroX();
    double getHeroY();
    void setHeroX(double xPos);
    boolean jump();
    boolean shoot();
    boolean moveLeft();
    boolean moveRight();
    boolean stopMoving();
    boolean levelLost();
    boolean levelWon();
    boolean levelComplete();
    boolean livesAllLost();
    boolean levelTransited();
    void setLevelTransited(boolean boo);
    int getTargetTime();
    void setTargetTime(int time);
    int getTime();
    void setTime(int time);
    int getScore();
    void TimeElapsed();
    void setScore(int score);
    void setScore(int score, int timeLeft);
    void setHero(Entity hero);
    Entity getHero();
}
