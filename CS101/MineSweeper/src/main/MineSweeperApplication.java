package main;

/**
* MineSweeperApplication.java
* CSC 101 Final Project
* Luong Nguyen 
**/

import javax.swing.JFrame;

/**
* MineSweeperApplication wraps a MineSweeperPanel for the MineSweeper game.
**/
public class MineSweeperApplication 
{
	
	public static void main( String[] args )
	{
		JFrame mineSweeperFrame = new JFrame( "MineSweeper game" );
		mineSweeperFrame.getContentPane().add( new MineSweeperPanel() );
		mineSweeperFrame.setSize( 500, 500 );
		mineSweeperFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mineSweeperFrame.setVisible( true );
	}
}
