package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface Draw {

    void init(GraphicsContext gc, Image image, Rect srcR, Rect destR);

    void draw();
}