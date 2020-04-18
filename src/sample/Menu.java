package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.TextureManager;


import static java.awt.SystemColor.menu;

public class Menu {

    private VBox box;
    private Canvas c;
    private GraphicsContext gc;
    private Stage primaryStage;
    private Scene scene;
    private Image image;
    private Pane root;
    private Game game = null;

    public static void main(String[] args) {

    }
    public void init(Stage primaryStage, Main main){

        this.primaryStage = primaryStage;

        root = new Pane();
        box = new VBox();
        image = TextureManager.loadTexture("sprite\\orig.gif");
        ImageView img = new ImageView(image);
        img.setFitHeight(525);
        img.setFitWidth(790);
        root.getChildren().add(img);

        root.getChildren().addAll(box);


        StackPane newGame = addButton("New Game");
        StackPane continueGame =  addButton("Continue");

        box.setTranslateX(195);
        box.setTranslateY(50);

        box.getChildren().addAll(newGame, continueGame);


        newGame.setOnMouseClicked(event -> { main.startGame(); });

        scene = new Scene(root, 790, 525);


        primaryStage.setScene(scene);
        primaryStage.setTitle("pause");
        primaryStage.show();


    }

    public void startScrene(){

        primaryStage.setScene(scene);
        primaryStage.setTitle("pause");
        primaryStage.show();
    }

    private StackPane addButton(String str){

        StackPane sp = new StackPane();

        Rectangle bg = new Rectangle(400,40,Color.color(0,0,0,0));

        Text text = new Text(str);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD,28));

        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(bg,text);
        FillTransition st = new FillTransition(Duration.seconds(0.5),bg);
        sp.setOnMouseEntered(event -> {
            st.setFromValue(Color.color(0,0,0,0));
            st.setToValue(Color.BLUE);
            st.setAutoReverse(true);
            st.play();
        });
        sp.setOnMouseExited(event -> {
            st.stop();
            bg.setFill(Color.color(0,0,0,0));
        });

        return sp;

    }



}

