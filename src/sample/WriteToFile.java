package sample;

import java.io.DataOutputStream;
import java.io.IOException;

public class WriteToFile {

    public static void writeDIR(DataOutputStream dos, DIR dir){

        int number = 0;

        if (dir == DIR.STOP)        { number = 0;}
        else if (dir == DIR.LEFT)   { number = 1;}
        else if (dir == DIR.RIGHT)  { number = 2;}
        else if (dir == DIR.UP)     { number = 3;}
        else if (dir == DIR.DOWN)   { number = 4;}

        try {
            dos.writeInt(number);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeRect(DataOutputStream dos, Rect rect){

        try {
            dos.writeDouble(rect.x);
            dos.writeDouble(rect.y);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeDiff(DataOutputStream dos, Lvl lvl){

        int diff = 0;

        if (lvl == Lvl.EASY) {diff = 0;}
        else if (lvl == Lvl.MEDIUM) {diff = 1;}
        else if (lvl == Lvl.HARD) {diff = 2;}

        try {
            dos.writeInt(diff);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
