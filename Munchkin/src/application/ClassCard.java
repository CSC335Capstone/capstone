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
			break;
		case 2:
			type = CLASSES.WARRIOR;
			break;
		case 3:
			type = CLASSES.WIZARD;
			break;
		}
	}
}
