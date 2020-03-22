package sample;

public class Collision {

    public static boolean AABB(final Rect recA, final Rect recB) {
        return recA.x + recA.w > recB.x &&
                recB.x + recB.w > recA.x &&
                recA.y + recA.h > recB.y &&
                recB.y + recB.h > recA.y;

    }
}
