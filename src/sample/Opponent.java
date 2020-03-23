package sample;

import java.util.List;

public class Opponent {



    private Rect srcR;
    private Rect destR;

    private boolean stop = true;

    private int frame = 0;

    private int pictures = 0;
    private int speedChangePictures = 5;


    private DIR direction = DIR.RIGHT;

    public void init() {

        this.destR = new Rect(10, 10, 10, 10);
        srcR = new Rect(2.5, 82.5, 15, 15);




    }



    public void update(List<Rect> fancec, Rect destPacman){

        ++frame;


        if ((direction == DIR.UP || direction == DIR.DOWN) && frame % 10 == 0 && !stop){
            if(!Collision.collisionWithFancec(fancec, destR.copy(), DIR.RIGHT) ||
               !Collision.collisionWithFancec(fancec, destR.copy(), DIR.LEFT)){

                stop = true;
            }
        }

        if ((direction == DIR.LEFT || direction == DIR.RIGHT) && frame % 10 == 0 && !stop){
            if(!Collision.collisionWithFancec(fancec, destR.copy(), DIR.UP) ||
               !Collision.collisionWithFancec(fancec, destR.copy(), DIR.DOWN)){

                stop = true;
            }
        }


        if (stop && (frame % 10 == 0)){
            direction = choiseNewDirection(fancec, destPacman);
            stop = false;
        }



        switch (direction) {
            case UP:    this.destR.y -= 1; break;
            case DOWN:  this.destR.y += 1; break;
            case LEFT:  this.destR.x -= 1; break;
            case RIGHT: this.destR.x += 1; break;
        }

        animation();
    }

    private DIR choiseNewDirection(List<Rect> fancec, Rect destPacman) {

        double distanceX = destPacman.x - destR.x;
        double distanceY = destPacman.y - destR.y;

        if (distanceX >= 0 && distanceY > 0) {
            if (distanceX > distanceY) {
                if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.RIGHT) && direction != DIR.LEFT)       return DIR.RIGHT;   //
                else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.DOWN) && direction != DIR.UP)     return DIR.DOWN;    //  O
                else if (direction != DIR.LEFT)                                                                     return DIR.LEFT;    //     P
                else                                                                                                return DIR.UP;     //
            } else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.DOWN) && direction != DIR.UP)       return DIR.DOWN;
            else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.RIGHT) && direction != DIR.LEFT)      return DIR.RIGHT;
            else if (direction != DIR.UP)                                                                           return DIR.UP;
            else                                                                                                    return DIR.LEFT;
        }

        if (distanceX > 0 && distanceY <= 0) {
            if (distanceX + distanceY > 0) {
                if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.RIGHT) && direction != DIR.LEFT)       return DIR.RIGHT;   //    P
                else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.UP) && direction != DIR.DOWN)     return DIR.UP;   // O
                else if (direction != DIR.DOWN)                                                                     return DIR.DOWN;
                else                                                                                                return DIR.LEFT;
            } else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.UP) && direction != DIR.DOWN)       return DIR.UP;
            else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.RIGHT) && direction != DIR.LEFT)      return DIR.RIGHT;
            else if (direction != DIR.LEFT)                                                                         return DIR.LEFT;
            else                                                                                                    return DIR.DOWN;
        }


        if (distanceX <= 0 && distanceY < 0) {
            if (distanceX < distanceY) {
                if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.LEFT) && direction != DIR.RIGHT)       return DIR.LEFT;   // P
                else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.UP) && direction != DIR.DOWN)     return DIR.UP;     //    O
                else if (direction != DIR.RIGHT)                                                                    return DIR.RIGHT;
                else                                                                                                return DIR.DOWN;
            } else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.UP) && direction != DIR.DOWN)       return DIR.UP;
            else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.LEFT) && direction != DIR.RIGHT)      return DIR.LEFT;
            else if (direction != DIR.DOWN)                                                                         return DIR.DOWN;
            else                                                                                                    return DIR.RIGHT;
        }

        if (distanceX < 0 && distanceY >= 0) {
            if (distanceX + distanceY < 0) {
                if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.LEFT) && direction != DIR.RIGHT)       return DIR.LEFT;   //    O
                else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.DOWN) && direction != DIR.UP)     return DIR.DOWN;   // P
                else if (direction != DIR.RIGHT)                                                                    return DIR.RIGHT;
                else                                                                                                return DIR.UP;
            } else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.DOWN) && direction != DIR.UP)       return DIR.DOWN;
            else if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.LEFT) && direction != DIR.RIGHT)      return DIR.LEFT;
            else if (direction != DIR.UP)                                                                           return DIR.UP;
            else                                                                                                    return DIR.RIGHT;
        }
        return DIR.DOWN;
    }

    public void stopRun() {

        switch (direction) {

            case UP:        destR.y += 1;   break;
            case DOWN:      destR.y -= 1;   break;
            case LEFT:      destR.x += 1;   break;
            case RIGHT:     destR.x -= 1;   break;
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
            case RIGHT:      srcR.x = 122.5;     break;
            case LEFT:     srcR.x = 82.5;    break;
        }

        if (pictures % 3 == 1) srcR.x += 20;


    }



    public Rect getSrcR() { return this.srcR; }
    public Rect getDestR() { return this.destR; }

    }
