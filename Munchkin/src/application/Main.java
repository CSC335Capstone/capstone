package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	@Override
	public void start(Stage primaryStage) {
		grid = new GridPane();
		
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(SPACING);
		grid.setVgap(SPACING);
		grid.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left (TRouBLe)
		
		primaryStage.setTitle("Munchkin");
		playerOne = new Character();
		
		labelsBox = createLabelsBox();
		levelLabel = new Label("Level: " + playerOne.getLevel());
		goldLabel = new Label("Gold Pieces: " + playerOne.getGold());
		feedbackLabel = new Label("Welcome to Munchkin!");
		
		labelsBox.getChildren().addAll(levelLabel, goldLabel, feedbackLabel);
		
		topBox = createCardsBox();
		topBox.getChildren().addAll(labelsBox);
		grid.add(topBox, 0, 0);
		
		playBox = createCardsBox();
		grid.add(playBox, 0, 1);
		
		handBox = createCardsBox();
		grid.add(handBox, 0, 2);
		
		drawButton = new Button("Kick Door");
		drawButton.setOnAction((ActionEvent ae) -> {
			if(drawButton.getText() == "Kick Door"){
				CARD_TYPE cardType;
				Card showMeACard;	
				showMeACard = dealDoorDeckCard();
				cardType = showMeACard.getCardType();
				
				if(cardType == CARD_TYPE.MONSTER){
					if(playerOne.getCurrentMonster() == null){
					addToTopBox(showMeACard);
					playerOne.setCurrentMonster(showMeACard);
					monsterCard(showMeACard);
					}
				} else if(cardType == CARD_TYPE.CURSE){
					//Apply CURSE - code below will change
					addToHand(showMeACard);
				} else {
					addToHand(showMeACard);
				}
				drawButton.setDisable(true);
			}
			else if(drawButton.getText() == "Run"){
				
			}
			else if(drawButton.getText() == "Fight!"){
				
			}
			else if(drawButton.getText() == "End Turn"){
				//End turn code
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
	
	private static void monsterCard(Card card) {
		//monster card will have the level of the monster as well as the treasure
		// once the monster is defeated. It will have the penalty if you can't defeat
		// the monster as well as the vulnerability of the monster.
		//This method will also call the card method in the case the player has to 
		// draw a new card.
		MonsterCard fightCard = (MonsterCard)card;
		
		if(fightCard.getLevel() >= playerOne.combatStrength()){
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
	
	private static void addToHand(Card card) {
		// Receive a Card object, get its filename and create an Image and ImageView.
		Image cardImage = new Image(card.getImageFile(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
		Button cardButton = new Button();
		cardButton.setGraphic(new ImageView(cardImage));
	
		cardButton.setContentDisplay(ContentDisplay.TOP);
		cardButton.setText(card.getImageLabel());
		
		handBox.getChildren().add(cardButton);
		// missing event listener for the card.
		cardButton.setOnAction((ActionEvent ae) -> {
			switch(card.getCardType()){
			case CLASS:
				if(playerOne.getCharacterClass() == null){
					ImageView classImageView = (ImageView) cardButton.getGraphic();
					setClassCard(classImageView, true);
					ClassCard setClass = (ClassCard)card;
					removeFromHand(cardButton);
					
					playerOne.setCharacterClass(setClass.getType());
					//ClearClass();
					//addToClass(card);
					playerOne.discard(card);
					//increaseLevel();
				}else {
					ImageView classImageView = (ImageView) cardButton.getGraphic();
					setClassCard(classImageView, false);
					ClassCard setClass = (ClassCard)card;
					removeFromHand(cardButton);
					
					playerOne.setCharacterClass(setClass.getType());
					//ClearClass();
					//addToClass(card);
					playerOne.discard(card);
					//increaseLevel();
					
				}
				
					
				break;
			case RACE:
				if(playerOne.getRace() == null){
					ImageView raceImageView = (ImageView) cardButton.getGraphic();
					setRaceCard(raceImageView, true);
					removeFromHand(cardButton);
					
					RaceCard setRace = (RaceCard)card;
					playerOne.setRace(setRace.getType());
					//addToPlay(cardButton);
					//ClearRace();
					//addToRace(card);
					playerOne.discard(card);
					//increaseLevel();
				} else {
					ImageView raceImageView = (ImageView) cardButton.getGraphic();
					setRaceCard(raceImageView, true);
					removeFromHand(cardButton);
					
					RaceCard setRace = (RaceCard)card;
					playerOne.setRace(setRace.getType());
					//addToPlay(cardButton);
					//ClearRace();
					//addToRace(card);
					playerOne.discard(card);
					//increaseLevel();
					
				}
				break;
			case MONSTER:
				if(playerOne.getCurrentMonster() == null){
					addToTopBox(card);
					playerOne.setCurrentMonster(card);
					removeFromHand(cardButton);
					monsterCard(card);
					}
				break;
			case CURSE:
				//applyCurse();
				break;
			case TREASURE:
				TreasureCard treasure = (TreasureCard) card;
				cardButton.setUserData(treasure.getGoldPieces());
				if(!sellTreasure(card)){
					addToPlay(cardButton);
				}
				//if(playerOne.getCurrentMonster() != null){
					//monsterCard(playerOne.getCurrentMonster());
				//}
				break;
			case HELP:
				//help card code
				break;
			}
		});
	}
	
	private static ImageView findHighestValueTreasure() {
		ImageView[] elements = (ImageView[]) playBox.getChildren().toArray();
		int index = -1;
		int max = -1;
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].getUserData() != null) {
				int value = (int) elements[i].getUserData();
				if (value > max) {
					max = value;
					index = i;
				}
			}
		}
		if (index == -1) {
			return null;
		}
		return elements[index];
	}
	
	private static boolean sellTreasure(Card card){
		return false;
	}
	
	private static boolean updateRace(Card card){
		boolean returnVal = false;
		if(playerOne.getRace()== null)
		{
			returnVal = true;
		}else{
			//Confirm user wants to replace race
		}
		return returnVal;
	}
	
	private static boolean updateClass(Card card){
		boolean returnVal = false;
		if(playerOne.getClass() == null){
			returnVal = true;
		}else {
			// Confirm user wants to replace class
		}
		return returnVal;
	}
	
	private static void addToPlay(Button cardButton) {
		removeFromHand(cardButton);
		
		Label cardLabel = new Label(cardButton.getText());
		ImageView cardImageView =  (ImageView)cardButton.getGraphic();
		cardLabel.setGraphic(cardImageView);
		cardLabel.setContentDisplay(ContentDisplay.TOP);
		playBox.getChildren().add(cardLabel);
		cardButton.setMouseTransparent(true);
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
	
	private static void setClassCard(ImageView imageView, boolean firstTime) {
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
	}
	
	private static void removeFromHand(Button cardButton) {
		handBox.getChildren().remove(cardButton);
	}
	
	private static void removeFromPlay(ImageView cardImage){
		playBox.getChildren().remove(cardImage);
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
