// JavaFX example that showcases how to build an entirely custom control
// that fits into the JavaFX model.
// imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// class definition
public class LightsOutApplication extends Application {

	// overridden init method
	@Override
	public void init() {
		// initialise the stack pane
		sp_mainlayout = new StackPane();


	}

	// overridden start method
	public void start(Stage primaryStage) {
		

		// set a size, title and a scene on the main window. show it when
		// ready
		primaryStage.setTitle("Lights Out - Tim Mencin");
		
		mainStage = new Scene(sp_mainlayout, 800, 600);

		VBox layout = new VBox(20);
		layout.setPadding(new Insets(100));
		Welcomescene = new Scene(layout, 600, 600);
		Label label1 = new Label("Welcome to Lights Out!");
		Label label2 = new Label("PLEASE SELECT Play Game button to start!");

		// Create buttons
		Button Play = new Button("Play game!");
		
		Label label3 = new Label("Instructions on playing the game: ");
		Label label4 = new Label("In order to win you must turn off all the lights");
		
		layout.getChildren().addAll(label1, label2, Play,label3,label4);
		primaryStage.setScene(Welcomescene);
		primaryStage.show();
		

		// Clicking will set the players
		Play.setOnAction(e -> { // lamba expression this button will pass 2 to
									// the custom control and intitlize it
			cc_custom = new CustomControl();
			sp_mainlayout.getChildren().add(cc_custom);
			primaryStage.setScene(mainStage); // change sceen
		});


	}

	// overridden stop method
	public void stop() {
	}

	// entry point into our program to launch our JavaFX application
	public static void main(String[] args) {
		launch(args);
	}

	// private fields for this class
	private StackPane sp_mainlayout;
	private CustomControl cc_custom;

	// two screens variables
	Scene mainStage, Welcomescene;
}
