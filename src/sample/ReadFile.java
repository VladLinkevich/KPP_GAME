package sample;

import java.io.DataInputStream;
import java.io.IOException;

public class ReadFile {

    public static DIR readDIR(DataInputStream dis){

        try {
            int number = dis.readInt();

            switch (number){
                case 0: return DIR.STOP;
                case 1: return DIR.LEFT;
                case 2: return DIR.RIGHT;
                case 3: return DIR.UP;
                case 4: return DIR.DOWN;
            }


        } catch (IOException e) {
           // e.printStackTrace();
        }

        return null;
    }

    public static Lvl readDiff(DataInputStream dis){
        try {
            int number = dis.readInt();

            switch (number){
                case 0: return Lvl.EASY;
                case 1: return Lvl.MEDIUM;
                case 2: return Lvl.HARD;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
