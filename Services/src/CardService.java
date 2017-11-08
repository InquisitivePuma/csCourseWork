package Models;

import java.util.ArrayList;

public class CardService {
    private static ArrayList<Cards> selectDeckCards(int DeckID, Models.DatabaseConnection db){
        ArrayList<Cards> cards = new ArrayList<>();
        db.runQuery(db.newStatement("SELECT * FROM Include WHERE DeckID = "+DeckID));
        //do something to put that in cards.
        return cards;
    }
}
