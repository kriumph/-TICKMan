package stickman.strategy;

import stickman.Entities.Slime;
import stickman.levels.LevelImp;

public class PurpleSlimeStrategy implements SlimeStrategy{
    @Override
    public void move(Slime slime, LevelImp level) {
        /* Always tries to go left side even if no path can go */
        slime.setXVel(-0.3);
    }
}
