package sample;

import java.util.List;

import static sample.Animation.animationGhost;

public class PinkGhost implements Ghost {
    private Rect srcR;
    private Rect destR;

    private boolean stop = false;

    private int frame = 0;
    private int pictures = 0;
    private int frameDelay = 5;
    private float pinkColor = 102.5f;

    private DIR direction = DIR.RIGHT;

    public void init() {

        this.destR = new Rect(40, 20, 10, 10);
        srcR = new Rect(2.5, 102.5, 15, 15);

    }



    public void update(List<Rect> fancec, Rect destPacman){

        ++frame;
        if ((frame % speedChangePictures) == 0) {
            pictures++;
        }

        srcR.y = pinkColor;

        if (stop && (frame % frameDelay == 0)){
            direction = choiseNewDirection(fancec, destPacman);
        }



        switch (direction) {
            case UP:    this.destR.y -= 2; break;
            case DOWN:  this.destR.y += 2; break;
            case LEFT:  this.destR.x -= 2; break;
            case RIGHT: this.destR.x += 2; break;
        }

        animationGhost(getSrcR(), direction, pictures);
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





    public Rect getSrcR() { return this.srcR; }
    public Rect getDestR() { return this.destR; }

}
