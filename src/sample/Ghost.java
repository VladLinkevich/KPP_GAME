package sample;

import java.util.List;

import static sample.Animation.animationGhost;

public class Ghost {

        private Rect startSrc;
        private Rect startDest;
        private Rect srcR;
        private Rect destR;

        private boolean stop = true;

        private int frame = 0;

        private int pictures = 0;
        private int speedChangePictures = 5;
        private boolean replay;

        private DIR direction = DIR.RIGHT;

        public void init(Rect srcR, Rect destR) {

            this.startSrc = new Rect(srcR);
            this.startDest = new Rect(destR);

            this.srcR = new Rect(srcR);
            this.destR = new Rect(destR);

        }



        public void update(List<Rect> fancec, Rect destPacman){

            ++frame;
            if ((frame % speedChangePictures) == 0) {
                pictures++;
            }
            srcR.y = startSrc.y;
            if (!replay) {
    if ((direction == DIR.UP || direction == DIR.DOWN) && frame % 10 == 0 && !stop) {
        if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.RIGHT) ||
                !Collision.collisionWithFancec(fancec, destR.copy(), DIR.LEFT)) {

            stop = true;
        }
    }

    if ((direction == DIR.LEFT || direction == DIR.RIGHT) && frame % 10 == 0 && !stop) {
        if (!Collision.collisionWithFancec(fancec, destR.copy(), DIR.UP) ||
                !Collision.collisionWithFancec(fancec, destR.copy(), DIR.DOWN)) {

            stop = true;
        }
    }


    if (stop && (frame % 10 == 0)) {
        if (!Animation.getFear())
            direction = GhostBehavior.ghostDirectionInOvertake(fancec, this.destR, destPacman, direction);
        else direction = GhostBehavior.ghostDirectionInFear(fancec, this.destR, destPacman, direction);
        // stop = false;
    }
}


            switch (direction) {
                case UP:    this.destR.y -= 1; break;
                case DOWN:  this.destR.y += 1; break;
                case LEFT:  this.destR.x -= 1; break;
                case RIGHT: this.destR.x += 1; break;
            }

            animationGhost(getSrcR(), direction, pictures);
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


        public void restart() {

            direction = DIR.STOP;
            this.destR = new Rect(startDest);

        }

    public void setReplay(boolean replay){
        this.replay = replay;
    }
    public void setDirection(DIR direction) {
        this.direction = direction;
    }

    public void setCordinate(double x, double y) {
        this.destR.x = x;
        this.destR.y = y;
    }

    public DIR getDirection() { return direction; }
    public Rect getSrcR() { return this.srcR; }
    public Rect getDestR() { return this.destR; }




}
