package sample;

import java.io.*;

public class FileManager {

    public static DataOutputStream openFile(String path){
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(path)))
        {
            return dos;
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return  null;
    }


}
