package Models;

public class Decks {
    private int deckID;
    private int includes;
    private int created; //ddmmyyyy
    private int lastEdit; //ddmmyyyy
    private int noOfCards;

    public int getDeckID() {
        return deckID;
    }

    public void setDeckID(int deckID) {
        this.deckID = deckID;
    }

    public int getIncludes() {
        return includes;
    }

    public void setIncludes(int includes) {
        this.includes = includes;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(int lastEdit) {
        this.lastEdit = lastEdit;
    }

    public int getNoOfCards() {
        return noOfCards;
    }

    public void setNoOfCards(int noOfCards) {
        this.noOfCards = noOfCards;
    }

}
