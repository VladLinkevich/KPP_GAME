package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public final int FPS = 60;
    final int frameDelay = 1000000000 / FPS;
    Game game = null;


    @Override
    public void start(Stage primaryStage) throws Exception {


        game = new Game();
        game.init(primaryStage, 200, 200);


        new AnimationTimer() {
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

        }.start();


    }


    public void update() {
        game.draw();
        game.update();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
