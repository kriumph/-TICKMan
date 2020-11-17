package stickman.strategy;

import stickman.Entities.Slime;
import stickman.levels.LevelImp;
import java.util.Random;

public class GreenSlimeStrategy implements SlimeStrategy {
    @Override
    public void move(Slime slime, LevelImp level) {
        /* random velocity at anytime */
        if(slime.getXVel() > 0){
            slime.setXVel(new Random().nextDouble());
        }else if(slime.getXVel() <= 0){
            slime.setXVel(new Random().nextDouble() * -1);
        }

    }
}
