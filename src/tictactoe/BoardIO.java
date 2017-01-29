package tictactoe;

import java.io.Serializable;

public class BoardIO implements Serializable{

	private static final long serialVersionUID = 5126737120099823959L;
	private Board board;
	
	public BoardIO() {
		this.board = new Board();
	}
	
	public boolean execute(int p, int r, int c) {
		if (validate(p, 0, 1) && validate(r, 0, 2) && validate(c, 0, 2)) {
			if (board.isEmpty(r, c)) {
				board.place(p, r, c);
				return true;
			}
		}
		return false;
	}
	
	public int winner() {
		return board.winner();
	}
	
	public String boardString() {
		return board.toString();
	}
	
	private boolean validate(int p, int l, int h) {
		return (l <= p && p <= h);
	}
}
