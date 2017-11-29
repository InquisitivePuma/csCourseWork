package Models;

public class Cards {
    private int cardID;
    private int lastEdit; //dd-mm-yyyy hh:mm:ss => ddmmyyyyhhmmss
    private String frontText;
    private String frontImage;
    private String backText;
    private String backImage;
    private String thirdText;
    private String thirdImage;
    private int urgency;
    public Cards(int cardID, int lastEdit, String frontText, String frontImage,
                 String backText, String backImage, String thirdText, String thirdImage, int urgency) { //<- constructor
        this.cardID = cardID;
        this.lastEdit = lastEdit;
        this.frontText = frontText;
        this.frontImage = frontImage;
        this.backText = backText;
        this.backImage = backImage;
        this.thirdText = thirdText;
        this.thirdImage = thirdImage;
        this.urgency=urgency;
    }
    //Getters and setters.
    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(int lastEdit) {
        this.lastEdit = lastEdit;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getThirdText() {
        return thirdText;
    }

    public void setThirdText(String thirdText) {
        this.thirdText = thirdText;
    }

    public String getThirdImage() {
        return thirdImage;
    }

    public void setThirdImage(String thirdImage) {
        this.thirdImage = thirdImage;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

}
