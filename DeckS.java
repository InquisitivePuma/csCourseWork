import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DeckS extends Main{
private static Stage mainstage;
    public static Pane launchDeckS(Stage stage) {
        mainstage =stage;
        Pane root = new Pane();

        //making a new Hbox for top menu buttons
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(15));
        //array of the buttons to go in the Hbox
        Button[] myButtons = new Button[4];

        myButtons[0] = new Button("No deck selected");
        myButtons[0].setPrefSize(180, 60);
        myButtons[0].setOnAction((ActionEvent ae) -> Main.placeholder());

        myButtons[1] = new Button("Add to favorites");
        myButtons[1].setPrefSize(180, 60);
        myButtons[1].setOnAction((ActionEvent ae) -> Main.placeholder());

        myButtons[2] = new Button("Edit");
        myButtons[2].setPrefSize(180, 60);
        myButtons[2].setOnAction((ActionEvent ae) -> Main.placeholder());

        myButtons[3] = new Button("Bin");
        myButtons[3].setPrefSize(180, 60);
        myButtons[3].setOnAction((ActionEvent ae) -> Main.placeholder());

        topBar.getChildren().addAll(myButtons);
        root.getChildren().add(topBar);

        //gridpane for the decks
        GridPane body = new GridPane();
        body.setHgap(10);
        body.setVgap(10);
        body.setPadding(new Insets(20));
        //filling gridpane with buttons

        int noOfDecks = DeckService.noOfDecks();
        int height = (noOfDecks/6);
        int lastWidth = (noOfDecks%6);
        int pages = 0;
        Button[][] bodyButtons = new Button[4][6];
        while ((height>4) || ((height==4) && (lastWidth==0))) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 5; y++) {
                    bodyButtons[x][y] = new Button(DeckService.selectDeckName(((5 * x) + y)+(24*pages)));
                    bodyButtons[x][y].setPrefSize(240, 120);
                    bodyButtons[x][y].setOnAction((ActionEvent ae) -> Main.startCardV(mainstage));
                    body.add(bodyButtons[x][y], x, y);
                }
            }
            noOfDecks -= 24;
            height = (noOfDecks/6);
            lastWidth = (noOfDecks%6);
            pages++;
        }


        bodyButtons[0][0].setText("New deck.");
        bodyButtons[0][0].setOnAction((ActionEvent ae) -> Main.startDeckC(mainstage));
        root.getChildren().add(body);
        body.setLayoutY(100);

        //Making bottom "page turn" buttons.
        HBox bottomBar = new HBox(10);
        bottomBar.setPadding(new Insets(32));
        //array of the buttons to go in the Hbox
        Button[] bottomButtons = new Button[2];

        bottomButtons[0] = new Button("<---");
        bottomButtons[0].setPrefSize(350, 60);
        bottomButtons[0].setOnAction((ActionEvent ae) -> Main.placeholder());

        bottomButtons[1] = new Button("--->");
        bottomButtons[1].setPrefSize(350, 60);
        bottomButtons[1].setOnAction((ActionEvent ae) -> Main.placeholder());

        bottomBar.getChildren().addAll(bottomButtons);
        root.getChildren().add(bottomBar);
        bottomBar.setLayoutY(800);

        return root;

    }
}