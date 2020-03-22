package sample;

class Rect {

    double x;
    double y;
    double h;
    double w;

    Rect() {
        x = y = h = w = 0;
    }

    Rect(final double x, final double y, final double h, final double w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public Rect multiplication(double mul) {


        //Rect r = new Rect(this);

        this.y *= mul;
        this.x *= mul;
        this.w *= mul;
        this.h *= mul;

        return this;
    }

    public Rect(Rect r){

        this.x = r.x;
        this.y = r.y;
        this.w = r.w;
        this.h = r.h;
    }

    public Rect copy() {

        return new Rect(this);
    }
}
