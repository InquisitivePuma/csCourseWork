import Models.Cards;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CardService {
    private static ArrayList<Cards> selectDeckCards(int DeckID, Models.DatabaseConnection db){
        try {
            ResultSet cards = db.runQuery(db.newStatement("SELECT CardID, lastEdit, frontText, frontText," +
                    " backText, backImage, thirdText, thirdImage FROM Include WHERE DeckID = " + DeckID));
            ArrayList<Cards> returnedCards = new ArrayList<>();
            if (cards != null) {
                while (cards.next()) {
                    returnedCards.add(new Cards(cards.getInt("cardID"), cards.getInt("lastEdit"),
                            cards.getString("frontText"), cards.getString("frontText"),
                            cards.getString("backText"), cards.getString("backImage"),
                            cards.getString("thirdText"), cards.getString("thirdImage")));
                }
            }

            return returnedCards;
        }
        catch (java.sql.SQLException exception){
            System.out.println("Database query error: " + exception.getMessage());
            ArrayList<Cards> emptyList = new ArrayList<Cards>();
            return emptyList;
        }
    }
}
