package application;

public class Cleric extends CharacterClass{

	// Attributes
	// Constructor
	public Cleric(){
		super.setType(CLASSES.CLERIC);
		super.setAbilities(new CLASS_ABILITY[]{CLASS_ABILITY.FIGHT_UNDEAD});
	}
}
