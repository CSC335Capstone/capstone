package application;

public class Elf extends Race {
	
	public Elf(){
		super.setType(RACES.ELF);
		super.setAbilities(new RACE_ABILITY[]{RACE_ABILITY.EASIER_RUN_AWAY});
	}
}
