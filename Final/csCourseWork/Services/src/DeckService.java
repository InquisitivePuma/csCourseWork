import Models.DatabaseConnection;
import com.sun.media.sound.ModelSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeckService {

    public static int noOfDecks(){
        try {
            PreparedStatement query = Main.db.newStatement("SELECT COUNT(*)FROM Deck");
            ResultSet rsNoOfDecks = Main.db.runQuery(query);
            int noOfDecks = rsNoOfDecks.getInt(1);
            return noOfDecks;
        }catch (java.sql.SQLException e) {
            System.out.println("Database query error in noOfDecks: " + e.getMessage());
            return 0;
        }
    }

    public static String selectDeckName(int deckID) {
        try {
            PreparedStatement query = Main.db.newStatement("SELECT name FROM Deck WHERE DeckID = "+ (deckID+1) +
                    " ORDER BY DeckID");
            ResultSet rs = Main.db.runQuery(query);

            String deckName = null;
            while (rs.next()) {
                deckName = rs.getString("name");
            }

            return deckName;
        } catch(java.sql.SQLException e){
            System.out.println("Database query error in selectDeckName: " + e.getMessage());
            return "";
        }
    }
}

