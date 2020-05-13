package sample;



import static sample.Animation.animationPacman;


public class Pacman /*implements Person*/ {


    private Rect srcR;
    private Rect destR;

    private DIR direction;
    private DIR newDirection = DIR.STOP;

    private final int size = 10;
    private final int startX = 100;
    private final int startY = 110;

    private int frame = 0;
    private int speedChangePictures = 5;
    private int pictures = 0;

    Pacman() {}

    public void init() {

        direction = DIR.STOP;

        this.destR = new Rect(startX, startY, size, size);
        srcR = new Rect(2.5,2.5,15,15);

    }




    public void update() {

        this.frame++;

        if ((frame % speedChangePictures) == 0) {
            pictures++;
        }


        switch (direction) {
            case UP:    destR.y -= 1; break;
            case DOWN:  destR.y += 1; break;
            case LEFT:  destR.x -= 1; break;
            case RIGHT: destR.x += 1; break;
        }

        animationPacman(srcR, direction, pictures);



        if (frame % 10 == 0){
            direction = newDirection;
        }
    }



    public void stopRun() {

        switch (direction) {

            case UP:        destR.y += 1;   break;
            case DOWN:      destR.y -= 1;   break;
            case LEFT:      destR.x += 1;   break;
            case RIGHT:     destR.x -= 1;   break;
        }
        direction = DIR.STOP;
    }
    public void restart() {

        direction = DIR.STOP;
        this.destR = new Rect(startX, startY, size, size);
    }

    public void setNewDirection(DIR newDirection) {
        this.newDirection = newDirection;
    }

    public Rect getDestR() {
        return this.destR;
    }

    public DIR getDirection() {
        return direction;
    }



    public Rect getSrcR() {
        return this.srcR;
    }

    public void setDirection(DIR direction) {
        this.direction = direction;
    }
}
