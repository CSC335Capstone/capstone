package application;

public class ClassCard extends Card{
	
	// Properties
	private CharacterClass type;
	
	// Getters and Setters
	public CharacterClass getType(){return type;}
	public void setType(CharacterClass newType){type = newType;}
	public ClassCard()
	{
		super.setCardType(CARD_TYPE.CLASS);
		int switchValue = Randomizer.RollDice(3);
		switch(switchValue){
		case 1:
			type = new Cleric();
			super.setImageFile(IMAGE_PATH + "cleric.jpg");
			break;
		case 2:
			type = new Warrior();
			super.setImageFile(IMAGE_PATH + "warrior.jpg");
			break;
		case 3:
			type = new Wizard();
			super.setImageFile(IMAGE_PATH + "Wizard.jpg");
			break;
		}
	}
}
