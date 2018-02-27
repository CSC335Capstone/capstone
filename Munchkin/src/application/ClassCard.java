package application;

public class ClassCard extends Card{
	
	// Properties
	private CLASSES type;
	
	// Getters and Setters
	public CLASSES getType(){return type;}
	public void setType(CLASSES newType){type = newType;}
	public ClassCard()
	{
		int switchValue = Randomizer.RollDice(3);
		switch(switchValue){
		case 1:
			type = CLASSES.CLERIC;
			super.setImageFile(IMAGE_PATH + "cleric.jpg");
			break;
		case 2:
			type = CLASSES.WARRIOR;
			super.setImageFile(IMAGE_PATH + "warrior.jpg");
			break;
		case 3:
			type = CLASSES.WIZARD;
			super.setImageFile(IMAGE_PATH + "Wizard.jpg");
			break;
		}
	}
}
