import Models.Cards;
import Models.DatabaseConnection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class ReviewController {

    private ArrayList<Cards> cards = new ArrayList<>();

    public void setDeck(int deckID, DatabaseConnection db){
       cards = CardService.selectDeckCards(deckID, db);
    }

    public Cards getSpacedCard(){
        int pool=0;
        for (Cards card:cards){
            pool+=card.getUrgency();
        }
        int select =  ThreadLocalRandom.current().nextInt(0, pool + 1);
    }

}
