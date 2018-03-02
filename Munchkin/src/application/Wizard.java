package application;

public class Wizard extends CharacterClass{
	
	public Wizard(){
		super.setType(CLASSES.WIZARD);
		super.setAbilities(new CLASS_ABILITY[]{CLASS_ABILITY.CHARM, CLASS_ABILITY.DISCARD_RUN_AWAY});
	}

}
