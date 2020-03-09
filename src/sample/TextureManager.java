package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//import javax.swing.text.html.ImageView;

public class TextureManager {


    public static Image LoadTexture(String path) {
        try {

            FileInputStream inputStream = new FileInputStream(path);
            return new Image(inputStream);

        } catch (FileNotFoundException e) {
            System.out.println("Error LoadTexture in TextureManager");
        }
        return null;
    }

    public static void DrawTexture(Image img, Rect srcR, Rect destR) {
        Game.getGC().drawImage(img, srcR.x, srcR.y, srcR.w, srcR.h, destR.x, destR.y, destR.w, destR.h);

    }
}
