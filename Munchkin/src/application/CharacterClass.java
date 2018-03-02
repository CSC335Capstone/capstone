package application;

public class CharacterClass {
	
	// Attributes
	private CLASSES type;
	private CLASS_ABILITY[] abilities;
	
	// Getters and Setters
	public CLASSES getType(){return type;}
	public void setType(CLASSES newType){type = newType;}
	public CLASS_ABILITY[] getAbilities(){return abilities;}
	public void setAbilities(CLASS_ABILITY[] newAbilities){abilities = newAbilities;}
}
