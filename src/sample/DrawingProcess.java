package sample;

import javafx.application.Platform;

public class DrawingProcess extends Thread {

    private Game game;
    private boolean isWork;

    public void init(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        while (true){

            Platform.runLater(()->{
                if (isWork){
                    game.draw();
                }
            });
            try {
                sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setWork(boolean work) {
        this.isWork = work;
    }
}
