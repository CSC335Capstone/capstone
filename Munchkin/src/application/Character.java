package application;

import java.util.ArrayList;
import java.util.Random;

public class Character {
	
	// Attributes
	private int level;
	private Race race;
	private CharacterClass currentClass;
	private ArrayList<Card> cards;
	
	// Getters and Setters
	public int getLevel(){return level;}
	public Race getRace(){return race;}
	public CharacterClass getCharacterClass(){return currentClass;}
	public ArrayList<Card> getCards(){return cards;}
	public void setLevel(int newLevel){level = newLevel;}
	public void setRace(Race newRace){race = newRace;}
	public void setCharacterClass(CharacterClass newClass){currentClass = newClass;}
	public void setCards(ArrayList<Card> newCards){cards = newCards;}
	
	// Methods
	public void assignRace(Race newRace){
		
		setRace(race);
	}
	public void assignClass(CharacterClass newClass){
		setCharacterClass(newClass);
	}
	public Card takeCard(Deck deck){
		Card returnCard = deck.draw();
		
		return returnCard;
	}
	public void addCard(Card card){
		cards.add(card);
	}
	public void discard(Card card){
		cards.remove(card);
	}
	public void playCard(Card card){
		// stub for playing a card
	}
	public int combatStrength()
	{
		int combatStrength = 0;
		// Code to calculate combat strength
		return combatStrength;
		
	}
	public int rollDie(){
		return Randomizer.RollDice(6);
	}
	public void buyLevel(){
		// Code for buying a level
	}
	public boolean runAway(){
		if(rollDie()>4){
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	

}
