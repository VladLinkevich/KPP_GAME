package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class MapFX{

    private GraphicsContext gc;

    private List<Rect> fancec;
    private List<Rect> bonus;
    private double sizeBlockX;
    private double sizeBlockY;
    private Texture texture;

    private int scale = 1;


    MapFX(List<Rect> fancec, List<Rect> bonus, double sizeBlockX, double sizeBlockY, int scale){


        this.fancec = fancec;
        this.bonus = bonus;
        this.sizeBlockX = sizeBlockX;
        this.sizeBlockY = sizeBlockY;
        this.scale = scale;
        this.texture = new Texture("sprite\\win.gif",
                0, 0, 400, 400,
                92 * scale, 172 * scale,scale * 16, scale * 16);
    }


    public void init(GraphicsContext gc) {
        this.gc = gc;
    }

    public void draw(double full) {

        for (Rect r : fancec) {
            gc.setFill(Color.YELLOW);
            gc.fillRect(r.x * scale, r.y * scale, sizeBlockX * scale, sizeBlockY * scale);

            gc.setFill(Color.RED);
            gc.fillRect(r.x * scale, r.y * scale,
                    (sizeBlockX - 1) * scale, (sizeBlockY - 1) * scale);
        }
        gc.setFill(Color.BLUE);
        gc.fillOval(92 * scale, 172 * scale,scale * 16, scale * 16);
        gc.setFill(Color.BLACK);

        if (full <= 16) {
            gc.fillRect(92 * scale, (172 + full) * scale, scale * 16, scale * (16 - full));
        }else {
            TextureManager.drawTexture(gc, texture.img, texture.srcR, texture.destR);
        }

        for (Rect r : bonus) {
            gc.setFill(Color.GREEN);
            gc.fillOval(r.x * scale, r.y * scale, r.w * scale, r.h * scale);
        }

    }
}
