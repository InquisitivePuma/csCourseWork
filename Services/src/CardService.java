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
    private static ArrayList<Cards> selectDeckCards(int DeckID, Models.DatabaseConnection db){
        try {
            ResultSet cards = db.runQuery(db.newStatement("SELECT CardID, lastEdit, frontText, frontImage," +
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
            System.out.println("Database query error in SelectDeckCards: " + exception.getMessage());
            return new ArrayList<Cards>();
        }

    }

    private static Cards selectCard(int cardID, Models.DatabaseConnection db){
        try {
        PreparedStatement cardStatement = db.newStatement("SELECT lastEdit, frontText, frontImage, backText, backImage, thirdText, thirdImage" +
                "FROM Card WHERE CardID = ?");
        cardStatement.setInt(1, cardID);
        ResultSet cardSet = db.runQuery(cardStatement);
        Cards card = new Cards(cardSet.getInt(cardID), cardSet.getInt("lastEdit"),
                cardSet.getString("frontText"), cardSet.getString("frontImage"),
                cardSet.getString("backText"), cardSet.getString("backImage"),
                cardSet.getString("thirdText"), cardSet.getString("thirdImage"));
        return card;
        }catch(SQLException e){
            System.out.println("SQLException in selectCard: "+e.getMessage());
            return new Cards(-404, 00000000000000, "", "",
                    "", "", "", "");
        }
    }

    private static void insertCard(int DeckID, Models.DatabaseConnection db, Cards card){
        try {
            String cQuery = "INSERT INTO Card (CardID,  LastEdit, frontText, frontImage, backText, backImage, thirdText, thirdImage)" +
                    "(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement cPrepped = db.newStatement(cQuery);
            cPrepped.setInt(1, card.getCardID());
            cPrepped.setInt(2, card.getLastEdit());
            cPrepped.setString(3, card.getFrontText());
            cPrepped.setString(4, card.getFrontImage());
            cPrepped.setString(5, card.getBackText());
            cPrepped.setString(6, card.getBackImage());
            cPrepped.setString(7, card.getThirdText());
            cPrepped.setString(8, card.getThirdImage());
            db.runQuery(cPrepped);

            String iQuery = "INSERT INTO Include (DeckID, CardID) values (?, ?)";
            PreparedStatement iPrepped = db.newStatement(iQuery);
            iPrepped.setInt(1, DeckID);
            iPrepped.setInt(2, card.getCardID());
            db.runQuery(iPrepped);
        }catch(SQLException e){System.out.println("SQLException in insertCard: "+e.getMessage());}
    }

    private static void updateCard(Models.DatabaseConnection db, Cards card){
        try {
        String cQuery = "UPDATE Card SET (CardID=?,  LastEdit=?, frontText=?, frontImage=?, " +
                "backText=?, backImage=?, thirdText=?, thirdImage=?)";
        PreparedStatement cPrepped = db.newStatement(cQuery);
        cPrepped.setInt(1, card.getCardID());
        cPrepped.setInt(2, card.getLastEdit());
        cPrepped.setString(3, card.getFrontText());
        cPrepped.setString(4, card.getFrontImage());
        cPrepped.setString(5, card.getBackText());
        cPrepped.setString(6, card.getBackImage());
        cPrepped.setString(7, card.getThirdText());
        cPrepped.setString(8, card.getThirdImage());
        }catch(SQLException e){System.out.println("SQLException in updateCard: "+ e.getMessage());}

    }
}
