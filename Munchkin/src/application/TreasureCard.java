package application;

public class TreasureCard extends Card{
	
	// Properties
	private int goldPieces;
	private int combatAdvantage;
	
	// Getters and Setters
	public int getGoldPieces(){return goldPieces;}
	public int getCombatAdvantage(){return combatAdvantage;}
	public void setGoldPieces(int newGoldPieces){goldPieces = newGoldPieces;}
	public void setCombatAdvantage(int newCombatAdvantage){combatAdvantage = newCombatAdvantage;}
	
	public TreasureCard(){
		goldPieces = Randomizer.RollDice(301) + 99;
		combatAdvantage = Randomizer.RollDice(5);
		super.setImageFile(IMAGE_PATH + "genericTreasure.jpg");
	}
}
