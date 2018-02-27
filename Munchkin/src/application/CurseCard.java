package application;

public class CurseCard extends Card{
	
	// Properties
	private CURSES type;
	
	// Getters and Setters
	public CURSES getType(){return type;}
	public void setType(CURSES newType){type = newType;}
	
	public CurseCard(){
		
		int switchValue = Randomizer.RollDice(10);
		switch(switchValue){
		case 1:
			type = CURSES.LOSE_ONE_ITEM;
			break;
		case 2:
			type = CURSES.LOSE_TWO_ITEMS;
			break;
		case 3:
			type = CURSES.LOSE_RACE_BECOME_HUMAN;
			break;
		case 4:
		case 5:
			type = CURSES.LOSE_RACE_DRAW_NEW_RACE;
			break;
		case 6:
		case 7:
			type = CURSES.LOSE_CLASS;
			break;
		case 8:
		case 9:
		case 10:
			type = CURSES.LOSE_LEVEL;
			break;
		default:
			break;
		}

	}
	
}
