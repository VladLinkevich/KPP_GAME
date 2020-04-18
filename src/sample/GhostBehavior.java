package sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GhostBehavior {

 public static DIR ghostDirectionInOvertake(List<Rect> fancec, Rect destGhost, Rect destPacman, DIR direction){

     double distanceX = destPacman.x - destGhost.x;
     double distanceY = destPacman.y - destGhost.y;

     boolean dir[] = new boolean[4];



     switch (direction){

         case LEFT: break;
         case RIGHT: break;
         case UP: break;
         case DOWN: break;
     }

     if (distanceX >= 0 && distanceY > 0) {
         if (distanceX > distanceY) {
             if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT) && direction != DIR.LEFT)          return DIR.RIGHT;   //
             else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN) && direction != DIR.UP)        return DIR.DOWN;    //  O
             else if (direction != DIR.LEFT)                                                                            return DIR.LEFT;    //     P
             else                                                                                                       return DIR.UP;     //
         } else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN) && direction != DIR.UP)          return DIR.DOWN;
         else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT) && direction != DIR.LEFT)         return DIR.RIGHT;
         else if (direction != DIR.UP)                                                                                  return DIR.UP;
         else                                                                                                           return DIR.LEFT;
     }

     if (distanceX > 0 && distanceY <= 0) {
         if (distanceX + distanceY > 0) {
             if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT) && direction != DIR.LEFT)          return DIR.RIGHT;   //    P
             else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP) && direction != DIR.DOWN)        return DIR.UP;   // O
             else if (direction != DIR.DOWN)                                                                            return DIR.DOWN;
             else                                                                                                       return DIR.LEFT;
         } else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP) && direction != DIR.DOWN)          return DIR.UP;
         else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT) && direction != DIR.LEFT)         return DIR.RIGHT;
         else if (direction != DIR.LEFT)                                                                                return DIR.LEFT;
         else                                                                                                           return DIR.DOWN;
     }


     if (distanceX <= 0 && distanceY < 0) {
         if (distanceX < distanceY) {
             if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT) && direction != DIR.RIGHT)          return DIR.LEFT;   // P
             else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP) && direction != DIR.DOWN)        return DIR.UP;     //    O
             else if (direction != DIR.RIGHT)                                                                           return DIR.RIGHT;
             else                                                                                                       return DIR.DOWN;
         } else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP) && direction != DIR.DOWN)          return DIR.UP;
         else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT) && direction != DIR.RIGHT)         return DIR.LEFT;
         else if (direction != DIR.DOWN)                                                                                return DIR.DOWN;
         else                                                                                                           return DIR.RIGHT;
     }

     if (distanceX < 0 && distanceY >= 0) {
         if (distanceX + distanceY < 0) {
             if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT) && direction != DIR.RIGHT)          return DIR.LEFT;   //    O
             else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN) && direction != DIR.UP)        return DIR.DOWN;   // P
             else if (direction != DIR.RIGHT)                                                                           return DIR.RIGHT;
             else                                                                                                       return DIR.UP;
         } else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN) && direction != DIR.UP)          return DIR.DOWN;
         else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT) && direction != DIR.RIGHT)         return DIR.LEFT;
         else if (direction != DIR.UP)                                                                                  return DIR.UP;
         else                                                                                                           return DIR.RIGHT;
     }
     return DIR.DOWN;
 }

    public static DIR ghostDirectionInFear(List<Rect> fancec, Rect destGhost, Rect destPacman, DIR direction) {
        double distanceX = destPacman.x - destGhost.x;
        double distanceY = destPacman.y - destGhost.y;

        if (distanceX >= 0 && distanceY > 0) {
            if (distanceX > distanceY) {
                if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT) && direction != DIR.RIGHT)       return DIR.LEFT;   //
                else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP)&& direction != DIR.DOWN )     return DIR.UP;       //  O
                else if (direction != DIR.RIGHT)                                                                        return DIR.RIGHT;    //     P
                else                                                                                                    return DIR.UP;     //
            } else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP)&& direction != DIR.DOWN )       return DIR.UP;
            else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT) && direction != DIR.RIGHT )     return DIR.LEFT;
            else if (direction != DIR.DOWN)                                                                             return DIR.DOWN;
            else                                                                                                        return DIR.RIGHT;
        }

        if (distanceX > 0 && distanceY <= 0) {
            if (distanceX + distanceY > 0) {
                if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT) && direction != DIR.RIGHT )      return DIR.LEFT;   //    P
                else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN) && direction != DIR.UP )    return DIR.DOWN;   // O
                else if (direction != DIR.UP)                                                                           return DIR.UP;
                else                                                                                                    return DIR.RIGHT;
            } else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN ) && direction != DIR.UP )     return DIR.DOWN;
            else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.LEFT) && direction != DIR.RIGHT )     return DIR.LEFT;
            else if (direction != DIR.RIGHT)                                                                            return DIR.RIGHT;
            else                                                                                                        return DIR.UP;
        }


        if (distanceX <= 0 && distanceY < 0) {
            if (distanceX < distanceY) {
                if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT) && direction != DIR.LEFT)       return DIR.RIGHT;    // P
                else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN) && direction != DIR.UP )    return DIR.DOWN;     //    O
                else if (direction != DIR.LEFT)                                                                         return DIR.LEFT;
                else                                                                                                    return DIR.UP;
            } else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.DOWN) && direction != DIR.UP )      return DIR.DOWN;
            else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT) && direction != DIR.LEFT)      return DIR.RIGHT;
            else if (direction != DIR.UP)                                                                               return DIR.UP;
            else                                                                                                        return DIR.LEFT;
        }

        if (distanceX < 0 && distanceY >= 0) {
            if (distanceX + distanceY < 0) {
                if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT) && direction != DIR.LEFT )      return DIR.RIGHT;   //    O
                else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP) && direction != DIR.DOWN )    return DIR.UP;      // P
                else if (direction != DIR.LEFT)                                                                         return DIR.LEFT;
                else                                                                                                    return DIR.UP;
            } else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.UP) && direction != DIR.DOWN )      return DIR.UP;
            else if (!Collision.collisionWithFancec(fancec, destGhost.copy(), DIR.RIGHT) && direction != DIR.LEFT )     return DIR.RIGHT;
            else if (direction != DIR.DOWN)                                                                             return DIR.DOWN;
            else                                                                                                        return DIR.LEFT;
        }
        return DIR.UP;
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
