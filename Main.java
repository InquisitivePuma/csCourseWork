import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Pane root;

    @Override
    public void start/*deckS*/(Stage stage) throws Exception {

        root = DeckS.deckS();

        Scene scene = new Scene(root, 768, 900);

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}