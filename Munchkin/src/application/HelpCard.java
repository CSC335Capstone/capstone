package application;

public class HelpCard extends Card{
	public HelpCard(){
		super.setCardType(CARD_TYPE.HELP);
		super.setImageFile(IMAGE_PATH + "help.jpg");
	}
}
