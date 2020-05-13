package sample;

import javafx.application.Platform;

public class proc extends Thread{

    private Game game;

    proc(Game game){
        this.game = game;
    }

    @Override
    public void run() {
       while(true)
       {
           Platform.runLater(()->{

               game.draw();

           });
       }
    }


}
