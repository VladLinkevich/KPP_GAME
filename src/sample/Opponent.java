package sample;

public class Opponent {



    private Rect srcR;
    private Rect destR;

    private int scale;

    private DIR direction = DIR.LEFT;

    public void init(final int scale) {

        this.destR = new Rect(110, 110, 100, 100);
        srcR = new Rect(0, 100, 18, 18);
        this.scale = scale;



    }

    public Rect getDestR() {
        return this.destR;
    }

    public Rect getSrcR() { return this.srcR; }


    }
