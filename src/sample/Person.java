package sample;


import javafx.scene.image.Image;

public abstract class Person {

    private Texture texture;
    public DIR direction = DIR.LEFT;
    private int scale;

    private enum DIR {LEFT, RIGHT, UP, DOWN, STOP}

    public void init(final int scale, final Image image, final Rect srcR, final Rect destR) {

        this.scale = scale;

        texture = new Texture(image, srcR, destR);

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
    }


}
