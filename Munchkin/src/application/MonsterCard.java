package application;

public class MonsterCard extends Card{
	
	// Properties
	private int level;
	private RACES vulnerability;
	private int treasureCards;
	private MONSTER_PENALTIES penalty;
	
	// Getters and Setters
	public int getLevel(){return level;}
	public RACES getVulnerability(){return vulnerability;}
	public int getTreasureCards(){return treasureCards;}
	public MONSTER_PENALTIES getPenalty(){return penalty;}
	
	public MonsterCard(){
		super.setCardType(CARD_TYPE.MONSTER);
		super.setImageFile(IMAGE_PATH + "Monster.jpg");
		
		level = Randomizer.RollDice(20);
		String setString = "L: " + Integer.toString(level) + " ";
		
		int switchValue = Randomizer.RollDice(10);
		switch(switchValue){
		case 1:
			vulnerability = RACES.ELF;
			setString += "V: E ";
			break;
		case 2:
			vulnerability = RACES.HALFLING;
			setString += "V: H ";
			break;
		case 3:
			vulnerability = RACES.DWARF;
			setString += "V: D ";
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			vulnerability = RACES.ALL;
			setString += "V: A ";
			break;
		default:
			break;
		}
		
		treasureCards = Randomizer.RollDice(5);
		
		setString += "T: " + Integer.toString(treasureCards) + " ";
		
		switchValue = Randomizer.RollDice(5);
		
		switch(switchValue){
		case 1:
			penalty = MONSTER_PENALTIES.LOSE_ONE_LEVEL;
			setString += "P: Lose 1 Level";
			break;
		case 2:
			penalty = MONSTER_PENALTIES.LOSE_TWO_LEVELS;
			setString += "P: Lose 2 Levels";
			break;
		case 3:
			penalty = MONSTER_PENALTIES.LOSE_BIGGEST_ITEM;
			setString += "P: Lose Biggest Item";
			break;
		case 4:
			penalty = MONSTER_PENALTIES.LOSE_TWO_ITEMS;
			setString += "P: Lose 2 Items";
			break;
		case 5:
			penalty = MONSTER_PENALTIES.DEATH;
			setString += "P: Death";
			break;
		default:
			break;
		}
		super.setImageLabel(setString);
		
	}
	
}
