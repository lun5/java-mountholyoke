﻿/*Luong NguyenCompSc 101HangmanGui.as*/// Circle.as// Luong Nguyen// CS 101 package {	// import statements	// if this is a class linked to a Symbol	//   import the Symbol's type	//   (e.g., MovieClip or Button)	import flash.display.MovieClip;	/**	     * Description of class.	     * This example is for a class definition linked 	     * to a MovieClip Symbol, so it is a MovieClip.	     * The is a relationship is indicated with the	     * keyword extends.	     **/	public class HangmanGUI extends MovieClip {		/********************** INSTANCE PROPERTIES *********************/		// "hidden" declarations from Symbol: outputField		// For every named instance that you create in the Flash Symbol,		// place a corresponding "hidden" declaration here using the 		// following format. This is so you can remember what belongs to		// this class		// DO NOT UNCOMMENT!!!		//// public var <instance name from Flash editor> : type;		//// For example, assuming there is a text field named outputField:		//// public var outputField : TextField;		// user-defined instance properties for this class go here		private var hangmanGame:HangmanGame;		/************************ INSTANCE METHODS **********************/		/**		         * Constructor		         **/		public function HangmanGUI() {			hangmanGame=new HangmanGame  ;			initGUI();		}		// additional instance methods go here		import flash.events.MouseEvent;		//initGUI takes in no parameters and has a void return type		public function initGUI():void {			//text in the currentGuess field			currentGuessField.text=hangmanGame.getCurrentGuess();			//text in the remaining strikes			strikeRemainField.text="";			// add a "mouse listener"  to the Guess button so that, 			// when it is clicked, the userGuessed function is called			guess.addEventListener(MouseEvent.CLICK,userGuessed);			//initilized to hide win/lose screens			winBand.visible=false;			loseBand.visible=false;			//update current display			refreshDisplay();		}		/*		userGuessed takes in a single parameter of type MouseEvent and returns void		Invoked when the user guesses a letter 		add as an event listener in the initGUI function		*/		public function userGuessed(e:MouseEvent):void {			//If the game isn't over			if (! hangmanGame.gameOver()) {				//call the guessLetter function to update "logic"				//trace("the input text is "+inputField.text);				hangmanGame.guessLetter(inputField.text);				//refresh the display to reflect current state of the game				refreshDisplay();			}		}		//refreshDisplay takes in no parameters and has a void return type		public function refreshDisplay():void {			//Refresh user interface (GUI), e.g., text fields 			//to reflect current guess			currentGuessField.text=hangmanGame.getCurrentGuess();			//current number of strikes. 			strikeRemainField.text=String(hangmanGame.numberOfRemainingStrikes());			revealAnswer();		}		//revealAnswer takes in no parameters and has a void return type		public function revealAnswer():void {			//Update GUI to reveal the correct answer			if (hangmanGame.gameWon()) {				trace("User won");				currentGuessField.text=hangmanGame.getAnswer();				winBand.visible=true;			} else if (hangmanGame.gameOver()&&! hangmanGame.gameWon()) {				trace("User lost");				currentGuessField.text=hangmanGame.getAnswer();				loseBand.visible=true;			}		}	}}