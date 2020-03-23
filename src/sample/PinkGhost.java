package sample;

import java.util.List;

public class PinkGhost implements Ghost {
    private Rect srcR;
    private Rect destR;

    private boolean stop = false;

    private int frame = 0;
    private int frameDelay = 5;

    private int pictures = 0;
    private int speedChangePictures = 5;


    private DIR direction = DIR.RIGHT;

    public void init() {

        this.destR = new Rect(40, 20, 10, 10);
        srcR = new Rect(2.5, 102.5, 15, 15);

    }



    public void update(List<Rect> fancec, Rect destPacman){

        ++frame;




        if (stop && (frame % frameDelay == 0)){
            direction = choiseNewDirection(fancec, destPacman);
        }



        switch (direction) {
            case UP:    this.destR.y -= 2; break;
            case DOWN:  this.destR.y += 2; break;
            case LEFT:  this.destR.x -= 2; break;
            case RIGHT: this.destR.x += 2; break;
        }

        animation();
    }

    private DIR choiseNewDirection(List<Rect> fancec, Rect destPacman) {

        double distanceX = destPacman.x - destR.x;
        double distanceY = destPacman.y - destR.y;

        if (distanceX == 0){
            stop = false;
            if (distanceY > 0) { return DIR.DOWN; }
            else { return DIR.UP;}
        }

        if (distanceY == 0){
            stop = false;

            if(distanceX > 0) {return DIR.RIGHT;}
            else {return DIR.LEFT;}
        }

        return DIR.STOP;
    }

    public void stopRun() {

        switch (direction) {

            case UP:        destR.y += 2;   break;
            case DOWN:      destR.y -= 2;   break;
            case LEFT:      destR.x += 2;   break;
            case RIGHT:     destR.x -= 2;   break;
        }

        stop = true;
    }

    public void animation() {

        if ((this.frame % this.speedChangePictures) == 0) {
            pictures++;
        }

        switch (direction) {

            case UP:        srcR.x = 2.5;    break;
            case DOWN:      srcR.x = 42.5;    break;
            case RIGHT:     srcR.x = 122.5;     break;
            case LEFT:      srcR.x = 82.5;    break;
            case STOP:      return;
        }

        if (pictures % 3 == 1) srcR.x += 20;


    }



    public Rect getSrcR() { return this.srcR; }
    public Rect getDestR() { return this.destR; }

}
