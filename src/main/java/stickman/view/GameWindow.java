package stickman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import stickman.Entities.Entity;
import stickman.levels.DefaultLevelBuilder;
import stickman.levels.Level;
import stickman.levels.LevelImp;
import stickman.memento.CareTaker;
import stickman.model.GameEngine;
import stickman.model.GameEngineImpl;

import java.util.ArrayList;
import java.util.List;

public class GameWindow {
    private final int width;
    private final int height;
    private Scene scene;
    private Pane pane;
    private GameEngine model;
    private List<EntityView> entityViews;
    private Timeline animationTimes, animationOthers, animationLoaded;
    private String targets, seconds, scores, lives, totalScores;
    private int target, second, score;
    private Text livesText, targetText, secondText, scoreText, totalScoreText;

    public static double xViewportOffset = 0.0;
    public static double yViewportOffset = 0.0;
    private static final double X_VIEWPORT_MARGIN = 200;
    private static final double Y_VIEWPORT_MARGIN = 100;

    public GameWindow(GameEngine model, int width, int height) {
        this.model = model;
        this.pane = new Pane();
        this.width = width;
        this.height = height;
        this.scene = new Scene(pane, width, height);
        this.entityViews = new ArrayList<>();

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler((GameEngineImpl) model);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

        model.getCurrentLevel().getBGDrawer().draw(model, pane);

        //update time once per second
        animationTimes = new Timeline(new KeyFrame(Duration.millis(1000), e -> updateTime()));
        animationTimes.setCycleCount(Timeline.INDEFINITE);
        animationTimes.play();
        //update others once per 10 milliseconds
        animationOthers = new Timeline(new KeyFrame(Duration.millis(10), e -> updateOthers()));
        animationOthers.setCycleCount(Timeline.INDEFINITE);
        animationOthers.play();
        //check loaded once per 10 milliseconds
        animationLoaded = new Timeline(new KeyFrame(Duration.millis(10), e -> loadLevel()));
        animationLoaded.setCycleCount(Timeline.INDEFINITE);
        animationLoaded.play();

        //initialize lives text
        livesText = new Text("Lives: " + DefaultLevelBuilder.lives);
        livesText.setX(this.width * 1 / 50);
        livesText.setY(this.height * 0.60 / 10);
        livesText.setFill(Color.BLACK);
        livesText.setViewOrder(1);

        //initialize residual time text
        second = model.getCurrentLevel().getTime();
        secondText = new Text("Residual Time: " + second);
        secondText.setX(this.width * 1 / 50);
        secondText.setY(this.height * 1 / 10);
        secondText.setFill(Color.BLACK);
        secondText.setViewOrder(1);

        //initialize target time text
        target = model.getCurrentLevel().getTime();
        targetText = new Text("Target Time: " + second);
        targetText.setX(this.width * 1 / 50);
        targetText.setY(this.height * 1.4 / 10);
        targetText.setFill(Color.BLACK);
        targetText.setViewOrder(1);

        //initialize current scores text
        score = model.getCurrentLevel().getScore();
        scoreText = new Text("Current Score: " + second);
        scoreText.setX(this.width * 1 / 50);
        scoreText.setY(this.height * 1.8 / 10);
        scoreText.setFill(Color.BLACK);
        scoreText.setViewOrder(1);

        //initialize total scores text
        totalScoreText = new Text("Previous Score: " + 0);
        totalScoreText.setX(this.width * 1 / 50);
        totalScoreText.setY(this.height * 2.2 / 10);
        totalScoreText.setFill(Color.BLACK);
        totalScoreText.setViewOrder(1);

        pane.getChildren().add(livesText);
        pane.getChildren().add(secondText);
        pane.getChildren().add(targetText);
        pane.getChildren().add(scoreText);
        pane.getChildren().add(totalScoreText);
    }

    //loadInfo for saved state
    public void loadLevel(){
        if(GameEngineImpl.loaded == true){
            targets = "Target Time: " + GameEngineImpl.targetTime;
            targetText.setText(targets);
            seconds = "Residual Time: " + GameEngineImpl.residualTime;
            secondText.setText(seconds);
            lives = "Lives: " + GameEngineImpl.lives;
            livesText.setText(lives);
            scores = "Current Score: " + GameEngineImpl.currentScore;
            scoreText.setText(scores);
            totalScores = "Previous Score: " + GameEngineImpl.prevScore;
            totalScoreText.setText(totalScores);
            DefaultLevelBuilder.lives = GameEngineImpl.lives;
            LevelImp.totalScore = GameEngineImpl.prevScore;
            model.getCurrentLevel().setScore(GameEngineImpl.currentScore);
            model.getCurrentLevel().setTime(GameEngineImpl.residualTime);
            model.getCurrentLevel().setTargetTime(GameEngineImpl.targetTime);
            GameEngineImpl.loaded = false;
        }
    }

    //update target time and residual time once per second
    public void updateTime(){

        //update target time
        if(model.getCurrentLevel().levelTransited()){
            target = model.getCurrentLevel().getTime();
            model.getCurrentLevel().setLevelTransited(false);
        }
        targets = "Target Time: " + target;
        targetText.setText(targets);

        //update residual time
        if(model.getCurrentLevel().levelWon() || model.getCurrentLevel().livesAllLost()){
            //do nothing here
        }else{
            model.getCurrentLevel().TimeElapsed();
            second = model.getCurrentLevel().getTime();
        }
        seconds = "Residual Time: " + second;
        secondText.setText(seconds);
    }

    //update lives, current scores, previous scores once per 10 milliseconds
    public void updateOthers(){

        //update lives
        livesText.setText("Lives: " + DefaultLevelBuilder.lives);

        //update current scores
        score = model.getCurrentLevel().getScore();
        scores = "Current Score: " + score;
        scoreText.setText(scores);

        //update previous scores
        totalScoreText.setText("Previous Score: " + LevelImp.totalScore);

        if(model.getCurrentLevel().levelWon()){
            scoreText.setText("Current Score: 0");
            totalScoreText.setText("Total Score: " + LevelImp.totalScore);
        }

        if(model.getCurrentLevel().livesAllLost()){
            scoreText.setText("Current Score: 0");
            totalScoreText.setText("Total Score: 0");
        }
    }

    public Scene getScene() {
        return this.scene;
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void draw() {
        model.tick();
        Level currentLevel = model.getCurrentLevel();
        List<Entity> entities = currentLevel.getEntities();

        for (EntityView entityView: entityViews) {
            entityView.markForDelete();
        }

        double heroXPos = model.getCurrentLevel().getHeroX();
        double heroYPos = model.getCurrentLevel().getHeroY();
        heroXPos -= xViewportOffset;
        heroYPos -= yViewportOffset;

        // Correct X-axis camera
        if (heroXPos < X_VIEWPORT_MARGIN) {
            if (xViewportOffset >= 0) { // Don't go further left than the start of the level
                xViewportOffset -= X_VIEWPORT_MARGIN - heroXPos;
                if (xViewportOffset < 0) {
                    xViewportOffset = 0;
                    model.getCurrentLevel().setHeroX(X_VIEWPORT_MARGIN);
                }
            }
        } else if (heroXPos > width - X_VIEWPORT_MARGIN) {
            xViewportOffset += heroXPos - (width - X_VIEWPORT_MARGIN);
        }

        // Correct Y-axis camera
        if (heroYPos > (height - Y_VIEWPORT_MARGIN)) {
            yViewportOffset += heroYPos - (height - Y_VIEWPORT_MARGIN);
            if (yViewportOffset > 0)
                yViewportOffset = 0;
        } else if (heroYPos < Y_VIEWPORT_MARGIN) {
            yViewportOffset -= Y_VIEWPORT_MARGIN - heroYPos;
        }

        currentLevel.getBGDrawer().update(xViewportOffset, yViewportOffset);

        for (Entity entity: entities) {
            boolean notFound = true;
            for (EntityView view: entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update(xViewportOffset, yViewportOffset);
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }

        for (EntityView entityView: entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }
}
