import Models.Cards;
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
        }catch(SQLException e){System.out.println("SQLException in insertCard");}
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
        }catch(SQLException e){System.out.println("SQLException in updateCard");}
    }
    /*
    private static void updateCard(Models.DatabaseConnection db, Cards card){
                db.runQuery(db.newStatement("UPDATE Cards SET CardID="+card.getCardID()+", lastEdit="+MainController.getDateTime()+
                ", frontText="+card.getFrontText()+", frontImage="+card.getFrontImage()+", " +
                "backText="+card.getBackText()+", backImage="+card.getBackImage()+", " +
                "thirdText="+card.getThirdText()+", thirdImage="+card.getThirdImage()));
    This is all wrong - look at pizza example classes. String concatenation is JANKYYYYY
    Another example is below
    }*/

    /*// the mysql insert statement
      String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
        + " values (?, ?, ?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString (1, "Barney");
      preparedStmt.setString (2, "Rubble");
      preparedStmt.setDate   (3, startDate);
      preparedStmt.setBoolean(4, false);
      preparedStmt.setInt    (5, 5000);
*/
}
