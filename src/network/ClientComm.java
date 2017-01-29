package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import tictactoe.Location;

public class ClientComm extends Thread{
	private int port;
	private Socket socket;
	private InetAddress inetAddr;
	private ObjectInputStream in;
	private Scanner sysIn;
	private ObjectOutputStream out;
	private int outcome;
	private int playerNum;
	private String token;
	
	public ClientComm(int port) throws UnknownHostException, IOException {
		this.port = port;
		this.socket = new Socket("localhost", port);
		this.out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		this.in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		this.sysIn = new Scanner(System.in);
		this.inetAddr = socket.getInetAddress();
		System.out.println("Player connected to " + inetAddr + ":" + port);
		this.out.flush();
		try {
			this.playerNum = (Integer)(in.readObject());
			this.token = playerNum == 1 ? "X" : "O";
		} catch (ClassNotFoundException c) {
			
		}
	}
	
	public void run() {
		Thread player = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Token t = (Token)(in.readObject());
						if (t.status > 1) {
							outcome = t.status;
							System.out.println((String)(in.readObject()));
							break;
						}
						if (t.status == 0) {
							System.out.println("It is your turn.");
						}
						if (t.status == 1) {
							System.out.println("Error: that move is invalid.");
						}
						String board = (String)(in.readObject());
						System.out.println(board);
						
						System.out.println("Place an " + token + ":");
						System.out.println("Row: ");
						int row = sysIn.nextInt() - 1;
						System.out.println("Column: ");
						int col = sysIn.nextInt() - 1;
						Location m = new Location(row, col);
						out.writeObject(m);
						out.flush();
					} catch (EOFException f) {
						System.exit(0);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException c) {
						c.printStackTrace();
					} catch (InputMismatchException i) {
						try {
							sysIn.nextLine();
							out.writeObject(new Location(-1, -1));
							out.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				if (outcome == 2) {
					System.out.println("You won!");
				}
				else if (outcome == 3) {
					System.out.println("You lost...");
				}
				else if (outcome == 4) {
					System.out.println("You tied!");
				}
			}
		});
		player.start();
		try {
			player.join();
			in.close();
			out.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
		
	}
}
