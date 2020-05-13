package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.nio.CharBuffer;
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
    private double score = 0;
    private Main main;
    private int frame = 0;
    private boolean save = false;

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


    public void init(Main main, Stage primaryStage, final int height, final int width) {

        this.main = main;
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

        ghosts.add(redGhost);
        //ghosts.add(pinkGhost);
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

        mapFX.draw(score * 0.32);

        pacmanDraw.draw(pacman.getDestR().copy().multiplication(scale));

        for (int i = 0; i < ghosts.size(); ++i) {
            objectDraws.get(i).draw(ghosts.get(i).getDestR().copy().multiplication(scale));
        }

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + score, 10, 30);
    }

    public void update() {

        if(KeyboardController.pauseController(scene)){
            saveGame();
        }



        pacman.setNewDirection(KeyboardController.pacmanController(scene));
        pacman.update();

        Rect pacmanR = pacman.getDestR();

        for (Ghost ghost : ghosts) {
            ghost.update(map.getFancec(), pacmanR);
            Rect redR = ghost.getDestR();
            if ((pacmanR.x <= redR.x + 1 && pacmanR.x >= redR.x - 1) &&
                    (pacmanR.y <= redR.y + 1 && pacmanR.y >= redR.y - 1)){
                if (getFear()){ redR.x = 100; redR.y = 80; }
                else { main.stopGame(); }
            }
            for (Rect r : map.getFancec()) {
                if (Collision.AABB(redR, r)) {
                    ghost.stopRun();
                    break;
                }
            }
        }
        frame++;
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

        if (score >= 50) {
            Rect g = new Rect(90 , 170 ,  20,  20);
            if (Collision.AABB(pacmanR, g)) {
                main.stopGame();
            }
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

    private void saveGame()  {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bonus.dat"));

            oos.writeObject(map.getBonus());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        continueGame();
    }

    public void continueGame(){

        List<Rect> b = new ArrayList<>();

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("bonus.dat"));
            b = (ArrayList<Rect>)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void restart(){

        for (Ghost ghost : ghosts){
            ghost.restart();
        }
        map.restart();
        pacman.restart();
        score = 0;
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
