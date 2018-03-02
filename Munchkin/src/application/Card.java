package application;

public abstract class Card {
	final static String IMAGE_PATH = ImagePath.IMAGE_PATH;
	// Properties
	private String imageFile;
	private String imageLabel;
	private CARD_TYPE cardType;
	
	// Methods
	public String getImageFile(){return imageFile;}
	public void setImageFile(String imageFile){this.imageFile = imageFile;}
	public String getImageLabel(){return imageLabel;}
	public void setImageLabel(String imageLabel){this.imageLabel = imageLabel;}
	public CARD_TYPE getCardType(){return cardType;}
	public void setCardType(CARD_TYPE cardType){this.cardType = cardType;}
}
