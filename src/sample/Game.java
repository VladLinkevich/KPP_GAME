package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static sample.Animation.getFear;
import static sample.Animation.setEndFear;
import static sample.Animation.setFear;


public class Game {

    private Lvl lvl;
    private int level = 1;
    private int levelSize = 40;
    private int scale = 4;
    private int width;
    private int height;
    private double score = 0;
    private Main main;
    private int frame = 0;
    //private boolean save = false;
    private int time = 0;
    private int skipByte = 0;

    private VBox box;
    private Canvas c;
    private GraphicsContext gc;
    private Scene scene;
    private Stage primaryStage;
    private WinWindows ww;
    private Pacman pacman;
    private ObjectDraw pacmanDraw;

    private Map map;
    private MapFX mapFX;
    private Image image;
    private MealFX[] meals;


    private ObjectDraw[] objectDraws;
    private ObjectDraw pinkGhostDraw;
    private Ghost[] ghosts;




    private boolean writeReplay;
    private boolean playReplay = true;
    private int timeFear = 0;
    private Rect[] fakePacman;
    private Rect maxRect = new Rect(200, 200, 0, 0);


    public Game() {}


    public void init(Main main, Stage primaryStage, final int height, final int width) {

        this.main = main;
        this.primaryStage = primaryStage;
        this.height = height;
        this.width = width;

        image = TextureManager.loadTexture("sprite\\spritesheet.png");
        levelSize *= scale;
        this.lvl = Lvl.EASY;
        ghosts = new Ghost[4];
        ghosts[0] = new Ghost();
        ghosts[0].init(new Rect(2.5,82.5,15,15), new Rect(10,10,10,10));
        ghosts[1] = new Ghost();
        ghosts[1].init(new Rect(2.5,102.5,15,15), new Rect(180,10,10,10));
        ghosts[2] = new Ghost();
        ghosts[2].init(new Rect(2.5,122.5,15,15), new Rect(180,180,10,10));
        ghosts[3] = new Ghost();
        ghosts[3].init(new Rect(2.5,142.5,15,15), new Rect(10,180,10,10));




        box = new VBox();
        c = new Canvas(width * scale, height * scale);
        box.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        scene = new Scene(box, width * scale, height * scale);

        objectDraws = new ObjectDraw[4];

        objectDraws[0] = new ObjectDraw();
        objectDraws[0].init(gc, image, ghosts[0].getSrcR(), ghosts[0].getDestR());
        objectDraws[1] = new ObjectDraw();
        objectDraws[1].init(gc, image, ghosts[1].getSrcR(), ghosts[1].getDestR());
        objectDraws[2] = new ObjectDraw();
        objectDraws[2].init(gc, image, ghosts[2].getSrcR(), ghosts[2].getDestR());
        objectDraws[3] = new ObjectDraw();
        objectDraws[3].init(gc, image, ghosts[3].getSrcR(), ghosts[3].getDestR());

        pacman = new Pacman();
        pacman.init();
        pacmanDraw = new ObjectDraw();
        pacmanDraw.init(gc, image, pacman.getSrcR(), pacman.getDestR().copy().multiplication(scale));


        meals = new MealFX[4];
        meals[0] = new MealFX();
        meals[0].init(gc, image, new Rect(170.0, 162.5, 15, 15),
                new Rect(10, 10, 10, 10), scale);
        meals[1] = new MealFX();
        meals[1].init(gc, image, new Rect(170.0, 182.5, 15, 15),
                new Rect(180, 180, 10, 10), scale);
        meals[2] = new MealFX();
        meals[2].init(gc, image, new Rect(170.0, 202.5, 15, 15),
                new Rect(180, 10, 10, 10), scale);
        meals[3] = new MealFX();
        meals[3].init(gc, image, new Rect(170.0, 222.5, 15, 15),
                new Rect(10, 180, 10, 10), scale);

        fakePacman = new Rect[4];
        fakePacman[0] = new Rect(180, 180,0,0);
        fakePacman[1] = new Rect(180, 180,0,0);
        fakePacman[2] = new Rect(180, 180,0,0);
        fakePacman[3] = new Rect(180, 180,0,0);

        ww = new WinWindows();
        ww.init(primaryStage, this);

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

        mapFX.draw(score * (0.32 / level));

        pacmanDraw.draw(pacman.getDestR().copy().multiplication(scale));

        for (int i = 0; i < ghosts.length; ++i) {
            objectDraws[i].draw(ghosts[i].getDestR().copy().multiplication(scale));
        }

        for (MealFX m : meals){
            m.draw();
        }


        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", levelSize));
        gc.fillText("Level " + level, (100 * scale) - levelSize * 1.35, (100 * scale) - levelSize);

        gc.setFill(Color.BLACK);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (int)score, 10, 30);
    }

    public void update() {

        frame++;


        if (levelSize > 0)
        levelSize -= 5;


        pacman.setNewDirection(KeyboardController.pacmanController(scene));


        pacman.update();

        Rect pacmanR = pacman.getDestR();
        int numberGhost = 0;
        for (Ghost ghost : ghosts) {
            ghost.update(map.getFancec(), choiceLvl(frame, numberGhost) );
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
            numberGhost++;
        }


        if (frame % 10 == 0){
            for (MealFX m : meals){
                if (Collision.AABB(pacmanR, m.getDectR())){
                    m.delete();
                    setFear(true);
                    timeFear = 0;
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

        if ((score >= 50 && level == 1) ||
                (score >= 100 && level == 2) ||
                (score >= 150 && level == 3) ||
                (score >= 200 && level == 4)) {
            Rect g = new Rect(90 , 170 ,  20,  20);
            if (Collision.AABB(pacmanR, g)) {
                level++;
                if (level == 5) {
                  ww.startScrene();
                }
                restart();

            }
        }
        timeFear++;

        if (timeFear == 125 && getFear()){
            Animation.setEndFear(true);
        }

        if (timeFear == 200 && getFear()){
            setEndFear(false);
            setFear(false);
        }

        if(KeyboardController.pauseController(scene) && frame % 10 == 0){
            KeyboardController.pause = false;
            saveGame("save\\save.bin");
        }

        if(KeyboardController.replayController(scene) && frame % 10 == 0){
            writeReplay = true;
            saveReplay("save\\saveReplay.bin");
        }

        if(playReplay && (frame % 10 == 0 || frame == 1) ){

            if (frame == 1){

                skipByte = 0;
                continueGame("save\\saveReplay.bin");
            } else {
                for (Ghost g : ghosts){
                    g.isReplay();
                }
                playReplay("save\\saveReplay.bin");
            }
        }

    }

    private void saveGame(String path)  {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(path,false)) /*FileManager.openFile("save\\save.bin")*/){

            for (Ghost g : ghosts){

                WriteToFile.writeDIR(dos, g.getDirection());
                WriteToFile.writeRect(dos, g.getDestR());
            }

            for (MealFX m : meals){
                dos.writeDouble(m.getDectR().x);
            }

            dos.writeInt(map.getBonus().size());

            for (Rect r : map.getBonus()){
                WriteToFile.writeRect(dos, r);
            }

            WriteToFile.writeDIR(dos, pacman.getDirection());
            WriteToFile.writeRect(dos, pacman.getDestR());

            dos.writeDouble(score);
            dos.writeInt(level);
            WriteToFile.writeDiff(dos, lvl);


        }
        catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("saveReplay");
        }

        if (!writeReplay)
        main.stopGame();
    }

    public void continueGame(String path){

        try(DataInputStream dis = new DataInputStream(new FileInputStream(path)))
        {
            for (Ghost g : ghosts){
                g.setDirection(ReadFile.readDIR(dis));
                g.setCordinate(dis.readDouble(), dis.readDouble());
            }


            for (MealFX m : meals){
                m.setCoordinate(dis.readDouble());
            }


            int lenght = dis.readInt();



            for (int i = 0, end = map.getBonus().size(); i < end; i++){
                if (i < lenght){
                    map.getBonus().get(i).x = dis.readDouble();
                    map.getBonus().get(i).y = dis.readDouble();
                    skipByte += 16;
                }
            }


            pacman.setDirection(ReadFile.readDIR(dis));
            KeyboardController.dir = pacman.getDirection();
            pacman.setCoordinate(dis.readDouble(), dis.readDouble());


            score = dis.readDouble();
            level = dis.readInt();
            this.lvl = ReadFile.readDiff(dis);
            skipByte += 152;

        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void restart(){

        for (Ghost ghost : ghosts){
            ghost.restart();
        }
        for (MealFX m : meals){
            m.restore();
        }

        map.restart();
        pacman.restart();
        KeyboardController.dir = DIR.STOP;
        pacman.setDirection(DIR.STOP);
        score = 0;
        levelSize = 40 * scale;
    }

    private void saveReplay(String path){

        if (time == 0){
            saveGame("save\\saveReplay.bin");
        } else {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("save\\saveReplay.bin",true)) /*FileManager.openFile("save\\save.bin")*/){

                for (Ghost g : ghosts){
                    WriteToFile.writeDIR(dos, g.getDirection());
                }

                WriteToFile.writeDIR(dos, pacman.getDirection());

            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }

        time++;
    }

    private void playReplay(String path){

        try(DataInputStream dis = new DataInputStream(new FileInputStream(path)))
        {
            dis.skipBytes(skipByte);

            for (Ghost g : ghosts){
               g.setDirection(ReadFile.readDIR(dis));
            }

            pacman.setDirection(ReadFile.readDIR(dis));
            KeyboardController.dir = pacman.getDirection();
            skipByte += 20;


        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }


    public Rect choiceLvl(int frame, int numberGhost){

        if (frame % 200 == 0)   { fakePacman[0] = maxRect.copy().random(); }
        if (frame % 200 == 50)  { fakePacman[1] = maxRect.copy().random(); }
        if (frame % 200 == 100) { fakePacman[2] = maxRect.copy().random(); }
        if (frame % 200 == 150) { fakePacman[3] = maxRect.copy().random(); }


        if (numberGhost == 0 && ((frame % 200 > 50 && lvl == Lvl.EASY)   ||
                                (frame % 200 > 100 && lvl == Lvl.MEDIUM) ||
                                (frame % 200 > 150 && lvl == Lvl.HARD))){
            return fakePacman[0];
        } else if (numberGhost == 1 && (((frame % 200 <= 50 || frame % 200 > 100) && lvl == Lvl.EASY)   ||
                                        ((frame % 200 <= 50 && frame % 200 > 150) && lvl == Lvl.MEDIUM) ||
                                        (frame % 200 < 50 && lvl == Lvl.HARD))) {
            return fakePacman[1];
        } else if (numberGhost == 2 && (((frame % 200 <= 100 || frame % 200 > 150) && lvl == Lvl.EASY)   ||
                                        (frame % 200 <= 100 && lvl == Lvl.MEDIUM) ||
                                        ((frame % 200 >= 50 && frame % 200 < 100) && lvl == Lvl.HARD))){
            return fakePacman[2];
        } else if (numberGhost == 3 && ((frame % 200 <= 150 && lvl == Lvl.EASY)   ||
                                       ((frame % 200 >= 50 && frame % 200 < 150)&& lvl == Lvl.MEDIUM) ||
                                       ((frame % 200 >= 100 && frame % 200 < 150) && lvl == Lvl.HARD))){
            return fakePacman[3];
        }
            return pacman.getDestR();


    }

    public void setLvl(Lvl lvl){
        this.lvl = lvl;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void newGame(){
        main.stopGame();
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
