package application;

public class Halfling extends Race {
	
	public Halfling(){
		super.setType(RACES.HALFLING);
		super.setAbilities(new RACE_ABILITY[]{RACE_ABILITY.DOUBLE_PRICE_ITEM, RACE_ABILITY.EXTRA_RUN_AWAY});
	}

}
