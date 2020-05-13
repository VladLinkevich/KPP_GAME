package sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;


public class GhostBehavior {

    private static DIR choiceDir(boolean[] newDir){

        if (newDir[0]) return DIR.UP;
        else  if (newDir[1]) return DIR.DOWN;
        else  if (newDir[2]) return DIR.LEFT;
        else return DIR.RIGHT;
    }

 public static DIR ghostDirectionInOvertake(List<Rect> fancec, Rect destGhost, Rect destPacman, DIR direction){

     double distanceX = destPacman.x - destGhost.x;
     double distanceY = destPacman.y - destGhost.y;

     int clearDir = 0;

     boolean newDir[] = {true, true, true, true}; /* UP = 0
                                                * DOWN = 1
                                                * LEFT = 2
                                                * RIGHT = 3
                                                */


     if (Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP))       { newDir[0] = false; }
     if (Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN))     { newDir[1] = false; }
     if (Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT))     { newDir[2] = false; }
     if (Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT))    { newDir[3] = false; }

     for(boolean d : newDir){                   // проверка если у него только один путь для выхода
         if (d) { clearDir++; }
     }
     if (clearDir == 1)
         return choiceDir(newDir);

     clearDir = 0;

     switch (direction){

         case LEFT:     newDir[3] = false;  break;
         case RIGHT:    newDir[2] = false;  break;
         case UP:       newDir[1] = false;  break;
         case DOWN:     newDir[0] = false;  break;

     }

     for(boolean d : newDir){                   // если мы находимся в танеле
         if (d) { clearDir++; }
     }
     if (clearDir == 1)
         return choiceDir(newDir);

     if (distanceX >= 0 && distanceY > 0) {
         if (distanceX > distanceY) {
             if (newDir[3]) return DIR.RIGHT;
             else if (newDir[1]) return DIR.DOWN;
             else if (newDir[0]) return  DIR.UP;
             else return DIR.LEFT;
         }else{
             if (newDir[1]) return DIR.DOWN;
             else if (newDir[3]) return DIR.RIGHT;
             else if (newDir[0]) return  DIR.LEFT;
             else return DIR.UP;
         }
     }else if (distanceX > 0 && distanceY <= 0){
         if (distanceX + distanceY > 0) {
             if (newDir[3]) return DIR.RIGHT;
             else if (newDir[0]) return DIR.UP;
             else if (newDir[1]) return  DIR.DOWN;
             else return DIR.LEFT;
         }else{
             if (newDir[0]) return DIR.UP;
             else if (newDir[3]) return DIR.RIGHT;
             else if (newDir[2]) return  DIR.LEFT;
             else return DIR.DOWN;
         }
     } else if (distanceX <= 0 && distanceY < 0){
         if (distanceX < distanceY) {
             if (newDir[2]) return DIR.LEFT;
             else if (newDir[0]) return DIR.UP;
             else if (newDir[1]) return  DIR.DOWN;
             else return DIR.RIGHT;
         }else{
             if (newDir[0]) return DIR.UP;
             else if (newDir[2]) return DIR.LEFT;
             else if (newDir[3]) return  DIR.RIGHT;
             else return DIR.DOWN;
         }
     } else if (distanceX < 0 && distanceY >= 0){
         if (distanceX + distanceY < 0) {
             if (newDir[2]) return DIR.LEFT;
             else if (newDir[1]) return DIR.DOWN;
             else if (newDir[0]) return  DIR.UP;
             else return DIR.RIGHT;
         }else{
             if (newDir[1]) return DIR.DOWN;
             else if (newDir[2]) return DIR.LEFT;
             else if (newDir[3]) return  DIR.RIGHT;
             else return DIR.UP;
         }
     }

     return DIR.DOWN;
 }

    public static DIR ghostDirectionInFear(List<Rect> fancec, Rect destGhost, Rect destPacman, DIR direction) {
        double distanceX = destPacman.x - destGhost.x;
        double distanceY = destPacman.y - destGhost.y;

        int clearDir = 0;
        Map<DIR, Boolean> dirBooleanMap = new HashMap<>();
        boolean newDir[] = {true, true, true, true}; /* UP = 0
         * DOWN = 1
         * LEFT = 2
         * RIGHT = 3
         */


        if (Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP))       { newDir[0] = false; }
        if (Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN))     { newDir[1] = false; }
        if (Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT))     { newDir[2] = false; }
        if (Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT))    { newDir[3] = false; }

        for(boolean d : newDir){                   // проверка если у него только один путь для выхода
            if (d) { clearDir++; }
        }
        if (clearDir == 1)
            return choiceDir(newDir);

        clearDir = 0;

        switch (direction){

            case LEFT:     newDir[3] = false;  break;
            case RIGHT:    newDir[2] = false;  break;
            case UP:       newDir[1] = false;  break;
            case DOWN:     newDir[0] = false;  break;

        }

        double d;
        int vector;

        while (true) {
            d = Math.random() * 4;
            vector = (int) d;

            if (vector == 0 && newDir[vector]) return DIR.UP;
            if (vector == 1 && newDir[vector]) return DIR.DOWN;
            if (vector == 2 && newDir[vector]) return DIR.LEFT;
            if (vector == 3 && newDir[vector]) return DIR.RIGHT;
        }

    }

    public static DIR directionReverse(DIR dir){

        switch (dir){
            case RIGHT: return DIR.LEFT;
            case LEFT:  return DIR.RIGHT;
            case UP:    return DIR.DOWN;
            case DOWN:  return DIR.UP;
        }

        return DIR.STOP;
    }

}
