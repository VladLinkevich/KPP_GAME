package sample;

import java.util.List;

public interface Ghost {

    int speedChangePictures = 5;


    void init();
    void update(List<Rect> fancec, Rect destPacman);
    void stopRun();


    Rect getSrcR();
    Rect getDestR();

}
