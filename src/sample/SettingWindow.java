package sample;

import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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



public class SettingWindow {

    private Menu menu;
    private VBox box;
    private Canvas c;
    private GraphicsContext gc;
    private Stage primaryStage;
    private Scene scene;
    private Image image;
    private Pane root;


    void init(Stage primaryStage, Menu menu){


        this.primaryStage = primaryStage;

        this.menu = menu;
        root = new Pane();
        box = new VBox();
        image = TextureManager.loadTexture("sprite\\setting.jpg");
        ImageView img = new ImageView(image);
        img.setFitHeight(525);
        img.setFitWidth(790);
        root.getChildren().add(img);

        root.getChildren().addAll(box);

        StackPane easy = addButton(box,"Easy", 50, 100);
        StackPane medium = addButton(box,"Medium", 150, 100);
        StackPane hard = addButton(box,"Hard", 250, 100);


        scene = new Scene(root, 790, 525);

        easy.setOnMouseClicked(event -> {menu.setLvl(Lvl.EASY);
            menu.startScrene();});
        medium.setOnMouseClicked(event -> {menu.setLvl(Lvl.MEDIUM);
            menu.startScrene();});
        hard.setOnMouseClicked(event -> {menu.setLvl(Lvl.HARD);
            menu.startScrene();});

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
            if (key.getCode() == KeyCode.ESCAPE) menu.startScrene();
        });

        Text text = new Text("Hello");
        box.getChildren().addAll(text);


    }

    public void startScrene(){

        primaryStage.setScene(scene);
        primaryStage.setTitle("pause");
        primaryStage.show();
    }

    private StackPane addButton(VBox box, String str, int width, int height){

        StackPane sp = new StackPane();

        Rectangle bg = new Rectangle( 175, 50, Color.color(0,0,0,0));

        Text text = new Text(str);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD,28));

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

        box.setTranslateX(height);
        box.setTranslateY(width);

        box.getChildren().addAll(sp);

        return sp;

    }
}
