package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Character {
	
	// Attributes
	private int level;
	private int gold;
	private Race race = null;
	private CharacterClass currentClass;
	private Card currentMonster;
	private ArrayList<Card> cards;
	private ArrayList<Card> playedCards;
	
	// Getters and Setters
	public int getLevel(){return level;}
	public Race getRace(){return race;}
	public int getGold(){return gold;}
	public CharacterClass getCharacterClass(){return currentClass;}
	public Card getCurrentMonster(){return currentMonster;}
	public ArrayList<Card> getCards(){return cards;}
	public ArrayList<Card> getPlayedCards(){return playedCards;}
	public void setLevel(int newLevel){level = newLevel;}
	public void setGold(int newGold){gold = newGold;}
	public void setRace(Race newRace){race = newRace;}
	public void setCharacterClass(CharacterClass newClass){currentClass = newClass;}
	public void setCurrentMonster(Card newMonster){currentMonster = newMonster;}
	public void setCards(ArrayList<Card> newCards){cards = newCards;}
	public void setPlayedCards(ArrayList<Card> newCards){playedCards = newCards;}
	
	// Constructor
	public Character(){
		level = 1;
		gold = 0;
		race = null;
		currentClass = null;
		currentMonster = null;
		cards = new ArrayList<Card>();
		playedCards = new ArrayList<Card>();
		
	}
	
	// Methods
	public void assignRace(Race newRace){
		setRace(race);
	}
	public void discardRace(){
		race = null;
	}
	public void assignClass(CharacterClass newClass){
		setCharacterClass(newClass);
	}
	public void dicardClass(){
		currentClass = null;
	}
	
	public void addCard(Card card){
		cards.add(card);
	}
	public void discard(Card card){
		cards.remove(card);
	}
	public void playCard(Card card){
		playedCards.add(card);
	}
	public void discardPlayCard(Card card){
		playedCards.remove(card);
	}
	public int combatStrength()
	{
		int combatStrength = level;
		
		// Code to calculate combat strength
		for(Card c : playedCards){
			TreasureCard strengthCard = (TreasureCard) c;
			combatStrength += strengthCard.getCombatAdvantage();
		}
		return combatStrength;
		
	}

	public int rollDie(){
		return Randomizer.RollDice(6);
	}
	public void buyLevel(){
		// Code for buying a level
	}
	public boolean runAway(int rollTarget){
		
		if(rollDie()>= rollTarget){
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	

}
