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
import java.util.concurrent.ThreadLocalRandom;

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
    private static int pool;
    private static Cards currentCardO;
    private static boolean manual = true;

    public static Pane launchCardV(Stage stage, int id){
        mainstage = stage;
        Pane root = new Pane();

        deckName = DeckService.selectDeckName(id);
        cards = CardService.selectDeckCards(id);
        for (Cards card : cards) {
            System.out.println(card.toString());
        }

        currentCardO = cards.get(currentCard);

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
        topButtons[2].setOnAction((ActionEvent ae) -> flip());

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
        myButtons[0] = new Button("Last card viewed.");
        myButtons[0].setPrefSize(180, 540);
        myButtons[0].setOnAction((ActionEvent ae) -> lastCard());

        myButtons[1] = new Button(currentCardO.getFrontText()+"\n\n"+currentCardO.getThirdText());
        myButtons[1].setPrefSize(340, 540);

        myButtons[2] = new Button("Next card in deck.");
        myButtons[2].setPrefSize(180, 540);
        myButtons[2].setOnAction((ActionEvent ae) -> nextCard());

        centre.getChildren().addAll(myButtons);
        root.getChildren().add(centre);
        centre.setLayoutY(130);
        centre.setLayoutX(15);

        //making a new HBox for the bottom elements
        HBox bottomBar = new HBox(10);
        bottomBar.setPadding((new Insets(15)));
        bottomButtons[0] = new Button("Enable spaced review");
        bottomButtons[0].setPrefSize(180, 120);
        bottomButtons[0].setOnAction((ActionEvent ae) -> manualToggle());

        bottomButtons[1] = new Button("Urgency: "+Integer.toString(currentCardO.getUrgency()));
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
            bottomButtons[1].setText("Urgency: "+Integer.toString(CurrentCardO.getUrgency()));
            myButtons[0].setText("Last card viewed.");
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
            bottomButtons[1].setText("Urgency: "+Integer.toString(CurrentCardO.getUrgency()));
            myButtons[2].setText("Next card in deck.");
        }else{
            myButtons[0].setText("Start of deck.");
        }
    }
    //spaced review actions for a reported success/fail. See design section for flowchart/initial design.
    public static void spacedCardSuccess(){
        pool = 0;
        for(Cards c : cards){
            pool+=c.getUrgency();
        }
        currentCardO.setUrgency(currentCardO.getUrgency()/2);
        nextSpacedCard();
        myButtons[1].setText(currentCardO.getFrontText()+"\n\n"+currentCardO.getThirdText());
        bottomButtons[1].setText("Urgency: "+Integer.toString(currentCardO.getUrgency()));
    }

    public static void spacedCardFail(){
        pool = 0;
        for(Cards c : cards){
            pool+=c.getUrgency(); //total urgency of cards in deck
        }
        //adjust urgency of card depending on correctness
        currentCardO.setUrgency(currentCardO.getUrgency()+((200-currentCardO.getUrgency())/4));
        nextSpacedCard(); //load card object CurrentCardO for next card.
        myButtons[1].setText(currentCardO.getFrontText()+"\n\n"+currentCardO.getThirdText());
        bottomButtons[1].setText("Urgency: "+Integer.toString(currentCardO.getUrgency()));
    }

    public static void nextSpacedCard(){
        try { //Error catching for bad database input (If total urgency of cards in deck = 0)
            int select = ThreadLocalRandom.current().nextInt(0, pool); //Random number between 0 and combined urgency.
            for(Cards c : cards){
                select-=c.getUrgency();
                if(select<1){
                    currentCardO = c;
                    break;           //Cycles through cards until total urgency of cards so far is <= the random number.
                }                    //This gives a random card, with the likelihood of selecting a given card
            }                        //being proportional to its urgency.
        }catch (IllegalArgumentException e){
            System.out.println("Total card urgency is 0. \n" +
                    " Make sure to give cards an urgency of greater than 0 when you add them to the database! \n" +
                    "Recommended starting value is 100.");
        }
    }

    public static void flip(){
        if(front) {
            myButtons[1].setText(currentCardO.getBackText()+"\n\n"+currentCardO.getThirdText());
            front=false;
        }else{
            myButtons[1].setText(currentCardO.getFrontText()+"\n\n"+currentCardO.getThirdText());
            front=true;
        }
    }
    public static void manualToggle(){
        if(manual) {
            manual=false;
            myButtons[0].setText("Mark card wrong.");
            myButtons[2].setText("Mark card right.");
            bottomButtons[0].setText("Switch to manual.");
            myButtons[0].setOnAction((ActionEvent ae) -> spacedCardFail());
            myButtons[2].setOnAction((ActionEvent ae) -> spacedCardSuccess());
            topButtons[1].setText("Spaced review mode enabled!");
        }else{
            manual=true;
            currentCard=0;
            myButtons[0].setText("Last card viewed.");
            myButtons[2].setText("Next card in deck.");
            bottomButtons[0].setText("Enable spaced review.");
            myButtons[0].setOnAction((ActionEvent ae) -> spacedCardFail());
            myButtons[2].setOnAction((ActionEvent ae) -> spacedCardSuccess());
            topButtons[1].setText(Integer.toString(cards.size()-currentCard) +  "cards to go");
        }
    }
}