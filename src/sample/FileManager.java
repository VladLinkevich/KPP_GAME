package sample;

import java.io.*;

public class FileManager {

    public static BufferedReader readFile(String path) {
        try {
            return new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("Error FileReader in FileManager");
        }
        return  null;
    }

    public static PrintWriter fileWriter(){
        try {
            File file = new File("sprite\\save.txt");
            if(file.exists())
                file.createNewFile();
            return new PrintWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
