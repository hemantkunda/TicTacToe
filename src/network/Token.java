package network;

import java.io.Serializable;

/** 
 * The Token class is used to communicate the status of the game to a player.
 * If status == 0, then the player should submit their move.
 * If status == 1, then the player's previous move was invalid
 * and they need to resubmit their move.
 * If status == 2, then the player has won the game.
 * If status == 3, then the player has lost the game.
 * 
 */
public class Token implements Serializable{

	private static final long serialVersionUID = 1L;
	public int status;
	public Token(int status) {
		this.status = status;
	}
}
