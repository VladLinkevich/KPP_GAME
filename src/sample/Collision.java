package sample;

import java.util.List;

public class Collision {

    public static boolean AABB(final Rect recA, final Rect recB) {
        return recA.x + recA.w > recB.x &&
                recB.x + recB.w > recA.x &&
                recA.y + recA.h > recB.y &&
                recB.y + recB.h > recA.y;

    }

    public static boolean collisionWithFancec(List<Rect> fancec, Rect destR, DIR direction){

        switch (direction) {
            case UP:    destR.y -= 1; break;
            case DOWN:  destR.y += 1; break;
            case LEFT:  destR.x -= 1; break;
            case RIGHT: destR.x += 1; break;
        }

        for (Rect r : fancec) {
            if (Collision.AABB(destR, r)) {
                return true;
            }
        }
        return false;
    }
}
