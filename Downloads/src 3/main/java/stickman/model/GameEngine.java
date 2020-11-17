package stickman.model;

import stickman.levels.Level;
import stickman.memento.levelMemento;

import java.io.IOException;

public interface GameEngine {
    Level getCurrentLevel();

    void loadLevel(int levelNumber);
    void startLevel();

    // Hero inputs - boolean for success (possibly for sound feedback)
    boolean jump();
    boolean moveLeft();
    boolean moveRight();
    boolean stopMoving();

    void tick();

    levelMemento save();
    void load(levelMemento levelMemento) throws IOException;

}
