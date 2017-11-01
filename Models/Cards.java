package Models;

public class Cards {
    private int cardID;
    private int imageID;
    private int created;
    private int lastEdit;
    private String frontText;
    private String frontImage;
    private String backText;
    private String backImage;
    private String thirdText;
    private String thirdImage;


    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
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

    public String toString(){
        return frontText+"\n\n\n"+backText+"\n\n"+thirdText;
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
}
