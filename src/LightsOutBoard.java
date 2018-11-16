


import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
//an implementation of the XO board and the game logic
//imports necessary for this class
import javafx.scene.layout.VBox;
//an implementation of the XO board and the game logic
//imports necessary for this class
import javafx.scene.layout.Pane;

//class definition for drawing a game board
class LightsOutBoard extends Pane {
	// constructor for the class
	public LightsOutBoard() {

		// initialise the boards
		board = new int[5][5];
		renders = new Piece[5][5];

		// Calling the intilization method
		initialization();
		
	}

	// Intilization method this method placed the LightOFF board and dynamicly set
	// up the board based on the amount of players
	public void initialization() {
                
        //Calling the reset game so that all renders are removed
		resetGame();


	}

	// we have to override resizing behaviour to make our view appear properly
	@Override
	public void resize(double width, double height) {
		// call the superclass method first
		super.resize(width, height);

		// figure out the width and height of a cell
		cell_width = width / 5.0;
		cell_height = height / 5.0;
		// we need to reset the sizes and positions of all Pieces that were
		// placed
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i][j] != 0) {
					renders[i][j].relocate(i * cell_width, j * cell_height);
					renders[i][j].resize(cell_width, cell_height);

				}
			}
		}
	}
	
	// public method for resetting the game
	public void resetGame() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[i][j] = LightOFF;
				getChildren().remove(renders[i][j]);
				renders[i][j]  = new Piece(LightOFF); // the piece of light off
				getChildren().add(renders[i][j] ); //adding renders
				totalcompletedmoves = 0;
			}
		}
		
		
		//Setting a random number of lights on
		Random rand = new Random();
		int numberOfRandomLightsOn = rand.nextInt((10 - 5) + 1) + 5;
		System.out.println(numberOfRandomLightsOn);
	
		//Setting a random lights on and rendering them
		for(int a = 0;a<=numberOfRandomLightsOn;a++) {
			int numberOfRandomLightsX = rand.nextInt((5 - 0) + 0) +  0;
			int numberOfRandomLightsY = rand.nextInt((5 - 0) + 0) +  0;
			board[numberOfRandomLightsX][numberOfRandomLightsY] = LightON;
			renders[numberOfRandomLightsX][numberOfRandomLightsY]  = new Piece(LightON); // the piece of light off
			getChildren().add(renders[numberOfRandomLightsX][numberOfRandomLightsY] ); //adding renders
		}
		

	}

	// public method that places a piece
	public void placePiece(final double x, final double y) {
		// translate the x, y coordinates into cell indexes
		int indexx = (int) (x / cell_width);
		int indexy = (int) (y / cell_height);
		
		//adding a completed move
		totalcompletedmoves++;
		
		//Checking edge cases out of bound / 0.0 check
		if(indexx==0 && indexy==0) {
			swicthLight(0,0);
			swicthLight(1,0);
			swicthLight(0,1);
			winconditioncheck();
			return;
		}//Checking edge cases out of bound / 0.4 check
		else if(indexx==0 && indexy==4) {
			swicthLight(0,4);
			swicthLight(0,3);
			swicthLight(1,4);
			winconditioncheck();
			return;
		} //Checking edge cases out of bound / 4.0 check
		else if(indexx==4 && indexy==0) {
			swicthLight(4,0);
			swicthLight(3,0);
			swicthLight(4,1);
			winconditioncheck();
			return;
		} //Checking edge cases out of bound / 4.4 check
		else if(indexx==4 && indexy==4) {
			swicthLight(4,4);
			swicthLight(3,4);
			swicthLight(4,3);
			winconditioncheck();
			return;
		}
		 
		//Checking edge cases out of bound / 0.1 to 0.3 check
		for(int a = 1;a<4;a++) {
			if(indexx==0 && indexy==a) {
				swicthLight(0,a);
				swicthLight(0,a-1);
				swicthLight(0,a+1);
				swicthLight(1,a);
				winconditioncheck();
				return;
			}
		}
		
		//Checking edge cases out of bound / 4.1 to 4.3 check
		for(int a = 1;a<4;a++) {
			if(indexx==4 && indexy==a) {
				swicthLight(4,a);
				swicthLight(4,a-1);
				swicthLight(4,a+1);
				swicthLight(3,a);
				winconditioncheck();
				return;
			}
		}
		
		//Checking edge cases out of bound / 1.0 to 1.3 check
		for(int a = 1;a<4;a++) {
			if(indexx==a && indexy==0) {
				swicthLight(a,0);
				swicthLight(a-1,0);
				swicthLight(a+1,0);
				swicthLight(a,1);
				winconditioncheck();
				return;
			}
		}
		
		//Checking edge cases out of bound / 0.1 to 0.3 check
		for(int a = 1;a<4;a++) {
			if(indexx==a && indexy==4) {
				swicthLight(a,4);
				swicthLight(a-1,4);
				swicthLight(a+1,4);
				swicthLight(a,3);
				winconditioncheck();
				return;
			}
		}
		
		//Otherwise
		swicthLight(indexx,indexy);
		swicthLight(indexx,indexy-1);
		swicthLight(indexx-1,indexy);
		swicthLight(indexx+1,indexy);
		swicthLight(indexx,indexy+1);
		winconditioncheck();
		return;
		
	}
	
	
	//Switching the light method turn the light off or on
	public void swicthLight(int indexx,int indexy) {
		if( board[indexx][indexy] == LightOFF ) {
			getChildren().remove(renders[indexx][indexy]); //removing old pieces
			board[indexx][indexy] = LightON; //setting the board with the light on
			renders[indexx][indexy] = new Piece(LightON); // the piece of light on
			getChildren().add(renders[indexx][indexy]); //adding renders
		}else if(board[indexx][indexy] == LightON) {
			getChildren().remove(renders[indexx][indexy]); //removing old pieces
			board[indexx][indexy] = LightOFF;//setting the board with the light off
			renders[indexx][indexy] = new Piece(LightOFF); // the piece of light off
			getChildren().add(renders[indexx][indexy]); //adding renders
		}
	}
        
        //the win condition method
	public void winconditioncheck() {
		
		//checking for all lights to be off
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i][j] != LightOFF) {
				 return;
				}
			}
		}
		
		//Creating an Alert for user when game is completed
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("WINNER!");
		alert.setHeaderText(null);
		alert.setContentText("ALL THE LIGHTS ARE OFF! You won!"+ " You completed it in "+totalcompletedmoves);
		alert.showAndWait();
		resetGame();
	}

	// private fields of the class
	private int[][] board; // array that holds all pieces
	private Piece[][] renders; // array that holds all the render pieces
	private double cell_width, cell_height; // width and height of a cell

	// constants for the class RENDERS
	private final int LightOFF = 1;
	private final int LightON = 2;
	
    //Win condition boolean
    static boolean WIN;

    //counter for the total moves
	int totalcompletedmoves;
        

}
