package application;

public class Dwarf extends Race {
	
	
	public Dwarf(){
		super.setType(RACES.DWARF);
		super.setAbilities(new RACE_ABILITY[]{RACE_ABILITY.CARRY_EXTRA_CARD});
	}
}
