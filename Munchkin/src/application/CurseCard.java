package application;

public class CurseCard extends Card{
	
	// Properties
	private CURSES type;
	
	// Getters and Setters
	public CURSES getType(){return type;}
	public void setType(CURSES newType){type = newType;}
	
	public CurseCard(){
		super.setCardType(CARD_TYPE.CURSE);
		int switchValue = Randomizer.RollDice(10);
		switch(switchValue){
		case 1:
			type = CURSES.LOSE_ONE_ITEM;
			super.setImageFile(IMAGE_PATH + "genericCurse.jpg");
			super.setImageLabel("Lose one item");
			break;
		case 2:
			type = CURSES.LOSE_TWO_ITEMS;
			super.setImageFile(IMAGE_PATH + "genericCurse.jpg");
			super.setImageLabel("Lose two items");
			break;
		case 3:
			type = CURSES.LOSE_RACE_BECOME_HUMAN;
			super.setImageFile(IMAGE_PATH + "loseRace.jpg");
			break;
		case 4:
		case 5:
			type = CURSES.LOSE_RACE_DRAW_NEW_RACE;
			super.setImageFile(IMAGE_PATH + "loseRace.jpg");
			break;
		case 6:
		case 7:
			type = CURSES.LOSE_CLASS;
			super.setImageFile(IMAGE_PATH + "loseClass.jpg");
			break;
		case 8:
		case 9:
		case 10:
			type = CURSES.LOSE_LEVEL;
			super.setImageFile(IMAGE_PATH + "loseLevel.jpg");
			break;
		default:
			break;
		}

	}
	
}
