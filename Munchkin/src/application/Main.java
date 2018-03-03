package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox; // Needed for HBox object
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.GridPane; // needed for JavaFX GridPane object
import javafx.scene.control.Button;
import javafx.scene.control.Label; // needed for JavaFX Label object
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Insets; // needed for JavaFX Insets object
import javafx.geometry.Pos; // needed for JavaFX Pos object
import javafx.event.ActionEvent; // needed for JavaFX ActionEvent
import javafx.stage.Stage; // needed for JavaFX Stage object
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
	
	// Constants and global variables
	private final static int SCENE_WIDTH = 1260;
	private final static int SCENE_HEIGHT = 900;
	private final static int SPACING = 10;
	
	private final static int IMAGE_WIDTH = 120;
	private final static int IMAGE_HEIGHT = 260;
	
	private final static int FEEDBACK_HEIGHT = 60;
	
	private static GridPane grid;
	private static HBox handBox;
	private static HBox playBox;
	private static HBox topBox;
	private static VBox labelsBox;
	private static Label levelLabel;
	private static Label goldLabel;
	private static Label feedbackLabel;
	private static Button drawButton;
	//private static ImageView classImageView;
	//private static ImageView raceImageView;
	
	//defining the players
	private static Character playerOne;
	
	static int nextPlayer = 0;
	static int highestLevel = 0;
	static int goldPieces = 0;
	static boolean endTurn = false;
	static boolean helpCardPlayed = false;
	static boolean soldForDouble = false;
	static int doorClicks = 0;
	
	private static String WELCOME_MESSAGE = "Welcome to Munchkin! You may play as many treasure cards as you like.  You may only have one race" + 
									" \nor class at a time so if you play another it will replace the existing one.  Play or draw a monster to fight.";
	private static String MONSTER_MESSAGE = "Monster drawn!";
	private static String DEFEAT_MONSTER_MESSAGE = "Congratulations! You have defeated the monster and gained a level and its treasure!";
	private static String WIN_MESSAGE = "YOU HAVE WON!!";
	private static String ESCAPE_MESSAGE = "You escaped with a roll of ";
	private static String NEW_TURN_MESSAGE = "New Turn.  Play some cards or Kick the Door!";
	private static String GOLD_MESSAGE = "Gold Pieces: ";
	private static String LEVEL_MESSAGE = "Level: ";
	private static String TOO_MANY_CARDS_MESSAGE = "You have too many cards.  Please discard or play cards until you have ";
	private static String BUY_A_LEVEL_YES_MESSAGE = "Congratulations!  You just bought a level";
	private static String BUY_A_LEVEL_NO_MESSAGE = "\n You need 1000 gold to buy a level! Right-click a treasure card in your hand to sell it.";
	private static String HELP_CARD_MESSAGE = "Help card played.  Next treasure card will have double strength.";
	private static String CURSE_CARD_MESSAGE = "Cursed! Click the curse card to discard it.";
	private static String SOLD_ITEM_MESSAGE = "Sold an item";
	@Override
	public void start(Stage primaryStage) {
		grid = new GridPane();
		
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(SPACING);
		grid.setVgap(SPACING);
		grid.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left 
		
		primaryStage.setTitle("Munchkin");
		playerOne = new Character();
		
		labelsBox = createLabelsBox();
		levelLabel = new Label(LEVEL_MESSAGE + playerOne.getLevel());
		goldLabel = new Label(GOLD_MESSAGE + playerOne.getGold());
		feedbackLabel = new Label(WELCOME_MESSAGE);
		goldLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
			        if(playerOne.getCurrentMonster() == null){
			            int playerGold = playerOne.getGold();
			        	if( playerGold >= 1000){
			        		updateFeedbackLabel(BUY_A_LEVEL_YES_MESSAGE);
			        		playerOne.setGold(playerGold - 1000);
			        		playerOne.buyLevel();
			        		updateGoldPieces();
			        		updateLevel();
			        		if(playerOne.getLevel() >= 10){
			        			updateFeedbackLabel(WIN_MESSAGE);
			        			grid.setDisable(true);
			        		}
			        		
			        	} else {
			        		updateFeedbackLabel(feedbackLabel.getText() + BUY_A_LEVEL_NO_MESSAGE);
			        	}
		        	}		            
		        }
		    }
		});
		
		labelsBox.getChildren().addAll(levelLabel, goldLabel, feedbackLabel);
		
		topBox = createCardsBox();
		topBox.getChildren().addAll(labelsBox);
		grid.add(topBox, 0, 0);
		
		ImageView imageView = new ImageView();
		playBox = createCardsBox();
		playBox.getChildren().add(0, imageView);
		grid.add(playBox, 0, 1);
		
		handBox = createCardsBox();
		grid.add(handBox, 0, 2);
		
		
		drawButton = new Button("Kick Door");
		drawButton.setOnAction((ActionEvent ae) -> {
			if(drawButton.getText() == "Kick Door"){
				kickDoor();
			}
			else if(drawButton.getText() == "Run"){
				run();
			}
			else if(drawButton.getText() == "Fight!"){
				fight();
				
			}
			else if(drawButton.getText() == "End Turn"){
				endTurn();
			}
		});
    	
		
		grid.add(drawButton, 0, 4);
	
		// Create the scene with the grid, add it to the stage, and show our work.
		Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setScene(scene);
        primaryStage.show();
        
        // Deal starting cards to player.
        setupGame();
	}
	public static void endTurn(){
		int maxSize = 5;
		if(playerOne.getRace() != null){
			RACE_ABILITY[] playerAbilities = playerOne.getRace().getAbilities(); 
			for(RACE_ABILITY a : playerAbilities){
				if(a == RACE_ABILITY.CARRY_EXTRA_CARD){
					maxSize = 6;
				}
			}
		}
		if(playerOne.getCards().size() <= maxSize){
			doorClicks = 0;
			endTurn = false;
			soldForDouble = false;
			updateFeedbackLabel(NEW_TURN_MESSAGE);
			drawButton.setText("Kick Door");
		} else {
			updateFeedbackLabel(TOO_MANY_CARDS_MESSAGE + maxSize);
		}
		
	}
	public static void fight(){
		
		MonsterCard defeatMonster = (MonsterCard)playerOne.getCurrentMonster();
		int treasure = defeatMonster.getTreasureCards();
		
		updateFeedbackLabel(DEFEAT_MONSTER_MESSAGE + " " + Integer.toString(treasure)+ " treasure cards.");
		playerOne.buyLevel();
		updateLevel();
		removeFromTopBox(playerOne.getCurrentMonster());
		playerOne.setCurrentMonster(null);
		if(playerOne.getLevel() >= 10){
			updateFeedbackLabel(WIN_MESSAGE);
			grid.setDisable(true);
		}
		Card addCard;
		for(int i = 0; i < treasure; i++)
        {
        	addCard = dealTreasureCard();
        	addToHand(addCard);
        	playerOne.addCard(addCard);
        }
		drawButton.setText("End Turn");
		endTurn = true;
	}
	public static void run(){
		
		int rollTarget = 5;
		boolean rollAgain = false;
		
		if(playerOne.getCharacterClass() != null){
			CLASS_ABILITY[] playerClassAbilities = playerOne.getCharacterClass().getAbilities(); 
			for(CLASS_ABILITY a : playerClassAbilities){
				if(a == CLASS_ABILITY.DISCARD_RUN_AWAY){
					
				}
			}
		}
		
		if(playerOne.getRace() != null){
			RACE_ABILITY[] playerRaceAbilities = playerOne.getRace().getAbilities(); 
			for(RACE_ABILITY a : playerRaceAbilities){
				if(a == RACE_ABILITY.EASIER_RUN_AWAY){
					rollTarget = 4;
				}
				if(a == RACE_ABILITY.EXTRA_RUN_AWAY){
					rollAgain = true;
				}
			}
		}
		int roll = playerOne.rollDie();
		if(rollAgain && roll >= rollTarget){
			updateFeedbackLabel(ESCAPE_MESSAGE + roll);
		} else {
			roll = playerOne.rollDie();
			if( roll >= rollTarget){
				String update = ESCAPE_MESSAGE + roll;
				if(rollAgain){
					update += " on second roll!";
				}
				updateFeedbackLabel(update);
			} else {
				doBadStuff(roll);
			}	
		}
		removeFromTopBox(playerOne.getCurrentMonster());
		playerOne.setCurrentMonster(null);
		
			
		drawButton.setText("End Turn");
		endTurn = true;
	}
	public static void kickDoor(){
		
		CARD_TYPE cardType;
		Card showMeACard;	
		showMeACard = dealDoorDeckCard();
		cardType = showMeACard.getCardType();
		doorClicks++;
		if(doorClicks > 1){
			drawButton.setText("End Turn");
			endTurn = true;
		}
		if(cardType == CARD_TYPE.MONSTER){
			if(playerOne.getCurrentMonster() == null){
			addToTopBox(showMeACard);
			playerOne.setCurrentMonster(showMeACard);
			monsterCard(showMeACard);
			
			}
		} else if(cardType == CARD_TYPE.CURSE){
			//Apply CURSE - code below will change
			addToHand(showMeACard);
			playerOne.addCard(showMeACard);
			curseCard(showMeACard);
		} else {
			addToHand(showMeACard);
			playerOne.addCard(showMeACard);
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private static void setupGame(){
		Card addCard;
		
		for(int i = 0; i < 4; i++)
        {
			addCard = dealDoorDeckCard();
			addToHand(addCard);
			playerOne.addCard(addCard);
        	
        }
        for(int i = 0; i < 4; i++)
        {
        	addCard = dealTreasureCard();
        	addToHand(addCard);
        	playerOne.addCard(addCard);
        }
	}
	private static void curseCard(Card curseCard) {
		//this method will be used when there is a curse card that has been drawn
		// It will place the curse on the certain player that drew the card.
		CurseCard processCurse = (CurseCard)curseCard;
		
		switch(processCurse.getType()){
		case LOSE_ONE_ITEM:
			if(playerOne.getPlayedCards().size()>0){
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " One item removed!");
				playerOne.discardPlayCard(playerOne.getPlayedCards().get(0));
				ObservableList<Node> elements = playBox.getChildren();
					int index = -1;
					for (int i = 0; i < elements.size(); i++) {
						if (elements.get(i).getUserData() != null) {
							index = i;
							break;
						}
					}
				removeFromPlay((Label)elements.get(index));
			} else {
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " No items to lose!");
			}
			break;
		case LOSE_TWO_ITEMS:
			if(playerOne.getPlayedCards().size()>0){
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " One item removed!");
				playerOne.discardPlayCard(playerOne.getPlayedCards().get(0));
				ObservableList<Node> elements = playBox.getChildren();
					int index = -1;
					for (int i = 0; i < elements.size(); i++) {
						if (elements.get(i).getUserData() != null) {
							index = i;
							break;
							
						}
					}
				
				removeFromPlay((Label)elements.get(index));
				
				if(playerOne.getPlayedCards().size()>0){
					updateFeedbackLabel(CURSE_CARD_MESSAGE + " Two items removed!");
					playerOne.discardPlayCard(playerOne.getPlayedCards().get(0));
					elements = playBox.getChildren();
					index = -1;
					for (int i = 0; i < elements.size(); i++) {
						if (elements.get(i).getUserData() != null) {
							index = i;
							break;
							
						}
					}
					removeFromPlay((Label)elements.get(index));
				}
			} else {
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " No items to lose!");
			}
			break;
		case LOSE_RACE_BECOME_HUMAN:
			if(playerOne.getRace() != null){
				
				playerOne.discardRace();
				ImageView blankView = new ImageView();
				setRaceCard(blankView,false);
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " You lose your race!");
			} else {
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " No race to lose.");
			}
			break;
		case LOSE_RACE_DRAW_NEW_RACE:
			if(playerOne.getRace() != null){
				Card newRace = dealRaceCard();
				Image cardImage = new Image(newRace.getImageFile(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
				ImageView raceImageView = new ImageView(cardImage);
				setRaceCard(raceImageView, false);
				RaceCard setRace = (RaceCard)newRace;
				playerOne.setRace(setRace.getType());
				updateFeedbackLabel(CURSE_CARD_MESSAGE + "Race changed to " + setRace.getType().toString());
			} else {
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " No race to lose.");
			}
			break;
		case LOSE_CLASS:
			if(playerOne.getCharacterClass() != null){
				playerOne.discardClass();
				ImageView blankView = new ImageView();
				setClassCard(blankView,false);
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " You lose your class!");
			} else {
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " No class to lose.");
			}
			break;
		case LOSE_LEVEL:
			if(playerOne.getLevel()> 1){
				playerOne.setLevel(playerOne.getLevel() - 1);
				updateLevel();
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " You lost a level!");
			} else {
				updateFeedbackLabel(CURSE_CARD_MESSAGE + " You can't lose a level.");
			}
			
			break;
		}
		
	}
	
	private static void doBadStuff(int roll){
		MonsterCard badCard = (MonsterCard)playerOne.getCurrentMonster();
		switch(badCard.getPenalty()){
		case LOSE_ONE_LEVEL:
			if(playerOne.getLevel()> 1){
				playerOne.setLevel(playerOne.getLevel() - 1);
				updateLevel();
				updateFeedbackLabel("You rolled a " + roll + " You lost a level!");
			} else {
				updateFeedbackLabel("You rolled a " + roll + " You can't lose a level.");
			}
			
			break;
		case LOSE_TWO_LEVELS:
			if(playerOne.getLevel() > 2){
			playerOne.setLevel(playerOne.getLevel() - 2);
			updateLevel();
			updateFeedbackLabel("You rolled a " + roll + " You lost 2 levels!");
			} else {
			updateFeedbackLabel("You rolled a " + roll + " You can't lose 2 levels");
			}
			break;
		case LOSE_BIGGEST_ITEM:
			if(playerOne.getPlayedCards().size() > 0){
				Card removeCard = findHighestValueCard();
				Label removeLabel = findHighestValueTreasure();
				playerOne.discardPlayCard(removeCard);
				removeFromPlay(removeLabel);
				updateFeedbackLabel("You rolled a " + roll + " Biggest item removed!");
			}
		case LOSE_TWO_ITEMS:
			if(playerOne.getPlayedCards().size()>0){
				updateFeedbackLabel("You rolled a " + roll + " One item removed!");
				playerOne.discardPlayCard(playerOne.getPlayedCards().get(0));
				ObservableList<Node> elements = playBox.getChildren();
					int index = -1;
					for (int i = 0; i < elements.size(); i++) {
						if (elements.get(i).getUserData() != null) {
							index = i;
							break;
							
						}
					}
				
				removeFromPlay((Label)elements.get(index));
				if(playerOne.getPlayedCards().size()>0){
					updateFeedbackLabel("You rolled a " + roll + " Two items removed!");
					playerOne.discardPlayCard(playerOne.getPlayedCards().get(0));
					elements = playBox.getChildren();
					index = -1;
					for (int i = 0; i < elements.size(); i++) {
						if (elements.get(i).getUserData() != null) {
							index = i;
							break;
							
						}
					}
				
					removeFromPlay((Label)elements.get(index));
				}
			} else {
				updateFeedbackLabel("You rolled a " + roll + " No items to lose!");
			}
			break;
		case DEATH:
			updateFeedbackLabel("You rolled a " + roll + " YOU DIED!!! GAME OVER!!");
			grid.setDisable(true);
			break;
		}
		
	}
	
	private static void monsterCard(Card card) {
		//monster card will have the level of the monster as well as the treasure
		// once the monster is defeated. It will have the penalty if you can't defeat
		// the monster as well as the vulnerability of the monster.
		//This method will also call the card method in the case the player has to 
		// draw a new card.
		int abilityAdjustment = 0;
		if(playerOne.getCharacterClass() != null){
			CLASS_ABILITY[] playerClassAbilities = playerOne.getCharacterClass().getAbilities(); 
			for(CLASS_ABILITY a : playerClassAbilities){
				if(a == CLASS_ABILITY.BERSERK){
					abilityAdjustment = 1;
				}
			}
		}
		
		MonsterCard fightCard = (MonsterCard)card;
		int monster = fightCard.getLevel();
		int player = playerOne.combatStrength();
		updateFeedbackLabel(MONSTER_MESSAGE + " Your Strength: " + Integer.toString(player) + " Monster Strength: " + Integer.toString(monster));
		if(monster >= player + abilityAdjustment){
			
			
			drawButton.setText("Run");
		}
		else
		{
			drawButton.setText("Fight!");
			
		}	
	}
	
	private static Card dealDoorDeckCard() {
		CardFactory gimmeACard = new MunchkinCardFactory();
		CARD_TYPE cardType;
		Card returnCard;
		//Image cardViewImage;
		int switchMe = Randomizer.RollDice(100);
		if(switchMe <= 15){
			cardType = CARD_TYPE.RACE;
		} else if(switchMe <= 30){
			cardType = CARD_TYPE.CLASS;
		} else if(switchMe <= 60){
			cardType = CARD_TYPE.MONSTER;
		} else if(switchMe < 90){
			cardType = CARD_TYPE.CURSE;
		} else {
			cardType = CARD_TYPE.HELP;
		}
		returnCard = gimmeACard.orderCard(cardType);
		return returnCard;
	}
	
	private static Card dealTreasureCard()
	{
		CardFactory gimmeACard = new MunchkinCardFactory();
		CARD_TYPE cardType = CARD_TYPE.TREASURE;
		Card returnCard = gimmeACard.orderCard(cardType);
		return returnCard;
	}
	private static Card dealRaceCard()
	{
		CardFactory gimmeACard = new MunchkinCardFactory();
		CARD_TYPE cardType = CARD_TYPE.RACE;
		Card returnCard = gimmeACard.orderCard(cardType);
		return returnCard;
	}
	
	private static void addToHand(Card card) {
		// Receive a Card object, get its filename and create an Image and ImageView.
		Image cardImage = new Image(card.getImageFile(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
		Button cardButton = new Button();
		cardButton.setGraphic(new ImageView(cardImage));
		
	
		cardButton.setContentDisplay(ContentDisplay.TOP);
		cardButton.setText(card.getImageLabel());
		
		handBox.getChildren().add(cardButton);
		// missing event listener for the card.
		cardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent event) {
            MouseButton button = event.getButton();
            if(button==MouseButton.PRIMARY){
            	switch(card.getCardType()){
    			case CLASS:
    				if(playerOne.getCharacterClass() == null){
    					ImageView classImageView = (ImageView) cardButton.getGraphic();
    					setClassCard(classImageView, false);
    					ClassCard setClass = (ClassCard)card;
    					removeFromHand(cardButton);
    					playerOne.setCharacterClass(setClass.getType());
    					playerOne.discard(card);
    				}else {
    					ImageView classImageView = (ImageView) cardButton.getGraphic();
    					setClassCard(classImageView, false);
    					ClassCard setClass = (ClassCard)card;
    					removeFromHand(cardButton);
    					playerOne.setCharacterClass(setClass.getType());
    					playerOne.discard(card);
    				}
    				break;
    			case RACE:
    				if(playerOne.getRace() == null){
    					ImageView raceImageView = (ImageView) cardButton.getGraphic();
    					setRaceCard(raceImageView, true);
    					removeFromHand(cardButton);
    					RaceCard setRace = (RaceCard)card;
    					playerOne.setRace(setRace.getType());
    					playerOne.discard(card);
    				} else {
    					ImageView raceImageView = (ImageView) cardButton.getGraphic();
    					setRaceCard(raceImageView, false);
    					removeFromHand(cardButton);
    					RaceCard setRace = (RaceCard)card;
    					playerOne.setRace(setRace.getType());
    					playerOne.discard(card);
    				}
    				break;
    			case MONSTER:
    				if(playerOne.getCurrentMonster() == null && !endTurn){
    					addToTopBox(card);
    					//updateFeedbackLabel(MONSTER_MESSAGE);
    					playerOne.setCurrentMonster(card);
    					removeFromHand(cardButton);
    					playerOne.discard(card);
    					monsterCard(card);
    					}
    				break;
    			case CURSE:
    				removeFromHand(cardButton);
    				playerOne.discard(card);
    				break;
    			case TREASURE:
    				TreasureCard treasure = (TreasureCard) card;
    				if(helpCardPlayed){
    					treasure.setCombatAdvantage(treasure.getCombatAdvantage() * 2);
    					cardButton.setText("STR: " + treasure.getCombatAdvantage() + "  Gold: " + treasure.getGoldPieces());
    					helpCardPlayed = false;
    				}
    				cardButton.setUserData(treasure.getGoldPieces());
    				addToPlay(cardButton);
    				playerOne.playCard(treasure);
    				playerOne.discard(card);
    				
    				if(playerOne.getCurrentMonster() != null){
    					monsterCard(playerOne.getCurrentMonster());
    				}
    				break;
    			case HELP:
    				helpCardPlayed = true;
    				removeFromHand(cardButton);
    				playerOne.discard(card);
    				updateFeedbackLabel(HELP_CARD_MESSAGE);
    				break;
            	}
            }else if(button==MouseButton.SECONDARY){
            	switch(card.getCardType()){
            		case TREASURE:
            			if(playerOne.getCurrentMonster() == null){
	            			boolean doublePrice = false;
	            			TreasureCard treasure = (TreasureCard) card;
	            			if(playerOne.getRace() != null){
	            				RACE_ABILITY[] playerRaceAbilities = playerOne.getRace().getAbilities(); 
	            				for(RACE_ABILITY a : playerRaceAbilities){
	            					if(a == RACE_ABILITY.DOUBLE_PRICE_ITEM){
	            						doublePrice = true;
	            					}
	            				}
	            			}
	            			int addedGold = treasure.getGoldPieces();
	            			if(doublePrice & !soldForDouble){
	            				
	            				playerOne.setGold(playerOne.getGold() + (addedGold * 2));
	            				updateFeedbackLabel(SOLD_ITEM_MESSAGE + " for double price!");
	            				soldForDouble = true;
	            			} else {
	            				playerOne.setGold(playerOne.getGold() + addedGold);
	            				updateFeedbackLabel(SOLD_ITEM_MESSAGE + " for " + addedGold + " gold pieces.");
	            				
	            			}
	            			updateGoldPieces();
	            			playerOne.discard(card);
	            			removeFromHand(cardButton);
            			}
            			break;
            		case HELP:
            		case CURSE:
            		case MONSTER:
            		case RACE:
            		case CLASS:
            			playerOne.discard(card);
            			removeFromHand(cardButton);
            			break;
            	}	       			
            }
            }
        });
         
		
	}
	private static Card findHighestValueCard(){
		Card returnCard = null;
		int max = -1;
		for(Card c : playerOne.getPlayedCards()){
			TreasureCard checkCard = (TreasureCard)c;
			int value = checkCard.getGoldPieces();
			if( value > max){
				max = value;
				returnCard = c;
			}
		}
		
		return returnCard;
	}
	
	private static Label findHighestValueTreasure() {
		ObservableList<Node> elements = playBox.getChildren();
		int index = -1;
		int max = -1;
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getUserData() != null) {
				int value = (int) elements.get(i).getUserData();
				if (value > max) {
					max = value;
					index = i;
				}
			}
		}
		if (index == -1) {
			return null;
		}
		return (Label)playBox.getChildren().get(index);
	}
	
	
	private static void addToPlay(Button cardButton) {
		removeFromHand(cardButton);
		
		Label cardLabel = new Label(cardButton.getText());
		ImageView cardImageView =  (ImageView)cardButton.getGraphic();
		cardLabel.setGraphic(cardImageView);
		cardLabel.setContentDisplay(ContentDisplay.TOP);
		cardLabel.setUserData(cardButton.getUserData());
		playBox.getChildren().add(cardLabel);
		
	}
	
	private static void addToTopBox(Card card) {
		// Receive a Card object, get its filename and create an Image and ImageView.
		Image cardImage = new Image(card.getImageFile(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
		ImageView cardImageView = new ImageView(cardImage);
		Label cardLabel = new Label(card.getImageLabel());
		cardLabel.setGraphic(cardImageView);
		cardLabel.setContentDisplay(ContentDisplay.TOP);
		
		topBox.getChildren().add(cardLabel);
	}
	private static void removeFromTopBox(Card card){
		Image cardImage = new Image(card.getImageFile(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
		ImageView cardImageView = new ImageView(cardImage);
		Label cardLabel = new Label(card.getImageLabel());
		cardLabel.setGraphic(cardImageView);
		cardLabel.setContentDisplay(ContentDisplay.TOP);
		
		topBox.getChildren().remove(1);
	}
	
	private static void setClassCard(ImageView imageView, boolean firstTime) {
	
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            updateFeedbackLabel("CLASS CLICKED");
        	playerOne.discardClass();
        	ImageView blankImage = new ImageView();
        	playBox.getChildren().set(0, blankImage);
            
});
		if (firstTime) {
			playBox.getChildren().add(0, imageView);
		}
		else {
			playBox.getChildren().set(0, imageView);
		}
		
		   
		
	}
	
	private static void setRaceCard(ImageView imageView, boolean firstTime) {
		
		if (firstTime) {
			playBox.getChildren().add(1, imageView);
		}
		else {
			playBox.getChildren().set(1, imageView);
		}
		imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            
		        	playerOne.discardRace();
		        	ImageView blankImage = new ImageView();
		        	playBox.getChildren().set(1, blankImage);
		            
		        }
		    }
		});
	}
	
	private static void removeFromHand(Button cardButton) {
		handBox.getChildren().remove(cardButton);
	}
	
	private static void removeFromPlay(Label cardLabel){
		playBox.getChildren().remove(cardLabel);
	}
	
	  // Create the HBox for the images and return it to add it to the grid.
    private static HBox createCardsBox() {
    	HBox hbImageContainer = new HBox();        
		hbImageContainer.setPrefWidth(9 * IMAGE_WIDTH);
		hbImageContainer.setPrefHeight(IMAGE_HEIGHT + SPACING);
		hbImageContainer.setSpacing(SPACING);
		hbImageContainer.setAlignment(Pos.CENTER);  // Set the alignment of the HBox
		
		return hbImageContainer;
    }
    
    private static VBox createLabelsBox() {
    	VBox labelsContainer = new VBox();
    	labelsContainer.setPrefHeight(IMAGE_HEIGHT + SPACING);
    	labelsContainer.setSpacing(SPACING);
    	labelsContainer.setAlignment(Pos.TOP_LEFT);
    	
    	return labelsContainer; 
    }
    
    private static void updateLevel() {
    	levelLabel.setText("Level: " + playerOne.getLevel());
    }
    
    private static void updateGoldPieces() {
    	goldLabel.setText("Gold pieces: " + playerOne.getGold());
    }
    
    private static void updateFeedbackLabel(String feedback) {
    	feedbackLabel.setText(feedback);
    }
}
