package stickman.model;
import stickman.Entities.Entity;
import stickman.Entities.Hero;
import stickman.levels.*;
import org.json.simple.JSONObject;
import stickman.memento.levelMemento;

import java.util.ArrayList;
import java.util.HashMap;

public class GameEngineImpl implements GameEngine {
    private Level currentLevel;
    public static int currentLevelNumber, levelCount, lives, residualTime, targetTime, currentScore, prevScore;
    public static boolean loaded = false;
    public static boolean saved = false;
    private LevelDirector levelDirector;
    private JSONObject configuration;
    private HashMap<String, Long> newState;
    private ArrayList<Entity> newEntities= new ArrayList<>();



    public GameEngineImpl(JSONObject configuration) {
        this.configuration = configuration;
        this.levelCount = 3;
        this.currentLevelNumber = 1;
        this.newState = null;
        startLevel();
    }

    @Override
    //change to public in order we can load it by level number in other classes
    public void loadLevel(int levelNumber) {
        JSONObject levels = (JSONObject)configuration.get("levels");

        String key = String.valueOf(levelNumber);
        JSONObject level = (JSONObject)levels.get(key);

        if (level != null) {
            levelDirector = new LevelDirector(new DefaultLevelBuilder(level));
            currentLevel = levelDirector.construct();
        }
    }

    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void startLevel() {
        loadLevel(this.currentLevelNumber);
    }

    @Override
    public boolean jump() {
        return currentLevel.jump();
    }

    @Override
    public boolean moveLeft() {
        currentLevel.moveLeft();
        return false;
    }

    @Override
    public boolean moveRight() {
        currentLevel.moveRight();
        return false;
    }

    @Override
    public boolean stopMoving() {
        currentLevel.stopMoving();
        return false;
    }

    @Override
    public void tick() {
        if (currentLevel.levelLost()) {
            if (DefaultLevelBuilder.lives == 3) {
                DefaultLevelBuilder.lives = 2;
            } else if (DefaultLevelBuilder.lives == 2) {
                DefaultLevelBuilder.lives = 1;
            }
            startLevel();
        }else if (currentLevel.levelComplete()) {
            startLevel();
            currentLevel.setLevelTransited(true);
        }
        currentLevel.tick();

    }

    //save by using memento pattern
    @Override
    public levelMemento save(){

        levelMemento memento = new levelMemento((LevelImp)this.getCurrentLevel());
        System.out.println(memento.getNewState());
        saved = true;
        return memento;
    }

    //load by using memento pattern
    @Override
    public void load(levelMemento levelMemento){

        HashMap<String,Long> savedState = levelMemento.getNewState();

        System.out.println(savedState);

        loaded = true;

        //restore the saved states of lives, time, scores and level number
        currentLevelNumber = savedState.get("levelNumber").intValue();
        lives = savedState.get("lives").intValue();
        residualTime = savedState.get("residualTime").intValue();
        targetTime = savedState.get("targetTime").intValue();
        currentScore = savedState.get("currentScore").intValue();
        prevScore = savedState.get("prevScore").intValue();

        //clone entities
        this.getCurrentLevel().setEntities(new ArrayList<Entity>());
        this.getCurrentLevel().setEntities(levelMemento.getNewEntities());

        //can be used to test the hero's velocity will be strange
        //due to the applyGravity (60 times applied per second to y-Velocity),
        //but I cannot fix it
//        for(Entity entity: newEntities){
//            if(entity.getClass() == Hero.class){
//                System.out.println(entity.getXPos());
//                System.out.println(entity.getYPos());
//                System.out.println(entity.getXVel());
//                System.out.println(entity.getYVel());
//            }
//        }


    }
}