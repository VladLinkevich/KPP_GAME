package sample;

import javafx.scene.Scene;

public interface Person {

    int scale = 4;

    void init();
    void update(Scene scene);
    void animation();
    void setDirection(DIR direction);
    Rect getDestR();
    DIR getDirection();
    DIR getNewDirection();

}
