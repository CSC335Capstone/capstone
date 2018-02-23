package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	//defining the players
	private static Character playerOne;
	private static Character playerTwo;
	private static Character playerThree;
	private static Character playerFour;
	//array to hold all the players
	private static Object[] players = {playerOne, playerTwo, playerThree, playerFour};
	
	static int nextPlayer = 0;
	static int highestLevel = 0;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
