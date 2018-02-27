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
		
		level = Randomizer.RollDice(20);
		
		int switchValue = Randomizer.RollDice(10);
		switch(switchValue){
		case 1:
			vulnerability = RACES.ELF;
			break;
		case 2:
			vulnerability = RACES.HALFLING;
			break;
		case 3:
			vulnerability = RACES.DWARF;
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			vulnerability = RACES.ALL;
			break;
		default:
			break;
		}
		
		treasureCards = Randomizer.RollDice(5);
		
		switchValue = Randomizer.RollDice(5);
		
		switch(switchValue){
		case 1:
			penalty = MONSTER_PENALTIES.LOSE_ONE_LEVEL;
			break;
		case 2:
			penalty = MONSTER_PENALTIES.LOSE_TWO_LEVELS;
			break;
		case 3:
			penalty = MONSTER_PENALTIES.LOSE_BIGGEST_ITEM;
			break;
		case 4:
			penalty = MONSTER_PENALTIES.LOSE_TWO_ITEMS;
			break;
		case 5:
			penalty = MONSTER_PENALTIES.DEATH;
			break;
		default:
			break;
		}
		
	}
	
}
