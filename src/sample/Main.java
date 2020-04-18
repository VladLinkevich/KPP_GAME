package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private final int FPS = 60;
    private final int frameDelay = 1000000000 / FPS;
    private Game game = null;
    private Menu menu;
    private AnimationTimer at;


    @Override
    public void start(Stage primaryStage) throws Exception {


        game = new Game();
        game.init(primaryStage, 200, 200);

        menu = new Menu();
        menu.init(primaryStage, this);

        //game.init(primaryStage, 200, 200);


        at = new AnimationTimer(){
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    update();

                }

                if (now - lastTick > frameDelay) {
                    lastTick = now;
                    update();
                }
            }

        };



    }

    public void startGame(){

        at.start();
        game.startScrene();
    }

    public void stopGame(){
        at.stop();
        menu.startScrene();
    }


    public void update() {
        game.draw();
        game.update();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
