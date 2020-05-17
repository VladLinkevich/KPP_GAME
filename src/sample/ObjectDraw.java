package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjectDraw {


    private Texture texture;
    private GraphicsContext gc;


    public void init(GraphicsContext gc, Image image, Rect srcR, Rect destR) {

        this.gc = gc;
        texture = new Texture(image, srcR, destR);

    }

    public void draw(Rect destR){

        this.texture.destR = destR;
        texture.draw(this.gc);
    }

    public void draw(){
        texture.draw(this.gc);
    }
}
