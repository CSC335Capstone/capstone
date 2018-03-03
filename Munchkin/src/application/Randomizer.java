package application;

import java.util.Random;

public class Randomizer {
	public static int RollDice(int diceSize){
		Random generator = new Random();
		int returnValue = generator.nextInt(diceSize) + 1;
		return (returnValue);
	}

}
