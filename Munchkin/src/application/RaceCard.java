package application;

public class RaceCard extends Card{
	// Attributes
	private Race type;
	
	// Getters and Setters
	public Race getType(){return type;}
	public void setType(Race newType){type = newType;}
	
	public RaceCard()
	{
		super.setCardType(CARD_TYPE.RACE);
		int switchValue = Randomizer.RollDice(3);
		switch(switchValue){
		case 1:
			type = new Dwarf();
			super.setImageFile(IMAGE_PATH + "dwarf.jpg");
			break;
		case 2:
			type = new Elf();
			super.setImageFile(IMAGE_PATH + "elf.jpg");
			break;
		case 3:
			super.setImageFile(IMAGE_PATH + "halfling.jpg");
			type = new Halfling();
			break;
		}
	}
	

}
