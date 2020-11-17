package stickman.strategy;

import stickman.Entities.Slime;
import stickman.levels.LevelImp;

public interface SlimeStrategy {
    void move(Slime slime, LevelImp level);
}
