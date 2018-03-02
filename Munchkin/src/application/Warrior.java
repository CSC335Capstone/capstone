package application;

public class Warrior extends CharacterClass {
	
	public Warrior(){
		super.setType(CLASSES.WARRIOR);
		super.setAbilities(new CLASS_ABILITY[]{CLASS_ABILITY.BERSERK});
	}

}
