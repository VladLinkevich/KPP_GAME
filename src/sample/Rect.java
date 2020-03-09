package sample;

public class Rect {

    public double x;
    public double y;
    public double h;
    public double w;

    Rect() {
        x = y = h = w = 0;
    }

    Rect(final double x, final double y, final double h, final double w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }
}
