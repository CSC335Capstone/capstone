/*
 * file: Loops.java
 * 
 * Description: Loops class is just the framework for the loop that will be used to
 * 					determine if the game should keep on going on not. This loop will 
 * 					check to see if any player has reached level 10. Once a plater reaches
 * 					level 10 then that player wins and the game shall terminate. This
 * 					class is just a framework and it contains class methods that have not
 * 					necessarily been implemented yet. Once the design for the game has been 
 * 					finalized, then we can create the necessary methods and classes to call
 * 					them properly.
 * 
 * Author: Emilio Neriz, Juan Vargas, Jason Kerr, Katherine Smith
 */

public class Loops {
	
	//defining the players
	private static Character playerOne;
	private static Character playerTwo;
	private static Character playerThree;
	private static Character playerFour;
	//array to hold all the players
	private static Object[] players = {playerOne, playerTwo, playerThree, playerFour};
	
	static int nextPlayer = 0;
	static int highestLevel = 0;
	
	public static void main(String[] args){
		
		//defining the players with the character class that will be implemented
		Character playerOne = new Character();
		playerOne.level = 0;
		Character playerTwo = new Character();
		playerTwo.level = 0;
		Character playerThree = new Character();
		playerThree.level = 0;
		Character playerFour = new Character();
		playerFour.level = 0;
		//array to hold all the players
		Character[] players = {playerOne, playerTwo, playerThree, playerFour};
		
		int nextPlayer = 0;
		int highestLevel = 0;
		
		//new deck to use for the game. Class would be implemented later
		Deck deck = new Deck();
		
		//keep playing until an individual player reaches level 10 then exit loop
		// as the game would end.
		looper(deck);
	}

	//method will be in charge of the loop while the game is active, until someone
	// reaches level 10
	private static void looper(Deck deck) {
		
		//issue cards to initialize the game
		playerOne.cards = deck.cards();
		playerTwo.cards = deck.cards();
		playerThree.cards = deck.cards();
		playerFour.cards = deck.cards();
		
		//keep playing until an individual player reaches level 10 then exit loop
		// as the game would end.
		while(highestLevel < 10) {
			//draw a card to see if they fight a monster
			munchkin();
			
			//call help card to see if another player wants to help defeat the monster.
			helpCard();
			
			//call treasure card to see if you can move up a level
			treasureCard();
			
			//checking level of current player
			if(highestLevel < players[nextPlayer].level){
				highestLevel = players[nextPlayer].level;
			}
			
			//next players turn
			nextPlayer++;
		}		
	}

	private static void monsterCard() {
		//monster card will have the level of the monster as well as the treasure
		// once the monster is defeated. It will have the penalty if you can't defeat
		// the monster as well as the vulnerability of the monster.
		//This method will also call the card method in the case the player has to 
		// draw a new card.
	}
	
	private static void curseCard() {
		//this method will be used when there is a curse card that has been drawn
		// it will place the curse on the certain player that drew the card.
	}

	private static void treasureCard() {
		//treasure card method will be used to see if the user wants to use the
		// treasure card and if so then they can move up levels or use it as they like.
	}

	private static void issueCards() {
		//method will be used to issue the initial cards of the game for all the players
		// will subtracts cards from the DECK and add cards to the Character.cards
	}

	//method lets players draw a card
	private static void munchkin() {
		//method will be used for the current player to draw a card, depending if
		// if it is a monster or curse card then other methods will be implemented.
		// if monster card then we call monsterCard method if curse card then we call
		// curseCard method.
	}
	
	private static void helpCard() {
		//method will be used just in case another player wants to help
		// the current plater defeat the monster.
	}
	
	private static void card() {
		//this method will have the image file that contains the card that has been drawn.
		//This method will be used when a new card needs to be drawn, it will call the Deck
		// object to draw the new card from it.
	}
}
