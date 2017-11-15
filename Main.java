import Models.DatabaseConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Pane root;
    @Override
    public void start(Stage stage) throws Exception{
        startDeckS(stage);
    }

    public static void startDeckS(Stage stage) {
        root = DeckS.launchDeckS(stage);

        Scene scene = new Scene(root, 768, 900);

        stage.setTitle("Jesse Ellis's Flashcard Program");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }




    public static void main(String[] args){
        DatabaseConnection db = new DatabaseConnection("");
        launch(args);
    }



    public static void startDeckC(Stage stage) {
        root = DeckC.launchDeckC(stage);

        Scene scene = new Scene(root, 768, 900);

        stage.setTitle("Jesse Ellis's Flashcard Program");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void startCardV(Stage stage){
        root = CardV.launchCardV(stage);

        Scene scene = new Scene(root, 768, 900);

        stage.setTitle("Jesse Ellis's Flashcard Program");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void startCardC(Stage stage){
        root = CardC.launchCardC(stage);

        Scene scene = new Scene(root, 768, 900);

        stage.setTitle("Jesse Ellis's Flashcard Program");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void placeholder() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("This feature has not been implemented yet.");

        alert.showAndWait();
    }
}