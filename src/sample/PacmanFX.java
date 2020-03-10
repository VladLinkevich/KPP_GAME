package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PacmanFX {

    private Texture texture;
    private GraphicsContext gc;

    public void init(GraphicsContext gc, Image img, Rect startRectR){

        this.gc = gc;
        texture = new Texture(img, new Rect(2, 2, 18, 18), startRectR);

    }

    public void draw(){
        texture.draw(this.gc);
    }

    public void setCoordinate(Rect srcR, Rect destR){
        texture.srcR = srcR;
        texture.destR = destR;

    }
}
