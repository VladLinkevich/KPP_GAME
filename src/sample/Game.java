package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static sample.Animation.getFear;
import static sample.Animation.setEndFear;
import static sample.Animation.setFear;


public class Game {

    private int scale = 4;
    private int width;
    private int height;
    private int score = 0;

    private VBox box;
    private Canvas c;
    private GraphicsContext gc;
    private Scene scene;
    private Stage primaryStage;

    private Pacman pacman;
    private ObjectDraw pacmanDraw;

    private Map map;
    private MapFX mapFX;
    private Image image;

    private Ghost redGhost = null;
    private ObjectDraw redGhostDraw;
    private Ghost pinkGhost = null;
    private ObjectDraw pinkGhostDraw;

    Vector<Ghost> ghosts;
    Vector<ObjectDraw> objectDraws;

    public int timeFear = 0;



    public Game() {


    }


    public void init(Stage primaryStage, final int height, final int width) {

        this.primaryStage = primaryStage;
        this.height = height;
        this.width = width;

        ghosts = new Vector<Ghost>();
        objectDraws = new Vector<ObjectDraw>();

        box = new VBox();
        c = new Canvas(width * scale, height * scale);
        box.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        scene = new Scene(box, width * scale, height * scale);

        image = TextureManager.loadTexture("sprite\\spritesheet.png");

        pacman = new Pacman();
        pacman.init();
        pacmanDraw = new ObjectDraw();
        pacmanDraw.init(gc, image, pacman.getSrcR(), pacman.getDestR().copy().multiplication(scale));


        redGhost = new RedGhost();
        redGhost.init();

        redGhostDraw = new ObjectDraw();
        redGhostDraw.init(gc, image, redGhost.getSrcR(), redGhost.getDestR());

        pinkGhost = new PinkGhost();
        pinkGhost.init();

        pinkGhostDraw = new ObjectDraw();
        pinkGhostDraw.init(gc, image, pinkGhost.getSrcR(), pinkGhost.getDestR());


        ghosts.add(redGhost);
        ghosts.add(pinkGhost);
        //ghosts.add(redGhost);

        objectDraws.add(redGhostDraw);
        objectDraws.add(pinkGhostDraw);

        map =  new  Map();
        map.init(this.width, this.height);
        mapFX = new MapFX(map.getFancec(), map.getBonus(), map.getSizeBlockX(), map.getSizeBlockY(), scale);
        mapFX.init(gc);


        primaryStage.setScene(scene);
        primaryStage.setTitle("The best game");
        primaryStage.show();


    }

    public void startScrene(){

        primaryStage.setScene(scene);
        primaryStage.setTitle("The best game");
        primaryStage.show();
    }

    void draw() {

        gc.clearRect(0, 0, width * scale, height * scale);                                // clear board


        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * scale, height * scale);

        mapFX.draw();

        pacmanDraw.draw(pacman.getDestR().copy().multiplication(scale));

        for (int i = 0; i < ghosts.size(); ++i) {
            objectDraws.get(i).draw(ghosts.get(i).getDestR().copy().multiplication(scale));
        }

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + score, 10, 30);
    }

    public void update() {


        pacman.update(scene);
        Rect pacmanR = pacman.getDestR();

        for (Ghost ghost : ghosts) {
            ghost.update(map.getFancec(), pacmanR);
            Rect redR = ghost.getDestR();
            for (Rect r : map.getFancec()) {
                if (Collision.AABB(redR, r)) {
                    ghost.stopRun();
                    break;
                }
            }
        }

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

        if(score == 10 || score == 250){
            setFear(true);
            timeFear = 0;
        }

        timeFear++;

        if (timeFear == 300 && getFear()){
            Animation.setEndFear(true);
        }

        if (timeFear == 400 && getFear()){
            setEndFear(false);
            setFear(false);
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
