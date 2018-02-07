import Models.Cards;
import Models.DatabaseConnection;
import Models.Decks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainController {

    //constructs for data from the database
    private DatabaseConnection database;
    private ArrayList<Cards> cards = new ArrayList<>();
    private ArrayList<Decks> decks = new ArrayList<>();

    public DatabaseConnection getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseConnection database) {
        this.database = database;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Cards> cards) {
        this.cards = cards;
    }

    public ArrayList<Decks> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Decks> decks) {
        this.decks = decks;
    }

    public MainController(){
        database = new DatabaseConnection("");
    }
    public static String getDateTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
        String strDate = sdf.format(cal.getTime());
        return strDate;
    }

}
