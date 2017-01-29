package tictactoe;

import java.io.Serializable;

public class Location implements Serializable {

	private static final long serialVersionUID = -6903146701283080142L;
	public int row;
	public int col;
	
	public Location(int r, int c) {
		this.row = r;
		this.col = c;
	}
	
	public String toString() {
		return "" + row + " " + col;
	}
}
