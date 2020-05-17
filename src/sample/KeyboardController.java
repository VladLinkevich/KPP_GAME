package sample;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.concurrent.atomic.AtomicReference;

public class KeyboardController {

    static DIR dir = DIR.STOP;
    static boolean pause = false;

    public static DIR pacmanController(Scene scene){

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {

            if (key.getCode() == KeyCode.UP)    dir = DIR.UP;                  //
            if (key.getCode() == KeyCode.DOWN)  dir = DIR.DOWN;                //    ^
            if (key.getCode() == KeyCode.RIGHT) dir = DIR.RIGHT;               // <- | ->
            if (key.getCode() == KeyCode.LEFT)  dir = DIR.LEFT;                //

            if (key.getCode() == KeyCode.W)     dir = DIR.UP;                     //
            if (key.getCode() == KeyCode.S)     dir = DIR.DOWN;                   //   W
            if (key.getCode() == KeyCode.D)     dir = DIR.RIGHT;                  // A S D
            if (key.getCode() == KeyCode.A)     dir = DIR.LEFT;                   //

        });

        return dir;
    }

    public static boolean pauseController(Scene scene){


        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.ESCAPE) pause = true;

        });

        return pause;
    }
}
