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

    private VBox box;
    private Canvas c;
    private GraphicsContext gc;
    private Scene scene;

    private Pacman pacman;
    private PacmanFX pacmanFX;

    private Map map;
    private MapFX mapFX;
    private Image image;

    Opponent opponent;
    PacmanFX opponentFX;

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
        pacmanFX.init(gc, image, pacman.getSrcR(), pacman.getDestR().multiplication(scale));


        opponent = new Opponent();
        opponent.init(scale);
        opponentFX = new PacmanFX();
        opponentFX.init(gc, image, opponent.getSrcR(), opponent.getDestR());


        map =  new  Map();
        map.init(this.scale, this.width, this.height);
        mapFX = new MapFX(map.getFancec(), map.getBonus(), map.getSizeBlockX(), map.getSizeBlockY());
        mapFX.init(gc);


        primaryStage.setScene(scene);
        primaryStage.setTitle("The best game");
        primaryStage.show();


    }


    void draw() {

        gc.clearRect(0, 0, width * scale, height * scale);                                // clear board


        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * scale, height * scale);

        mapFX.draw();
        // opponentFX.draw();
        pacmanFX.draw();

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + score, 10, 30);
    }

    public void update() {


        pacman.update(scene);

        Rect pacmanR = pacman.getDestR();

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
