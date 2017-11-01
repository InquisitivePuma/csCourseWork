package Models;

public class Decks {
    private int deckID;
    private int includes;
    private int created; //ddmmyyyy
    private int lastEdit; //ddmmyyyy
    private int noOfCards;

    public void setDeckID(int deckID) {
        this.deckID = deckID;
    }

    public void setIncludes(int includes) {
        this.includes = includes;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setLastEdit(int lastEdit) {
        this.lastEdit = lastEdit;
    }

    public void setNoOfCards(int noOfCards) {
        this.noOfCards = noOfCards;
    }

    public int getDeckID() {
        return deckID;
    }

    public int getIncludes() {
        return includes;
    }

    public int getCreated() {
        return created;
    }

    public int getLastEdit() {
        return lastEdit;
    }

    public int getNoOfCards() {
        return noOfCards;
    }
}
