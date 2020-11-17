package stickman.strategy;

import stickman.Entities.Entity;
import stickman.Entities.Hero;
import stickman.Entities.Slime;
import stickman.levels.LevelImp;

public class YellowSlimeStrategy implements SlimeStrategy{
    @Override
    public void move(Slime slime, LevelImp level){
        /* Always tries to go towards Hero's xPos */
        for (Entity entity : level.getEntities()) {
            if (entity instanceof Hero) {
                if(slime.getXPos() < entity.getXPos()){
                    slime.setXVel(0.2);
                }
                if(slime.getXPos() >= entity.getXPos()){
                    slime.setXVel(-0.2);
                }
            }
        }
    }
}
