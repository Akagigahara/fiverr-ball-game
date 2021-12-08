package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import server.*;


public class ServerProgram {
	
	private final static int port = 8888;
	private final static Ball ball = new Ball();
	private static int assignablePlayerID;
	private static Vector<String> playersOnField = new Vector<String>(0,1);
	private static Boolean ballOnField = false;
	private static Boolean emptyField = false;

	public static void main(String[] args) throws InterruptedException
	{
		runServer();
	}
	
	private static void runServer() throws InterruptedException
	{
		ServerSocket serverSocket = null;
		
		try 
		{
			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for incoming connection.");
			while(true)
			{
				Socket socket = serverSocket.accept();
				new Thread(new ClientHandler(socket), "Player" + assignablePlayerID).start();
				
				Thread.sleep(50);
				
				if(!ballOnField && playersOnField.size() >= 2)
				{
					Ball.assignBall();
				}
				else if(ballOnField && emptyField)
				{
					Ball.reAssignBall();
				}
			}
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static int assignID()
	{
		int assignedID = assignablePlayerID;
		assignablePlayerID++;
		return assignedID;
	}
	
	public static void addPlayer(int playerID)
	{
		playersOnField.add(Integer.toString(playerID));
	}
	
	public static void removePlayer(int playerID)
	{
		playersOnField.remove(Integer.toString(playerID));
	}
	
	public static Vector<String> getPlayers()
	{
		return playersOnField;
	}
	
	public static Boolean isBallOnField()
	{
		return ballOnField;
	}

	public static void setBallOnField(Boolean state) {
		ballOnField = state;
	}

	public static boolean checkPlayers(int toPlayer)
	{
		for(String player : playersOnField)
		{
			if(toPlayer == Integer.parseInt(player))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void changeEmptyField()
	{
		emptyField = !emptyField;
	}
}
