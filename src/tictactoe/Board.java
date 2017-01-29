package tictactoe;

import java.io.Serializable;

public class Board implements Serializable{

	private static final long serialVersionUID = 3450527006136644688L;
	private int[][] tiles;
	
	public Board() {
		tiles = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tiles[i][j] = -1;
			}
		}
	}
	
	/** 
	 * Places the given letter in the specified location
	 * on the board.
	 * 
	 * @param letter the letter to be placed; either 1 or 2
	 * @param row the row on the board; between 0 and 2 inclusive
	 * @param col the col on the board; between 0 and 2 inclusive
	 */
	public int place(int letter, int row, int col) {
		tiles[row][col] = letter;
		return winner();
	}
	
	/**
	 * Returns 0 if Player 1 wins; 1 if Player 2 wins; -1 if no winner yet
	 * @return the winner of the game
	 */
	public int winner() {
		for (int i = 0; i < 3; i++) {
			// check rows
			if (tiles[i][0] == tiles[i][1] && tiles[i][1] == tiles[i][2]) {
				if (tiles[i][0] > -1) return tiles[i][0];
			}
			// check cols
			if (tiles[0][i] == tiles[1][i] && tiles[1][i] == tiles[2][i]) {
				if (tiles[0][i] > -1) return tiles[0][i];
			}
		}
		
		//downwards diagonal
		if (tiles[0][0] == tiles[1][1] && tiles[1][1] == tiles[2][2]) {
			if (tiles[0][0] > -1) return tiles[0][0];
		}
		
		//upwards diagonal
		if (tiles[2][0] == tiles[1][1] && tiles[1][1] == tiles[0][2]) {
			if (tiles[2][0] > -1) return tiles[2][0];
		}
		return -1;
	}
	
	public boolean isEmpty(int r, int c) {
		return tiles[r][c] == -1;
	}
	
	public String toString() {
		String str = "";
		char[] conv = {' ', 'X', 'O'};
		for (int i = 0; i < 3; i++) {
			char firstTile = conv[tiles[i][0] + 1]; 
			char secondTile = conv[tiles[i][1] + 1]; 
			char thirdTile = conv[tiles[i][2] + 1]; 
			str += firstTile + " | " + secondTile + " | " + thirdTile + "\n";
			if (i != 2) {
				str += "---------\n";
			}
		}
		return str;
	}

}
