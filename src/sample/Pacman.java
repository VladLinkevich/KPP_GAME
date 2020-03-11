package sample;


import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Pacman{


    private Rect srcR;
    private Rect destR;

    private DIR direction = DIR.LEFT;


    private int scale;
    private final int size = 7;
    private final int startX = 100;
    private final int startY = 100;

    private int frame = 0;
    private int speed = 6;
    private int i = 0;


    Pacman() {
    }

    public void init(final int scale) {

        this.destR = new Rect(startX, startY, size, size);
        srcR = new Rect(0,0,18,18);
        this.scale = scale;


    }

    public void update(Scene scene) {

        switch (direction) {
            case UP:    destR.y -= scale; break;
            case DOWN:  destR.y += scale; break;
            case LEFT:  destR.x -= scale; break;
            case RIGHT: destR.x += scale; break;
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

            if (key.getCode() == KeyCode.W)         direction = DIR.UP;                     //
            if (key.getCode() == KeyCode.S)         direction = DIR.DOWN;                   //   W
            if (key.getCode() == KeyCode.D)         direction = DIR.RIGHT;                  // A S D
            if (key.getCode() == KeyCode.A)         direction = DIR.LEFT;                   //

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

            case UP:        destR.y += scale;   break;
            case DOWN:      destR.y -= scale;   break;
            case LEFT:      destR.x += scale;   break;
            case RIGHT:     destR.x -= scale;   break;
        }

        direction = DIR.STOP;
    }

    public Rect getDestR() {
        return this.destR;
    }

    public Rect getSrcR() {
        return this.srcR;
    }

}
