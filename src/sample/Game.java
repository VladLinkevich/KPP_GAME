package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Game {

    private int scale = 4;
    private int width;
    private int height;
    private int score = 0;

    //static private Group root;
    private VBox box;
    static private Canvas c;
    static private GraphicsContext gc;
    static private Scene scene;

    private Pacman pacman;

    private Map map;
    private Image image;

    public Game() {


    }


    public void init(Stage primaryStage, final int height, final int width) {

        this.height = height;
        this.width = width;

        image = TextureManager.LoadTexture("C:\\Mu_projects\\my_best_game\\sprite\\spritesheet.png");

        pacman = new Pacman();
        pacman.init(scale, image);

        box = new VBox();
        c = new Canvas(width * scale, height * scale);
        box.getChildren().add(c);
        gc = c.getGraphicsContext2D();

        scene = new Scene(box, width * scale, height * scale);

        map = new Map();
        map.init(this.scale, this.width, this.height);

        primaryStage.setScene(scene);
        primaryStage.setTitle("The best game");
        primaryStage.show();


    }


    void draw() {

        gc.clearRect(0, 0, width, height);                                // clear board

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * scale, height * scale);
        map.Draw();

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + score, 10, 30);


        pacman.draw();

    }

    public void update() {

        Rect pacmanR = pacman.getCoordinate();

        pacman.update();

        for (Rect r : map.getFancec()) {
            if (Collision.AABB(pacmanR, r)) {
                pacman.stopRun();
                break;
            }
        }

        for (Rect r : map.getBonus()) {
            if (Collision.AABB(pacmanR, r)) {
                map.getBonus().remove(r);
                score++;
                break;
            }
        }


    }

    public static Scene getScene() {
        return scene;
    }

    public static GraphicsContext getGC() {
        return gc;
    }

    public int getScale() {
        return scale;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
