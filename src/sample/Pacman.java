package sample;


import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import static sample.Animation.animationPacman;


public class Pacman /*implements Person*/ {


    private Rect srcR;
    private Rect destR;

    private DIR direction;
    private DIR newDirection = DIR.STOP;

    private final int size = 10;
    private final int startX = 100;
    private final int startY = 110;

    private int frame = 0;
    private int speedChangePictures = 5;
    private int pictures = 0;

    Pacman() {}

    public void init() {

        direction = DIR.STOP;

        this.destR = new Rect(startX, startY, size, size);
        srcR = new Rect(2.5,2.5,15,15);

    }




    public void update(Scene scene) {

        this.frame++;

        if ((frame % speedChangePictures) == 0) {
            pictures++;
        }


        switch (direction) {
            case UP:    destR.y -= 1; break;
            case DOWN:  destR.y += 1; break;
            case LEFT:  destR.x -= 1; break;
            case RIGHT: destR.x += 1; break;
        }

        animationPacman(srcR, direction, pictures);

        keyBoardController(scene);

        if (frame % 10 == 0){
            direction = newDirection;
        }
    }

    private void keyBoardController(Scene scene) {

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {

                if (key.getCode() == KeyCode.UP) newDirection = DIR.UP;                     //
                if (key.getCode() == KeyCode.DOWN) newDirection = DIR.DOWN;                 //    ^
                if (key.getCode() == KeyCode.RIGHT) newDirection = DIR.RIGHT;               // <- | ->
                if (key.getCode() == KeyCode.LEFT) newDirection = DIR.LEFT;                 //

                if (key.getCode() == KeyCode.W) newDirection = DIR.UP;                     //
                if (key.getCode() == KeyCode.S) newDirection = DIR.DOWN;                   //   W
                if (key.getCode() == KeyCode.D) newDirection = DIR.RIGHT;                  // A S D
                if (key.getCode() == KeyCode.A) newDirection = DIR.LEFT;                   //

        });
    }





    public void stopRun() {

        switch (direction) {

            case UP:        destR.y += 1;   break;
            case DOWN:      destR.y -= 1;   break;
            case LEFT:      destR.x += 1;   break;
            case RIGHT:     destR.x -= 1;   break;
        }
        direction = DIR.STOP;
    }


    public Rect getDestR() {
        return this.destR;
    }

    public DIR getDirection() {
        return direction;
    }



    public Rect getSrcR() {
        return this.srcR;
    }

    public void setDirection(DIR direction) {
        this.direction = direction;
    }
}
