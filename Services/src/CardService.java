import Models.Cards;
import com.sun.media.sound.ModelSource;
import sun.plugin2.gluegen.runtime.CPU;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CardService {
    public static ArrayList<Cards> selectDeckCards(int DeckID){
        try {
            System.out.println(DeckID);
            ResultSet cards = Main.db.runQuery(Main.db.newStatement("SELECT c.CardID, c.lastEdit, c.frontText," +
                    " c.frontImage, c.backText, c.backImage, c.thirdText, c.thirdImage" +
                    " FROM Card c INNER JOIN Include i ON c.CardID = i.CardID" +
                    " WHERE i.DeckID = "+DeckID));
            ArrayList<Cards> returnedCards = new ArrayList<>();
            if (cards != null) {
                while (cards.next()) {
                    returnedCards.add(new Cards(cards.getInt("cardID"), cards.getInt("lastEdit"),
                            cards.getString("frontText"), cards.getString("frontImage"),
                            cards.getString("backText"), cards.getString("backImage"),
                            cards.getString("thirdText"), cards.getString("thirdImage"), cards.getInt("urgency")));
                }
            }
            System.out.println(returnedCards);
            return returnedCards;
        }
        catch (java.sql.SQLException exception){
            System.out.println("Database query error in SelectDeckCards: " + exception.getMessage());
            return new ArrayList<Cards>();
        }

    }
    //Selects a card from the database, identifying it by its CardID.
    public static Cards selectCard(int cardID){
        try {
        PreparedStatement cardStatement = Main.db.newStatement("SELECT lastEdit, frontText, frontImage, backText, backImage, thirdText, thirdImage" +
                "FROM Card WHERE CardID = ?");
        cardStatement.setInt(1, cardID);
        ResultSet cardSet = Main.db.runQuery(cardStatement);
        Cards card = new Cards(cardSet.getInt(cardID), cardSet.getInt("lastEdit"),
                cardSet.getString("frontText"), cardSet.getString("frontImage"),
                cardSet.getString("backText"), cardSet.getString("backImage"),
                cardSet.getString("thirdText"), cardSet.getString("thirdImage"), cardSet.getInt("urgency"));
        return card;
        }catch(SQLException e){
            System.out.println("SQLException in selectCard: "+e.getMessage());
            return new Cards(-404, 00000000000000, "", "", //Error code in place of card ID to allow recieving method to error check.
                    "", "", "", "", 0);
        }
    }

    //Inserts a card, given all relevant information.
    public static void insertCard(int DeckID, Cards card){
        try {
            String cQuery = "INSERT INTO Card (CardID,  LastEdit, frontText, frontImage, backText, backImage, thirdText, thirdImage, urgency)" +
                    "(?, ?, ?, ?, ?, ?, ?, ? ?)";
            PreparedStatement cPrepped = Main.db.newStatement(cQuery);
            cPrepped.setInt(1, card.getCardID());
            cPrepped.setInt(2, card.getLastEdit());
            cPrepped.setString(3, card.getFrontText());
            cPrepped.setString(4, card.getFrontImage());
            cPrepped.setString(5, card.getBackText());
            cPrepped.setString(6, card.getBackImage());
            cPrepped.setString(7, card.getThirdText());
            cPrepped.setString(8, card.getThirdImage());
            cPrepped.setInt(8, card.getUrgency());
            Main.db.runQuery(cPrepped);

            String iQuery = "INSERT INTO Include (DeckID, CardID) values (?, ?)";
            PreparedStatement iPrepped = Main.db.newStatement(iQuery);
            iPrepped.setInt(1, DeckID);
            iPrepped.setInt(2, card.getCardID());
            Main.db.runQuery(iPrepped);
        }catch(SQLException e){System.out.println("SQLException in insertCard: "+e.getMessage());}
    }

    //Updates a card, given ALL relevant details. It is presumed that any card you wnt to update will have been
    //instantiated in the prgram and so you will have all relevant data.
    public static void updateCard(Cards card){
        try {
        String cQuery = "UPDATE Card SET (CardID=?,  LastEdit=?, frontText=?, frontImage=?, " +
                "backText=?, backImage=?, thirdText=?, thirdImage=?, urgency=?)";
        PreparedStatement cPrepped = Main.db.newStatement(cQuery);
        cPrepped.setInt(1, card.getCardID());
        cPrepped.setInt(2, card.getLastEdit());
        cPrepped.setString(3, card.getFrontText());
        cPrepped.setString(4, card.getFrontImage());
        cPrepped.setString(5, card.getBackText());
        cPrepped.setString(6, card.getBackImage());
        cPrepped.setString(7, card.getThirdText());
        cPrepped.setString(8, card.getThirdImage());
        cPrepped.setInt(8, card.getUrgency());
        }catch(SQLException e){System.out.println("SQLException in updateCard: "+ e.getMessage());}

    }
}
