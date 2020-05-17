package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MealFX {

    Texture texture;
    GraphicsContext gc;
    int scale;

    public void init(GraphicsContext gc, Image img, Rect srcR, Rect destR, int scale){

        this.gc = gc;
        this.texture = new Texture(img, srcR, destR);
        this.scale = scale;

    }

    public void draw() {
        TextureManager.drawTexture(gc, texture.img, texture.srcR, texture.destR.copy().multiplication(scale));
    }
    public Rect getDectR(){
        return texture.destR;
    }

    public void delete(){
        texture.destR.x += 1000;
    }

    public void setCoordinate(double x){
        this.texture.destR.x = x;
    }
    public void restore(){
        if (texture.destR.x >= 1000)
        texture.destR.x -= 1000;
    }


}
