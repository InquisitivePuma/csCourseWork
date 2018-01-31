import Models.Cards;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class CardV {
    private static Stage mainstage;
    public static Pane launchCardV(Stage stage, int id){
        mainstage =stage;
        Pane root = new Pane();

        //Gathering relevant information about the deck:
        String deckName = DeckService.selectDeckName(id);
        ArrayList<Cards> Cards = CardService.selectDeckCards(id);
        int currentCard = 0;
        Cards currentCardO = Cards.get(currentCard);

        //Showing deck title at top of window
        Text title = new Text(10, 50, deckName);
        title.setFont(new Font(20));
        title.setLayoutX(20);
        root.getChildren().add(title);
        //making a new Hbox for top menu buttons
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(15));
        //array of the buttons to go in the HBox
        Button[] topButtons = new Button[4];

        topButtons[0] = new Button("X% correct");
        topButtons[0].setPrefSize(180, 60);

        topButtons[1] = new Button((Cards.size()-currentCard) + " cards to go");
        topButtons[1].setPrefSize(340, 60);

        topButtons[2] = new Button("Flip");
        topButtons[2].setPrefSize(84, 60);
        topButtons[2].setOnAction((ActionEvent ae) -> Main.placeholder());

        topButtons[3] = new Button("Back");
        topButtons[3].setPrefSize(84, 60);
        topButtons[3].setOnAction((ActionEvent ae) -> Main.startDeckS(mainstage));

        topBar.getChildren().addAll(topButtons);
        root.getChildren().add(topBar);
        topBar.setLayoutY(60);
        topBar.setLayoutX(15);

        //making a new HBox for the central elements
        HBox centre = new HBox(10);
        centre.setPadding((new Insets(15)));
        //array of all the buttons to go in the HBox - I'm not reusing the last one because it's the wrong size!
        Button[] myButtons = new Button[3];

        myButtons[0] = new Button("Mark wrong / \nlast card.");
        myButtons[0].setPrefSize(180, 540);
        myButtons[0].setOnAction((ActionEvent ae) -> Main.placeholder());

        myButtons[1] = new Button(currentCardO.getFrontText());
        myButtons[1].setPrefSize(340, 540);

        myButtons[2] = new Button("Mark right / \nnext card.");
        myButtons[2].setPrefSize(180, 540);
        myButtons[2].setOnAction((ActionEvent ae) -> Main.placeholder());

        centre.getChildren().addAll(myButtons);
        root.getChildren().add(centre);
        centre.setLayoutY(130);
        centre.setLayoutX(15);

        //making a new HBox for the bottom elements
        HBox bottomBar = new HBox(10);
        bottomBar.setPadding((new Insets(15)));

        //reusing other array for efficiency.
        myButtons[0] = new Button("Switch to manual");
        myButtons[0].setPrefSize(180, 120);
        myButtons[0].setOnAction((ActionEvent ae) -> Main.placeholder());

        myButtons[1] = new Button("Card urgency");
        myButtons[1].setPrefSize(340, 120);

        myButtons[2] = new Button("Edit card");
        myButtons[2].setPrefSize(180, 120);
        myButtons[2].setOnAction((ActionEvent ae) -> Main.startCardC(mainstage));

        bottomBar.getChildren().addAll(myButtons);
        root.getChildren().add(bottomBar);
        bottomBar.setLayoutY(680);
        bottomBar.setLayoutX(15);

        return root;
    }
}
