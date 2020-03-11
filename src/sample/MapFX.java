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


    MapFX(List<Rect> fancec, List<Rect> bonus, double sizeBlockX, double sizeBlockY){

        this.fancec = fancec;
        this.bonus = bonus;
        this.sizeBlockX = sizeBlockX;
        this.sizeBlockY = sizeBlockY;
    }


    public void init(GraphicsContext gc) {
        this.gc = gc;
    }

    public void draw() {

        for (Rect r : fancec) {
            gc.setFill(Color.YELLOW);
            gc.fillRect(r.x, r.y, sizeBlockX, sizeBlockY);

            gc.setFill(Color.RED);
            gc.fillRect(r.x, r.y, sizeBlockX - 1, sizeBlockY - 1);
        }

        for (Rect r : bonus) {
            gc.setFill(Color.GREEN);
            gc.fillOval(r.x, r.y, r.w, r.h);
        }

    }
}
