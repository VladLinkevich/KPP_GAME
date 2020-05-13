package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private final int FPS = 60;
    private final int frameDelay = 1000000000 / FPS;
    private Game game = null;
    private Menu menu;
    private AnimationTimer at;
    proc p;

    @Override
    public void start(Stage primaryStage) throws Exception {




        game = new Game();
        game.init(this, primaryStage, 200, 200);

        menu = new Menu();
        menu.init(primaryStage, this);

        //game.init(primaryStage, 200, 200);


        at = new AnimationTimer(){
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    try {
                        update();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                if (now - lastTick > frameDelay) {
                    lastTick = now;
                    try {
                        update();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        };




    }

    @Override
    public void stop() throws Exception {

        super.stop();
    }

    public Game getGame() {
        return game;
    }

    public void startGame(){


        game.restart();
        game.setLevel(1);
        at.start();
        game.startScrene();
    }

    public void stopGame(){

        at.stop();
        menu.startScrene();
    }


    private void update() throws IOException {

            game.draw();
            game.update();

    }

    public void setLvl(Lvl lvl){
        game.setLvl(lvl);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
