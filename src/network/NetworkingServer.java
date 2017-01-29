package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import tictactoe.BoardIO;
import tictactoe.Location;

public class NetworkingServer extends Thread{
	private int port;
	private BoardIO io;
	public NetworkingServer(int port) {
		this.port = port;
		this.io = new BoardIO();
	}
	
	public void run() {
		try {
			System.out.println("Starting server...");
			ServerSocket socket1 = new ServerSocket(port);
			Socket s1 = socket1.accept();
			System.out.println("Player A connected.");
			//ServerSocket socket2 = new ServerSocket(port2);
			Socket s2 = socket1.accept();
			System.out.println("Player B connected.");
			System.out.println("Sockets up and running!");
			System.out.println("Connection established!");
			//Thread.sleep(2000);
			ObjectOutputStream aOut = new ObjectOutputStream(s1.getOutputStream());
			ObjectOutputStream bOut = new ObjectOutputStream(s2.getOutputStream());
			ObjectInputStream aIn = new ObjectInputStream(s1.getInputStream());
			ObjectInputStream bIn = new ObjectInputStream(s2.getInputStream());
			Token valid = new Token(0);
			Token invalid = new Token(1);
			
			aOut.writeObject(1);
			bOut.writeObject(2);
			
			boolean success = false;
			int count = 0;
			int move = 0;
			System.out.println("Starting game!");
			while (io.winner() == -1) {
				while (!success) {
					System.out.println("A's turn");
					aOut.writeObject(count == 0 ? valid : invalid);
					aOut.writeObject(io.boardString());
					Location aMove = (Location)(aIn.readObject());
					success = io.execute(0, aMove.row, aMove.col);
					count++;
				}
				if (io.winner() >= 0) break;
				success = false;
				count = 0;
				move++;
				if (move == 9) break;
				while (!success) {
					System.out.println("B's turn");
					bOut.writeObject(count == 0 ? valid : invalid);
					bOut.writeObject(io.boardString());
					Location bMove = (Location)(bIn.readObject());
					success = io.execute(1, bMove.row, bMove.col);
					count++;
				}
				success = false;
				count = 0;
				move++;
			}
			System.out.println(move);
			if (move == 9) {
				aOut.writeObject(new Token(4));
				bOut.writeObject(new Token(4));
			}
			else {
				int winner = io.winner();
				if (winner == 0) {
					aOut.writeObject(new Token(2));
					bOut.writeObject(new Token(3));
				}
				else {
					aOut.writeObject(new Token(3));
					bOut.writeObject(new Token(2));
				}
			}
			aOut.writeObject(io.boardString());
			bOut.writeObject(io.boardString());
			socket1.close();
			//socket2.close();

		} catch (IOException | ClassNotFoundException i) {
			i.printStackTrace();
		}
	}
	
	public BoardIO getGameIO() {
		return io;
	}
}
