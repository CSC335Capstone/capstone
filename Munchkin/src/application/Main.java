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


public class Main extends Application {
	
	//defining the players
	private static Character playerOne;
	private final static double CARD_HEIGHT = 300;
	private final static double CARD_WIDTH = 175;
	private final static double SCENE_HEIGHT = 400;
	private final static double SCENE_WIDTH = 275;
	
	static int nextPlayer = 0;
	static int highestLevel = 0;
	@Override
	public void start(Stage primaryStage) {
	
	
		VBox root = new VBox();
		ImageView cardView = new ImageView();
		Button drawButton = new Button("Press Me");
		
		CardFactory gimmeACard = new MunchkinCardFactory();
		
		
		drawButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override public void handle(ActionEvent e) {
    			
    			Card showMeACard;
    			Image cardViewImage;
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
    		
    			cardViewImage = new Image(showMeACard.getImageFile());
    			cardView.setImage(cardViewImage);
    		}
    	});
		
		 
		
		cardView.setFitHeight(CARD_HEIGHT);
		cardView.setFitWidth(CARD_WIDTH);
		
	
		
		root.getChildren().addAll(cardView, drawButton);
		Scene scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
