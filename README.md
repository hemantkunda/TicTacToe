# TicTacToe

I implemented Tic Tac Toe using basic Java networking. There are two main classes: NetworkingServer (the server), and ClientComm (the client).

Clients communicate their moves to the server, and the server responds in turn with Tokens that represent what their next action should be.

To run the game, first start the server (in TicTacToeNetwork.java), then create two players (in PlayerA.java and PlayerB.java). 

Currently, clients must be on the same network as the server to connect.
