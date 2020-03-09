package sample;

import javafx.scene.image.Image;

public class Texture {

    public final Image img;
    public Rect srcR, destR;


    public Texture(Image img) {
        this.img = img;
        srcR = new Rect();
        destR = new Rect();
    }

    public Texture(String path) {

        this.img = TextureManager.LoadTexture(path);
        srcR = new Rect();
        destR = new Rect();

    }

    public Texture(Image image, Rect srcR, Rect destR) {

        this.img = image;
        this.srcR = srcR;
        this.destR = destR;

    }

    public Texture(Image img, double sx, double sy, double sh, double sw,
                   double dx, double dy, double dh, double dw) {
        this.img = img;
        srcR = new Rect(sx, sy, sh, sw);
        destR = new Rect(dx, dy, dh, dw);
    }

    public Texture(String path, double sx, double sy, double sh, double sw,
                   double dx, double dy, double dh, double dw) {

        this.img = TextureManager.LoadTexture(path);
        srcR = new Rect(sx, sy, sh, sw);
        destR = new Rect(dx, dy, dh, dw);
    }

    public void Draw() {
        TextureManager.DrawTexture(img, srcR, destR);
    }


}
