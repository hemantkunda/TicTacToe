package main;

import java.io.IOException;
import java.net.UnknownHostException;

import network.NetworkingServer;
import network.Const;

public class TicTacToeNetwork {
	public static void main(String[] args) throws UnknownHostException, IOException {
		new NetworkingServer(Const.port1).start();
	}
}
