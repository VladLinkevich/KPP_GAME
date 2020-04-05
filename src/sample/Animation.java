package sample;

import static sample.DIR.*;


public class Animation {



    private static boolean fear = false;
    private static boolean endFear = false;

    public static void animationGhost(Rect srcR, DIR direction, int pictures) {

        if(fear){

            srcR.y = 162.5;

            if(pictures % 2 == 0){ srcR.x = 2.5; }
            else {srcR.x = 22.5; }
            if (endFear)
            if(pictures % 4 == 2){ srcR.x = 42.5;}
            else if (pictures % 4 == 3) { srcR.x = 62.5; }
        }
        else {


            switch (direction) {

                case UP:        srcR.x = 2.5;       break;
                case DOWN:      srcR.x = 42.5;      break;
                case RIGHT:     srcR.x = 122.5;     break;
                case LEFT:      srcR.x = 82.5;      break;
                case STOP:                          return;
            }

            if (pictures % 3 == 1) srcR.x += 20;
        }

    }

    public static void animationPacman(Rect srcR, DIR direction, int pictures){

        switch (direction) {

            case UP:        srcR.y = 42.5;    break;
            case DOWN:      srcR.y = 62.5;    break;
            case LEFT:      srcR.y = 2.5;     break;
            case RIGHT:     srcR.y = 22.5;    break;
        }

        if (pictures % 3 == 0) srcR.x = 2.5;

        if (pictures % 3 == 1) srcR.x = 22.5;

        if (pictures  % 3 == 2) {

            srcR.x = 42.5;
            srcR.y = 2.5;
        }
    }

    public static boolean getFear() { return fear; }
    public static void setFear(boolean fear) { Animation.fear = fear; }
    public static boolean getEndFear() { return endFear; }
    public static void setEndFear(boolean endFear) { Animation.endFear = endFear; }
}
