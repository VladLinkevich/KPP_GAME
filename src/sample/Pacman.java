package sample;


import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Pacman extends Person {


    private Texture texture;
    private DIR direction = DIR.LEFT;

    private final int startX = 51;
    private final int staetY = 51;
    private int scale = 0;
    private final int size = 6;

    private int frame = 0;
    private int speed = 6;
    private int i = 0;

    private enum DIR {LEFT, RIGHT, UP, DOWN, STOP}

    Pacman() {
    }

    public void init(final int scale, final Image image) {

        this.scale = scale;

        texture = new Texture(image,
                new Rect(3, 3, 15, 15),
                new Rect(staetY * scale, startX * scale, size * scale, size * scale));

    }

    public void update() {

        switch (direction) {
            case UP:
                texture.destR.y -= scale;
                break;
            case DOWN:
                texture.destR.y += scale;
                break;
            case LEFT:
                texture.destR.x -= scale;
                break;
            case RIGHT:
                texture.destR.x += scale;
                break;
        }

        animation();

        keyBoardController();
    }

    private void keyBoardController() {

        Game.getScene().addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.UP) {
                direction = DIR.UP;
            }
            if (key.getCode() == KeyCode.DOWN) {
                direction = DIR.DOWN;
            }
            if (key.getCode() == KeyCode.RIGHT) {
                direction = DIR.RIGHT;
            }
            if (key.getCode() == KeyCode.LEFT) {
                direction = DIR.LEFT;
            }
            if (key.getCode() == KeyCode.W) {
                direction = DIR.UP;
            }
            if (key.getCode() == KeyCode.S) {
                direction = DIR.DOWN;
            }
            if (key.getCode() == KeyCode.D) {
                direction = DIR.RIGHT;
            }
            if (key.getCode() == KeyCode.A) {
                direction = DIR.LEFT;
            }
        });
    }

    public void animation() {

        this.i++;

        if ((i % this.speed) == 0) {
            frame++;
        }

        switch (direction) {
            case UP:
                texture.srcR.y = 42;
                break;
            case DOWN:
                texture.srcR.y = 62;
                break;
            case LEFT:
                texture.srcR.y = 2;
                break;
            case RIGHT:
                texture.srcR.y = 22;
                break;
        }

        if (frame % 3 == 0) {
            texture.srcR.x = 2;
        }

        if (frame % 3 == 1) {
            texture.srcR.x = 22;
        }

        if (frame % 3 == 2) {
            texture.srcR.x = 42;
            texture.srcR.y = 2;
        }
    }

    public void draw() {

        texture.Draw();
    }

    public void stopRun() {
        switch (direction) {
            case UP:
                texture.destR.y += scale;
                break;
            case DOWN:
                texture.destR.y -= scale;
                break;
            case LEFT:
                texture.destR.x += scale;
                break;
            case RIGHT:
                texture.destR.x -= scale;
                break;
        }

        direction = DIR.STOP;
    }

    public Rect getCoordinate() {
        return texture.destR;
    }

}
