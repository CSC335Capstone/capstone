package application;

public class Race {
	
	// Attributes
	private RACES type;
	private RACE_ABILITY[] abilities;
	
	// Getters and Setters
	public RACES getType(){return type;}
	public void setType(RACES newType){type = newType;}
	public RACE_ABILITY[] getAbilities(){return abilities;}
	public void setAbilities(RACE_ABILITY[] newAbilities){abilities = newAbilities;}
	
}
