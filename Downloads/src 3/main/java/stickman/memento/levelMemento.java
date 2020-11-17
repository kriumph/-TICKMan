package stickman.memento;
import stickman.Entities.Entity;
import stickman.levels.DefaultLevelBuilder;
import stickman.levels.LevelImp;
import stickman.model.GameEngineImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class levelMemento {
    private ArrayList<Entity> newEntities = new ArrayList<>();
    private HashMap<String, Long> newState = new HashMap<>();

    //constructor of memento
    public levelMemento(LevelImp level){
        //to do a new entities list by clone by using newInstance
        for(Entity entity: level.getEntities()){
            Entity newEntity = entity.newInstance();
            newEntities.add(newEntity);
        }

        //to do a hashmap to store all state of lives, time, scores, level number
        newState.put("levelNumber", (long) GameEngineImpl.currentLevelNumber);
        newState.put("lives", (long) DefaultLevelBuilder.lives);
        newState.put("residualTime", (long) level.getTime());
        newState.put("targetTime", (long) level.getTargetTime());
        newState.put("currentScore", (long) level.getScore());
        newState.put("prevScore", (long) LevelImp.totalScore);

    }

    //getter for new entities list
    public ArrayList<Entity> getNewEntities(){return newEntities;}

    //getter for new state hashmap
    public HashMap<String, Long> getNewState(){return newState;}

}
