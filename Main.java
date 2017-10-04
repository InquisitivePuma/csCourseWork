import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();


        Scene scene = new Scene(root, 720, 720);
        stage.setResizable(false);
        stage.setTitle("Hello World");
        stage.setScene(scene);
        HBox topBar = new HBox(10);

        //top bar
        Button[] topBarChildren = new Button[4];

        topBarChildren[0] = new Button("X cards in current deck");
        topBarChildren[0].setPrefSize(180, 40);
        topBarChildren[0].setOnAction((ActionEvent ae) -> doSomething(ae));

        topBarChildren[1] = new Button("Add to favorites.");
        topBarChildren[1].setPrefSize(150, 40);
        topBarChildren[1].setOnAction((ActionEvent ae) -> doSomething(ae));

        topBarChildren[2] = new Button("Edit");
        topBarChildren[2].setPrefSize(100, 40);
        topBarChildren[2].setOnAction((ActionEvent ae) -> doSomething(ae));

        topBarChildren[3] = new Button("Bin");
        topBarChildren[3].setPrefSize(100, 40);
        topBarChildren[3].setOnAction((ActionEvent ae) -> doSomething(ae));

        topBar.getChildren().addAll(topBarChildren);
        root.getChildren().add(topBar);


        //doing positions
        topBar.setAlignment(Pos.TOP_CENTER);
        BorderPane.setAlignment(topBar, Pos.TOP_CENTER);

        //proper termination
        stage.setOnCloseRequest((WindowEvent kill) -> System.exit(0));

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void doSomething(ActionEvent ae){
        //joke's on you, this doesn't do anything.
    }
}
