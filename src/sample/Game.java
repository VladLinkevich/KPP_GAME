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

    private int scale = 1;
    private int width;
    private int height;
    private int score = 0;

    private VBox box;
    private Canvas c;
    private GraphicsContext gc;
    private Scene scene;

    private Pacman pacman;
    private PacmanFX pacmanFX;

    private Map map;
    private Image image;

    public Game() {


    }


    public void init(Stage primaryStage, final int height, final int width) {

        this.height = height;
        this.width = width;

        box = new VBox();
        c = new Canvas(width * scale, height * scale);
        box.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        scene = new Scene(box, width * scale, height * scale);

        image = TextureManager.loadTexture("C:\\Mu_projects\\my_best_game\\sprite\\spritesheet.png");

        pacman = new Pacman();
        pacman.init();
        pacmanFX = new PacmanFX();
        pacmanFX.init(gc, image, new Rect(10, 10, 10, 10));


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
        map.draw(gc);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + score, 10, 30);

        pacmanFX.draw();

    }

    public void update() {

        Rect pacmanR = pacman.getDestR();

        pacman.update(scene);

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

        pacmanFX.setCoordinate(pacman.getSrcR(), pacman.getDestR());
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
