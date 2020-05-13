package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingWindow {

    private VBox box;
    private Canvas c;
    private GraphicsContext gc;
    private Stage primaryStage;
    private Scene scene;
    private Image image;
    private Pane root;


    void init(Stage primaryStage, Main main){


        this.primaryStage = primaryStage;

        root = new Pane();
        box = new VBox();
        image = TextureManager.loadTexture("sprite\\setting.jpg");
        ImageView img = new ImageView(image);
        img.setFitHeight(525);
        img.setFitWidth(790);
        root.getChildren().add(img);

        root.getChildren().addAll(box);


        scene = new Scene(root, 790, 525);
    }

    public void startScrene(){

        primaryStage.setScene(scene);
        primaryStage.setTitle("pause");
        primaryStage.show();
    }
}
