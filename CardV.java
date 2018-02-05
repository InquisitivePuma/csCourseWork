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

    //Making data used in multiple functions visible throughout the class - tidier than passing it around!
    private static String deckName;
    private static ArrayList<Cards> cards;
    private static Button[] topButtons = new Button[4];
    private static Button[] myButtons = new Button[3];
    private static Button[] bottomButtons = new Button[3];
    private static int currentCard = 0;
    private static int lastCard = -1;
    private static boolean front = true;
    public static int pool;

    public static Pane launchCardV(Stage stage, int id){
        mainstage = stage;
        Pane root = new Pane();

        deckName = DeckService.selectDeckName(id);
        cards = CardService.selectDeckCards(id);
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i).toString());
        }
       
        Cards currentCardO = cards.get(currentCard);

        //Showing deck title at top of window
        Text title = new Text(10, 50, deckName);
        title.setFont(new Font(20));
        title.setLayoutX(20);
        root.getChildren().add(title);
        //making a new Hbox for top menu buttons
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(15));
        //array of the buttons to go in the HBox
        topButtons[0] = new Button("X% correct");
        topButtons[0].setPrefSize(180, 60);

        topButtons[1] = new Button((cards.size()-currentCard) + " cards to go");
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
        myButtons[0] = new Button("Start of deck.");
        myButtons[0].setPrefSize(180, 540);
        myButtons[0].setOnAction((ActionEvent ae) -> lastCard());

        myButtons[1] = new Button(currentCardO.getFrontText());
        myButtons[1].setPrefSize(340, 540);

        myButtons[2] = new Button("Mark right / \nnext card.");
        myButtons[2].setPrefSize(180, 540);
        myButtons[2].setOnAction((ActionEvent ae) -> nextCard());

        centre.getChildren().addAll(myButtons);
        root.getChildren().add(centre);
        centre.setLayoutY(130);
        centre.setLayoutX(15);

        //making a new HBox for the bottom elements
        HBox bottomBar = new HBox(10);
        bottomBar.setPadding((new Insets(15)));
        bottomButtons[0] = new Button("Switch to manual");
        bottomButtons[0].setPrefSize(180, 120);
        bottomButtons[0].setOnAction((ActionEvent ae) -> Main.placeholder());

        bottomButtons[1] = new Button(Integer.toString(currentCardO.getUrgency()));
        bottomButtons[1].setPrefSize(340, 120);

        bottomButtons[2] = new Button("Edit card");
        bottomButtons[2].setPrefSize(180, 120);
        bottomButtons[2].setOnAction((ActionEvent ae) -> Main.startCardC(mainstage));

        bottomBar.getChildren().addAll(bottomButtons);
        root.getChildren().add(bottomBar);
        bottomBar.setLayoutY(680);
        bottomBar.setLayoutX(15);

        return root;
    }
    public static void nextCard(){
        if (currentCard < cards.size()-1) {
            lastCard = currentCard;
            currentCard++;
            System.out.println(Integer.toString(currentCard));
            Cards CurrentCardO = cards.get(currentCard);
            topButtons[1].setText((cards.size() - currentCard) + " cards to go");
            myButtons[1].setText(CurrentCardO.getFrontText()+"\n\n"+CurrentCardO.getThirdText());
            bottomButtons[1].setText(Integer.toString(CurrentCardO.getUrgency()));
            myButtons[0].setText("Mark wrong / \nlast card.");
        }else{
            myButtons[2].setText("End of deck.");
        }

    }
    public static void lastCard(){
        if (lastCard != -1) {
            int swap = lastCard;
            lastCard = currentCard;
            currentCard = swap;
            System.out.println(Integer.toString(currentCard));
            Cards CurrentCardO = cards.get(currentCard);
            topButtons[1].setText((cards.size() - currentCard) + " cards to go");
            myButtons[1].setText(CurrentCardO.getFrontText()+"\n\n"+CurrentCardO.getThirdText());
            bottomButtons[1].setText(Integer.toString(CurrentCardO.getUrgency()));
            myButtons[2].setText("Mark right / \nnext card.");
        }else{
            myButtons[0].setText("Start of deck.");
        }
    }
    //spaced review actions for a reported success/fail. See design section for flowchart/initial design.
    public void spacedCardSuccess(){
        pool = 0;
        for(Cards c : cards){
            pool+=c.getUrgency;
        }
        CurrentCardO.setUrgency(CurrentCardO.getUrgency()/3); 
        int select = ThreadLocalRandom.current().nextInt(0, pool);
        for(Cards c : cards){
            select-=c.getUrgency();
            if(select<1){
                CurrentCardO = c;
                break;
            }
        }
        topButtons[1].setText((cards.size() - currentCard) + " cards to go");
        myButtons[1].setText(CurrentCardO.getFrontText()+"\n\n"+CurrentCardO.getThirdText());
        bottomButtons[1].setText(Integer.toString(CurrentCardO.getUrgency()));
    }
    public void spacedCardFail(){
        pool = 0;
        for(Cards c : cards){
            pool+=c.getUrgency;
        }
        CurrentCardO.setUrgency(CurrentCardO.getUrgency()+((100-CurrentCardO.getUrgency())/3));
        int select = ThreadLocalRandom.current().nextInt(0, pool);
        for(Cards c : cards){
            select-=c.getUrgency();
            if(select<1){
                CurrentCardO = c;
                break;
            }
        }
        topButtons[1].setText((cards.size() - currentCard) + " cards to go");
        myButtons[1].setText(CurrentCardO.getFrontText()+"\n\n"+CurrentCardO.getThirdText());
        bottomButtons[1].setText(Integer.toString(CurrentCardO.getUrgency()));
    }
    public void flip(){
        if(front = true) {
            myButtons[1].setText(CurrentCardO.getBackText()+"\n\n"+CurrentCardO.getThirdText());
        }else{
            myButtons[1].setText(CurrentCardO.getFrontText()+"\n\n"+CurrentCardO.getThirdText());
        }
    }
}
