package sample;

import javafx.application.Platform;

public class DrawingProcess extends Thread {

    @Override
    public void run() {
        while (true){
            Platform.runLater(()->{


            });
        }
    }
}
