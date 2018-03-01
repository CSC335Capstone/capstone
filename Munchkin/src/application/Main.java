package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox; // Needed for HBox object
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	final int SCENE_WIDTH = 1260;
	final int SCENE_HEIGHT = 900;
	final int SPACING = 10;
	
	final int IMAGE_WIDTH = 120;
	final int IMAGE_HEIGHT = 260;
	
	final int FEEDBACK_HEIGHT = 60;
	Label feedbackLabel;
	
	GridPane grid;
	HBox handBox;
	HBox playBox;
	HBox topBox;
	
	//defining the players
	private static Character playerOne;
	
	static int nextPlayer = 0;
	static int highestLevel = 0;
	
	@Override
	public void start(Stage primaryStage) {
	
		grid = new GridPane();
		
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(SPACING);
		grid.setVgap(SPACING);
		grid.setPadding(new Insets(25, 25, 25, 25)); // Top Right Bottom Left (TRouBLe)
		
		primaryStage.setTitle("Munchkin");
		
		handBox = createCardsBox();
		grid.add(handBox, 0, 2);
		
		playBox = createCardsBox();
		grid.add(playBox, 0, 1);
		
		topBox = createCardsBox();
		grid.add(topBox, 0, 0);
	
		//VBox root = new VBox();
		//ImageView cardView = new ImageView();
		Button drawButton = new Button("Press Me");
		
		CardFactory gimmeACard = new MunchkinCardFactory();
		
		drawButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override public void handle(ActionEvent e) {
    			
    			Card showMeACard;
    			//Image cardViewImage;
    			int switchMe = Randomizer.RollDice(100);
    			if(switchMe <= 15){
    				showMeACard = gimmeACard.orderCard("Race");
    			} else if(switchMe <= 30){
    			    showMeACard = gimmeACard.orderCard("Class");
    			} else if(switchMe <= 60){
    				showMeACard = gimmeACard.orderCard("Curse");
    			} else if(switchMe <= 100){
    				showMeACard = gimmeACard.orderCard("Treasure");
    			} else {
    				showMeACard = null;
    			}
    		
    			addToHand(showMeACard);
    			//cardViewImage = new Image(showMeACard.getImageFile());
    			//cardView.setImage(cardViewImage);
    		}
    	});
		
		grid.add(drawButton, 0, 4);
	
		// Create the scene with the grid, add it to the stage, and show our work.
		Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	private void addToHand(Card card) {
		// Receive a Card object, get it's filename and create an Image and ImageView.
		Image cardImage = new Image(card.getImageFile(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
		Button cardButton = new Button("", new ImageView(cardImage));
		handBox.getChildren().add(cardButton);
		// missing event listener for the card.
		// Allow the button to be selected and unselected.
		cardButton.setOnAction((ActionEvent ae) -> {
			addToPlay(cardButton);
		});
	}
	
	private void addToPlay(Button cardButton) {
		removeFromHand(cardButton);
		playBox.getChildren().add(cardButton);
	}
	
	private void addToTopBox(Card card) {
		// Receive a Card object, get it's filename and create an Image and ImageView.
		Image cardImage = new Image(card.getImageFile(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
		ImageView cardImageView = new ImageView(cardImage);
		topBox.getChildren().add(cardImageView);
	}
	
	private void removeFromHand(Button cardButton) {
		handBox.getChildren().remove(cardButton);
	}
	
	  // Create the HBox for the images and return it to add it to the grid.
    private HBox createCardsBox() {
    	HBox hbImageContainer = new HBox();        
		hbImageContainer.setPrefWidth(9 * IMAGE_WIDTH);
		hbImageContainer.setPrefHeight(IMAGE_HEIGHT + SPACING);
		hbImageContainer.setSpacing(SPACING);
		hbImageContainer.setAlignment(Pos.CENTER);  // Set the alignment of the HBox
		
		return hbImageContainer;
    }
}
