package sample;

import java.util.List;

public interface Ghost {

    void init();
    void update(List<Rect> fancec, Rect destPacman);
    void stopRun();


    Rect getSrcR();
    Rect getDestR();

}
