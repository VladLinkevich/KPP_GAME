package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PacmanFX implements Draw {

    private Texture texture;
    private GraphicsContext gc;

    @Override
    public void init(GraphicsContext gc, Image image, Rect srcR, Rect destR) {

        this.gc = gc;
        texture = new Texture(image, srcR, destR);

    }

    public void draw(){
        texture.draw(this.gc);
    }


}
