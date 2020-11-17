package stickman.levels;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import stickman.Entities.*;
import stickman.model.GameEngineImpl;
import stickman.view.BackgroundDrawer;
import stickman.view.GameWindow;

import java.util.ArrayList;
import java.util.List;

public class LevelImp implements Level {

    private int targetTime;
    private int time;
    private List<Entity> entities;
    private Entity hero;
    private BackgroundDrawer bg_drawer;
    private boolean left = false;
    private boolean right = false;
    private final double gravity = 0.1;
    private boolean heroHasGrown = false;
    private boolean levelLost = false;
    private boolean levelWon = false;
    private boolean livesAllLost = false;
    private boolean levelComplete = false;
    private boolean heroCanShoot = false;
    private boolean levelTransited = false;
    public static int totalScore = 0;
    private int jumpCount = 0;
    private int score = 0;

    public LevelImp(List<Entity> entities, Entity hero, BackgroundDrawer bg_drawer, int time) {//changes
        this.entities = entities;
        this.hero = hero;
        this.bg_drawer = bg_drawer;
        this.time = time;
        this.targetTime = time;
    }

    @Override
    public int getTargetTime() {return this.targetTime;}//addition

    @Override
    public int getTime() {return this.time;}//addition

    @Override
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void setTargetTime(int time) {
        this.targetTime = time;
    }

    @Override
    public void setScore(int score){
        this.score = score;
    }

    @Override
    public int getScore() {return this.score;}//addition

    @Override
    public void TimeElapsed() {
        this.time --;
    }

    @Override
    public void setScore(int score, int timeLeft) {
        LevelImp.totalScore += score;
        LevelImp.totalScore += timeLeft;
    }

    @Override
    public void setHero(Entity hero) {
        this.hero = hero;
    }

    @Override
    public Entity getHero() {
        return this.hero;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public void setEntities(List<Entity> newEntities){
        entities = newEntities;
    }

    @Override
    public BackgroundDrawer getBGDrawer() {
        return this.bg_drawer;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    //@Override
    public double getWidth() {
        return 0;
    }

    @Override
    public void tick() {

        if (levelWon || levelLost || levelComplete || livesAllLost) return;

        // Set Hero's direction.
        updateEntitiesPosition();

        // apply gravity to all objects with velocity.
        applyGravity();

        for (Entity ent_a : getMovingEntities()) {
            for (Entity ent_b : entities) {

                // Perform actions based on which Entities intersect each other.
                if (ent_a.intersects(ent_b)) {

                    // Certain Entity interactions have consequences.
                    if (heroIntersectsWithSlime(ent_a, ent_b) ||
                        heroIntersectsWithMushroom(ent_a, ent_b) ||
                        heroHasFallen() ||
                        bulletIntersectsWithSlime(ent_a, ent_b) ||
                        heroIntersectsWithFlag(ent_a, ent_b) ||
                        intersectionRequiresNoAction(ent_a, ent_b))
                        continue;

                    // Calculate the left and right overlap of the Entity's from both directions.
                    // The smallest overlap is assumed to be the direction of travel.
                    boolean from_left;
                    boolean from_top;
                    double x_overlap = 0;
                    double y_overlap = 0;

                    // X-axis overlap.
                    if (ent_a.getXPos() < ent_b.getXPos()) {
                        x_overlap = Math.abs(ent_a.getXPos() + ent_a.getWidth() - ent_b.getXPos());
                        from_left = true;
                    } else {
                        x_overlap = Math.abs(ent_b.getXPos() + ent_b.getWidth() - ent_a.getXPos());
                        from_left = false;
                    }

                    // Y-axis overlap.
                    if (ent_a.getYPos() < ent_b.getYPos()) {
                        y_overlap = Math.abs(ent_a.getYPos() + ent_a.getHeight() - ent_b.getYPos());
                        from_top = true;
                    } else {
                        y_overlap = Math.abs(ent_b.getYPos() + ent_b.getHeight() - ent_a.getYPos());
                        from_top = false;
                    }


                    // Direction of travel is on the x-axis.
                    if (x_overlap < y_overlap) {
                        if (from_left)
                            ent_a.setXPos(ent_b.getXPos() - ent_a.getWidth());

                        // From right
                        else
                            ent_a.setXPos(ent_b.getXPos() + ent_b.getWidth());

                        if (ent_a != hero)
                            ent_a.setXVel(ent_a.getXVel() * -1);

                    // Direction of travel is on the y-axis.
                    } else {
                        if (from_top) {
                            ent_a.setYPos(ent_b.getYPos() - ent_a.getHeight());
                            if (ent_a == hero) jumpCount = 0; // Allow the hero to jump again.
                        }

                        // From bottom
                        else
                            ent_a.setYPos(ent_b.getYPos() + ent_b.getHeight());

                        if (ent_a.getClass() == Bullet.class)
                            ent_a.setYVel(ent_a.getYVel() * -1);
                        else
                            ent_a.setYVel(0);

                    }
                }
            }
        }
    }

    private boolean intersectionRequiresNoAction(Entity a, Entity b) {
        if  ((a.getClass() == b.getClass()) ||
             (a.getClass() == Mushroom.class) ||
             (b.getClass() == Mushroom.class) ||
             (a.getClass() == Ghost.class) ||
             (b.getClass() == Ghost.class) ||
             (a == hero && b.getClass() == Bullet.class) ||
             (b == hero && a.getClass() == Bullet.class) ||
             (a.getClass() == Flag.class) ||
             (b.getClass() == Flag.class))
            return true;
        else
            return false;
    }

    private boolean heroIntersectsWithMushroom(Entity a, Entity b) {
        if ((a.getClass() == Mushroom.class && b == hero) ||
            (b.getClass() == Mushroom.class && a == hero)) {

            this.score += 50;

            if (!heroCanShoot)
                heroCanShoot = true;

            // Delete the mushroom
            if (a.getClass() == Mushroom.class)
                entities.remove(a);
            else
                entities.remove(b);

            return true;
        }
        return false;
    }

    private boolean heroHasFallen() {
        if (hero.getYPos() > 500) {
            for (Entity ent: getMovingEntities ()) {
                ent.setXVel(0);
                ent.setYVel(0);
            }

            entities.remove(hero);
            if(DefaultLevelBuilder.lives == 1){
                LevelImp.totalScore = 0;
                DefaultLevelBuilder.lives = 0;
                Entity gameOver = new GameOverBanner(GameWindow.xViewportOffset + 100, GameWindow.yViewportOffset - 200);//changes
                entities.add(gameOver);
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                        t -> {gameOver.setYPos(gameOver.getYPos() + 2);}));

                timeline.setCycleCount(110);
                timeline.play();
                livesAllLost = true;
            }else{
                levelLost = true;
            }
            return true;
        }
        return false;
    }

    private boolean heroIntersectsWithSlime(Entity a, Entity b) {
        if ((a == hero && b.getClass() == Slime.class) ||
            (a.getClass() == Slime.class && b == hero)) {

            for (Entity ent: getMovingEntities ()) {
                ent.setXVel(0);
                ent.setYVel(0);
            }


            if(DefaultLevelBuilder.lives == 1){
                LevelImp.totalScore = 0;
                DefaultLevelBuilder.lives = 0;
                Entity gameOver = new GameOverBanner(GameWindow.xViewportOffset + 100, GameWindow.yViewportOffset - 200);//changes
                entities.add(gameOver);
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                        t -> {gameOver.setYPos(gameOver.getYPos() + 2);}));

                timeline.setCycleCount(110);
                timeline.play();
                livesAllLost = true;


            }else{

                entities.remove(hero);
                levelLost = true;

            }
            return true;
        }
        return false;
    }

    private boolean bulletIntersectsWithSlime(Entity a, Entity b) {
        if ((a.getClass() == Bullet.class && b.getClass() == Slime.class) ||
            (b.getClass() == Bullet.class && a.getClass() == Slime.class)) {

            Entity ghost;
            if (a.getClass() == Slime.class)
                ghost = new Ghost(a.getXPos(), a.getYPos());
            else
                ghost = new Ghost(b.getXPos(), b.getYPos());

            this.score += 100;
            entities.remove(a);
            entities.remove(b);
            entities.add(ghost);

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                    t -> {ghost.setYPos(ghost.getYPos() - 3);}));

            timeline.setCycleCount(10000);
            timeline.play();
            return true;
        }
        return false;
    }

    private boolean heroIntersectsWithFlag(Entity a, Entity b) {
        if ((a == hero && b.getClass() == Flag.class) ||
            (a.getClass() == Flag.class && b == hero)) {

            for (Entity ent: getMovingEntities()) {
                ent.setXVel(0);
                ent.setYVel(0);
            }
            this.setScore(this.getScore(),this.getTime());
            System.out.println(LevelImp.totalScore);

            if(GameEngineImpl.currentLevelNumber != GameEngineImpl.levelCount){
                //still some level behind current level
                levelComplete = true;
                GameEngineImpl.currentLevelNumber ++ ;//static

            }else if(GameEngineImpl.currentLevelNumber == GameEngineImpl.levelCount){
                //current level is the last level
                Entity youWin = new YouWinBanner(GameWindow.xViewportOffset + 100, GameWindow.yViewportOffset - 200);//changes
                entities.add(youWin);
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                        t -> {youWin.setYPos(youWin.getYPos() + 2);}));

                timeline.setCycleCount(110);
                timeline.play();
                levelWon = true;

            }

            return true;
        }
        return false;
    }

    private List<Entity> getMovingEntities() {
        List<Entity> moving_ents = new ArrayList<>();
        for (Entity ent: entities) {
            if (ent == hero) continue; // put hero in last.
            if (ent.getXVel() != 0 || ent.getYVel() != 0) {
                moving_ents.add(ent);
            }
            if (ent instanceof Slime) {
                moving_ents.add(ent);
            }
        }

        // This is to prevent the hero being pushed into walls etc.
        // The hero is the last to be corrected.
        moving_ents.add(hero);
        return moving_ents;
    }

    private void updateEntitiesPosition() {
        if (left)
            hero.setXPos(hero.getXPos() - hero.getXVel());
        if (right)
            hero.setXPos(hero.getXPos() + hero.getXVel());
        hero.setYPos(hero.getYPos() + hero.getYVel());

        for (Entity ent : getMovingEntities()) {
            if (ent == hero) continue;
            if (ent instanceof Slime){
                ((Slime) ent).think(this);
            }
                ent.setXPos(ent.getXPos() + ent.getXVel());

            // Bullet's are not controlled by gravity, but independently.
            // Need to update the Bullet's y-axis too.
            // Delete the bullet if it has travelled far away.
            if (ent.getClass() == Bullet.class) {
                ent.setYPos(ent.getYPos() + ent.getYVel());
                double x_travel = Math.abs(hero.getXPos() - ent.getXPos());
                double y_travel = Math.abs(hero.getYPos() - ent.getYPos());
                if (x_travel > 1000 || y_travel > 1000)
                    entities.remove(ent);
            }
        }
    }

    private void applyGravity() {
        for (Entity ent : entities) {
            if (ent.getClass() == Bullet.class) continue; // bullets not effected by gravity.
            if (!(ent.getXVel() == 0) || !(ent.getYVel() == 0)) {
                ent.setYVel(ent.getYVel() + gravity);
                ent.setYPos(ent.getYPos() + ent.getYVel());
            }
        }
    }

    @Override
    public double getFloorHeight() {
        return 300;
    }

    @Override
    public double getHeroX() {
        return hero.getXPos();
    }

    @Override
    public double getHeroY() {
        return hero.getYPos();
    }

    @Override
    public void setHeroX(double xPos) {
        hero.setXPos(xPos);
    }

    @Override
    public boolean jump() {
        if (levelWon || levelLost || levelComplete || livesAllLost)
            return false;

        // Can only jump twice before landing again.
        // Jump count is reset to 0 within tick() if the hero intersects
        // another entity from the top.
        if (jumpCount < 2) {
            jumpCount += 1;
            hero.setYVel(-3);
            return true;
        }
        return false;
    }

    @Override
    public boolean shoot() {
        if (levelWon || levelLost || levelComplete || livesAllLost || !heroCanShoot)
            return false;

        Entity bullet = new Bullet(hero.getXPos(), hero.getYPos());
        if (left)
            bullet.setXVel(Math.abs(bullet.getXVel()) * -1);
        entities.add(bullet);
        return true;
    }

    @Override
    public boolean moveLeft() {
        right = false;
        left = true;
        return true;
    }

    @Override
    public boolean moveRight() {
        right = true;
        left = false;
        return true;
    }

    @Override
    public boolean stopMoving() {
        right = false;
        left = false;
        return true;
    }

    @Override
    public boolean levelLost() {
        return levelLost;
    }

    @Override
    public boolean levelWon() {
        return levelWon;
    }

    @Override
    public boolean levelComplete() {return levelComplete;}

    @Override
    public boolean livesAllLost() {return livesAllLost;}

    @Override
    public boolean levelTransited(){return levelTransited;}

    @Override
    public void setLevelTransited(boolean boo){
        levelTransited = boo;
    }
}
