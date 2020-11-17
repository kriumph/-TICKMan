package stickman.view;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import stickman.levels.DefaultLevelBuilder;
import stickman.levels.LevelImp;
import stickman.memento.CareTaker;
import stickman.model.GameEngineImpl;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class KeyboardInputHandler{
    private final GameEngineImpl model;
    private boolean left = false;
    private boolean right = false;
    private boolean shoot = false;
    private Set<KeyCode> pressedKeys = new HashSet<>();
    private Map<String, MediaPlayer> sounds = new HashMap<>();
    private CareTaker careTaker = new CareTaker();

    KeyboardInputHandler(GameEngineImpl model) {
        this.model = model;


        URL mediaUrl = getClass().getResource("/jump.wav");
        String jumpURL = mediaUrl.toExternalForm();
        Media jumpSound = new Media(jumpURL);
        MediaPlayer jumpMediaPlayer = new MediaPlayer(jumpSound);
        sounds.put("jump", jumpMediaPlayer);

        mediaUrl = getClass().getResource("/shoot.wav");
        String shootURL = mediaUrl.toExternalForm();
        Media shootSound = new Media(shootURL);
        MediaPlayer shootMediaPlayer = new MediaPlayer(shootSound);
        sounds.put("shoot", shootMediaPlayer);
    }

    void handlePressed(KeyEvent keyEvent) {
        if (pressedKeys.contains(keyEvent.getCode())) {
            return;
        }
        pressedKeys.add(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.Q)){
            if(GameEngineImpl.saved == true) {
                System.out.println("LOAD SUCCESSFULLY");
                model.load(careTaker.getLevelMemento());
            }else{
                System.out.println("You do not save anything yet.");
            }
        }

        if (keyEvent.getCode().equals(KeyCode.S)){
            System.out.println("SAVE SUCCESSFULLY");
            careTaker.setLevelMemento(this.model.save());
        }

        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            if (model.getCurrentLevel().levelWon() || model.getCurrentLevel().livesAllLost()){
                model.loadLevel(1);
                model.getCurrentLevel().setLevelTransited(true);
                GameEngineImpl.currentLevelNumber = 1;
                DefaultLevelBuilder.lives = 3;
                LevelImp.totalScore = 0;
            }
        }

        if (keyEvent.getCode().equals(KeyCode.UP)) {
            if (model.jump()) {
                MediaPlayer jumpPlayer = sounds.get("jump");
                jumpPlayer.stop();
                jumpPlayer.play();
            }
        }

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            left = true;
        }
        else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            right = true;
        } else if (keyEvent.getCode().equals(KeyCode.SPACE)) {
            if ((left || right) && !(left && right)) {
                shoot = true;
            }
        } else {
            return;
        }

        // Stop moving
        if (left && right) {
            model.stopMoving();
            return;
        }

        // Move
        if (left)
            model.moveLeft();
        if (right)
            model.moveRight();

        // shoot
        if ((left || right) && shoot) {
            if (model.getCurrentLevel().shoot()) {
                MediaPlayer jumpPlayer = sounds.get("shoot");
                jumpPlayer.stop();
                jumpPlayer.play();
            }
            shoot = false;
        }
    }

    void handleReleased(KeyEvent keyEvent) {
        pressedKeys.remove(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            left = false;
        }
        else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            right = false;
        } else {
            return;
        }

        if (!(right || left)) {
            model.stopMoving();
        } else if (right) {
            model.moveRight();
        } else {
            model.moveLeft();
        }
    }
}
