
//implementation of a custom control for javafx
//imports for the class
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

//class definition for a custom control
class CustomControl extends Control {
	// constructor for the class
	public CustomControl() {
		
		// set a default skin and generate a game board
		setSkin(new CustomControlSkin(this));
		LightsOutBoard = new LightsOutBoard();
		getChildren().add(LightsOutBoard);

		// add a mouse clicked listener that will try to place a piece
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			// overridden handle method
			@Override
			public void handle(MouseEvent event) {
				LightsOutBoard.placePiece(event.getX(), event.getY());
			}
		});

		setOnKeyPressed(new EventHandler<KeyEvent>() {
			// overridden handle method
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE)
					LightsOutBoard.initialization();
			}
		});

	}

	// override the resize method
	@Override
	public void resize(double width, double height) {
		// update the size of the rectangle
		super.resize(width, height);
		LightsOutBoard.resize(width, height);
	}

	// private fields of the class
	private LightsOutBoard LightsOutBoard;
	private int player;
}
