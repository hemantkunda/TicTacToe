package main;

import java.io.IOException;
import java.net.UnknownHostException;

import network.ClientComm;
import network.Const;

public class PlayerA {
	public static void main(String[] args) throws UnknownHostException, IOException {
		new ClientComm(Const.port1).start();
	}
}
