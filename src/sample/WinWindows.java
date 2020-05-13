package sample;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class WinWindows {

    private Scene scene;
    private Stage primaryStage;
    private Image image;
    private VBox box;
    private Pane root;
    private Game game;

    void init(Stage primaryStage, Game game){

        this.game = game;
        root = new Pane();
        box = new VBox();
        this.primaryStage = primaryStage;
        image = TextureManager.loadTexture("sprite\\win.jpg");
        ImageView img = new ImageView(image);
        img.setFitHeight(525);
        img.setFitWidth(790);
        root.getChildren().add(img);
        root.getChildren().addAll(box);

        scene = new Scene(root, 790, 525);



    }

    public void startScrene(){

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key->{
            game.newGame();
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("pause");
        primaryStage.show();
    }

}
