package application;

public class RaceCard extends Card{
	// Attributes
	private RACES type;
	
	// Getters and Setters
	public RACES getType(){return type;}
	public void setType(RACES newType){type = newType;}
	
	public RaceCard()
	{
		int switchValue = Randomizer.RollDice(3);
		switch(switchValue){
		case 1:
			type = RACES.DWARF;
			super.setImageFile(IMAGE_PATH + "dwarf.jpg");
			break;
		case 2:
			type = RACES.ELF;
			super.setImageFile(IMAGE_PATH + "elf.jpg");
			break;
		case 3:
			super.setImageFile(IMAGE_PATH + "halfling.jpg");
			type = RACES.HALFLING;
			break;
		}
	}
	

}
