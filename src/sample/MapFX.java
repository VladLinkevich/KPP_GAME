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

    private int scale = 1;


    MapFX(List<Rect> fancec, List<Rect> bonus, double sizeBlockX, double sizeBlockY, int scale){

        this.fancec = fancec;
        this.bonus = bonus;
        this.sizeBlockX = sizeBlockX;
        this.sizeBlockY = sizeBlockY;
        this.scale = scale;
    }


    public void init(GraphicsContext gc) {
        this.gc = gc;
    }

    public void draw() {

        for (Rect r : fancec) {
            gc.setFill(Color.YELLOW);
            gc.fillRect(r.x * scale, r.y * scale, sizeBlockX * scale, sizeBlockY * scale);

            gc.setFill(Color.RED);
            gc.fillRect(r.x * scale, r.y * scale,
                    (sizeBlockX - 1) * scale, (sizeBlockY - 1) * scale);
        }

        for (Rect r : bonus) {
            gc.setFill(Color.GREEN);
            gc.fillOval(r.x * scale, r.y * scale, r.w * scale, r.h * scale);
        }

    }
}
