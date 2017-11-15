import Models.Cards;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CardService {
    private static ArrayList<Cards> selectDeckCards(int DeckID, Models.DatabaseConnection db){
        try {
            ResultSet cards = db.runQuery(db.newStatement("SELECT CardID, lastEdit, frontText, frontText," +
                    " backText, backImage, thirdText, thirdImage FROM Include WHERE DeckID = " + DeckID));
            ArrayList<Cards> returnedCards = new ArrayList<>();
            if (cards != null) {
                while (cards.next()) {
                    returnedCards.add(new Cards(cards.getInt("cardID"), cards.getInt("lastEdit"),
                            cards.getString("frontText"), cards.getString("frontImage"),
                            cards.getString("backText"), cards.getString("backImage"),
                            cards.getString("thirdText"), cards.getString("thirdImage")));
                }
            }

            return returnedCards;
        }
        catch (java.sql.SQLException exception){
            System.out.println("Database query error: " + exception.getMessage());
            ArrayList<Cards> emptyList = new ArrayList<>();
            return emptyList;
        }

    }

    /*private static void insertCard(int DeckID, Models.DatabaseConnection db, Cards card){
        db.runQuery(db.newStatement("INSERT INTO Include (CardID, lastEdit, frontText, frontImage, " +
                "backText, backImage, thirdText, thirdImage) VALUES ( "+card.getCardID()+","+card.getLastEdit()+","
                +card.getFrontText()+","+card.getFrontImage()+","+card.getBackText()+","+card.getBackImage()+","
                +card.getThirdText()+","+card.getThirdImage()));
    }
    private static void updateCard(int CardID, Models.DatabaseConnection db, Cards card){
                db.runQuery(db.newStatement("UPDATE Cards SET CardID="+card.getCardID()+", lastEdit="+MainController.getDateTime()+
                ", frontText="+card.getFrontText()+", frontImage="+card.getFrontImage()+", " +
                "backText="+card.getBackText()+", backImage="+card.getBackImage()+", " +
                "thirdText="+card.getThirdText()+", thirdImage="+card.getThirdImage()));
    This is all wrong - look at pizza example classes. String concatenation is JANKYYYYY
    }*/
}
