package stickman.strategy;

import stickman.Entities.Slime;
import stickman.levels.LevelImp;

import java.util.Random;

public class BlueSlimeStrategy implements SlimeStrategy{
    @Override
    public void move(Slime slime, LevelImp level) {
        /* Always tries to go right side even if no path can go */
        slime.setXVel(0.3);
        }
}
