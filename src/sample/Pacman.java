package sample;


import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Pacman implements Person {


    private Rect srcR;
    private Rect destR;

    private DIR direction;
    private DIR newDir;


    private int scale;
    private final int size = 10;
    private final int startX = 100;
    private final int startY = 100;

    private int frame = 0;
    private int speed = 6;
    private int i = 0;

    private int speedChangePictures = 0;
    private void

    Pacman() {}

    public void init() {

        direction = DIR.STOP;
        newDir = DIR.STOP;

        this.destR = new Rect(startX, startY, size, size);
        srcR = new Rect(2,2,15,15);



    }

    @Override


    public void update(Scene scene) {



        switch (direction) {
            case UP:    destR.y -= 1; break;
            case DOWN:  destR.y += 1; break;
            case LEFT:  destR.x -= 1; break;
            case RIGHT: destR.x += 1; break;
        }

        animation();
        keyBoardController(scene);
    }

    private void keyBoardController(Scene scene) {

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {

            if (key.getCode() == KeyCode.UP)        direction = DIR.UP;                     //
            if (key.getCode() == KeyCode.DOWN)      direction = DIR.DOWN;                   //    ^
            if (key.getCode() == KeyCode.RIGHT)     direction = DIR.RIGHT;                  // <- | ->
            if (key.getCode() == KeyCode.LEFT)      direction = DIR.LEFT;                   //

            if (key.getCode() == KeyCode.W)         newDir = DIR.UP;                     //
            if (key.getCode() == KeyCode.S)         newDir = DIR.DOWN;                   //   W
            if (key.getCode() == KeyCode.D)         newDir = DIR.RIGHT;                  // A S D
            if (key.getCode() == KeyCode.A)         newDir = DIR.LEFT;                   //

        });
    }

    public void animation() {

        this.i++;

        if ((i % this.speed) == 0) {
            frame++;
        }

        switch (direction) {

            case UP:        srcR.y = 42;    break;
            case DOWN:      srcR.y = 62;    break;
            case LEFT:      srcR.y = 2;     break;
            case RIGHT:     srcR.y = 22;    break;
        }

        if (frame % 3 == 0) srcR.x = 2;

        if (frame % 3 == 1) srcR.x = 22;

        if (frame % 3 == 2) {

            srcR.x = 42;
            srcR.y = 2;
        }
    }




    public void stopRun() {

        switch (direction) {

            case UP:        destR.y += scale * 10;   break;
            case DOWN:      destR.y -= scale * 10;   break;
            case LEFT:      destR.x += scale * 10;   break;
            case RIGHT:     destR.x -= scale * 10;   break;
        }

        newDir = DIR.STOP;
        direction = DIR.STOP;
    }

    public Rect getDestR() {
        return this.destR;
    }

    @Override
    public DIR getDirection() {
        return direction;
    }

    @Override
    public DIR getNewDirection() {
        return newDir;
    }

    public Rect getSrcR() {
        return this.srcR;
    }

    @Override
    public void setDirection(DIR direction) {
        this.direction = direction;
    }
}
