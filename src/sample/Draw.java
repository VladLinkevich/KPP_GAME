package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface Draw {

    GraphicsContext gc = null;
    Texture texture = null;


    void init(GraphicsContext gc, Image image, Rect srcR, Rect destR);

    void draw();
}