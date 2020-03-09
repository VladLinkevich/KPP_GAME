package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private Texture texture;
    private final double sizeX = 232;
    private final double sizeY = 256;

    private double sizeBlockX;
    private double sizeBlockY;

    List<Rect> fancec;
    List<Rect> bonus;

    private final int[][] map = {
//           0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 202122232425
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//0
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},//1
            {1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//2
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},//3
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//4
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//5
            {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//6
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//7
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//8
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//9
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//10
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//11
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//12
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//13
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//14
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//15
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//16
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//17
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//18
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//0

            //  {1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1},//15
            //  {1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},//16
            //  {1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},//17
            //  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},//18
            //  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},//19
            //  {1,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,1},//20
            //  {1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},//21
            //  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},//22
            //  {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},//23

    };

    public void init(double scale, double width, double height) {

        sizeBlockX = (width * scale) / (map[0].length);
        sizeBlockY = (height * scale) / (map.length);

        //Image map = TextureManager.LoadTexture("C:\\Users\\mipro\\Desktop\\map.png");
        //this.texture = new Texture(map, 0, 0, sizeY, sizeX, 0, 0, width * scale, height * scale);

        fancec = new ArrayList<>();
        bonus = new ArrayList<>();


        refershMap();


    }

    public void Draw() {
        //texture.Draw();

        GraphicsContext gc = Game.getGC();

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

    private void initBonus() {

    }

    private void refershMap() {


        for (int i = 0, end = map.length; i < end; i++) {
            for (int j = 0, endL = map[i].length; j < endL; ++j) {

                if (map[i][j] == 1) {
                    fancec.add(new Rect(j * sizeBlockX, i * sizeBlockY, sizeBlockX, sizeBlockY));
                }
                if (map[i][j] == 0) {
                    bonus.add(new Rect((j * sizeBlockX) + (sizeBlockX / 3),
                            (i * sizeBlockY) + (sizeBlockY / 3),
                            sizeBlockX / 3, sizeBlockY / 3));
                }
            }

        }

    }

    public List<Rect> getFancec() {
        return fancec;
    }

    public List<Rect> getBonus() {
        return bonus;
    }

}
