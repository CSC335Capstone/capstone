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
}
