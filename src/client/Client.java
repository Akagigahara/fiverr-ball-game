package client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements AutoCloseable
{
	final int port = 8888;
	
	private final Scanner reader;
	private final PrintWriter writer;
	private int playerID;
	
	public Client() throws Exception
	{
		Socket socket = new Socket("localhost", port);
		reader = new Scanner(socket.getInputStream());
		
		writer = new PrintWriter(socket.getOutputStream(), true);
		
		setPlayerID();
		
		String line = reader.nextLine();
		if(line.trim().equalsIgnoreCase(line))
		{
			System.out.println("Successfully connected!");
		}
	}
	
	public void pass(int toPlayer, int fromPlayer) throws Exception
	{
		writer.println("PASS " + fromPlayer + " " + toPlayer);
		
		String line = reader.nextLine();
		switch(line.toLowerCase())
		{
			case "player does not have ball":
				System.out.println("You do not own the ball.");
				break;
			case "player not in game":
				System.out.println("The player you want to pass the ball to is no longer in the game.");
				break;
			default:
				System.out.println("Ball successfully passed.");
				break;
		}
	}
	
	public void setPlayerID()
	{
		playerID = Integer.parseInt(reader.nextLine()); 
	}
	
	public int getPlayerID()
	{	
		return playerID;
	}
	
	public String getPlayers()
	{
		writer.println("PLAYERS");
		String result = reader.nextLine();
		return result;
	}
	
	public void getPlayerWhoHasBall()
	{
		writer.println("WHO");
		
		String line = reader.nextLine().toLowerCase();
		if(line.equalsIgnoreCase("game not started"))
		{
			System.out.println("The game has not started yet.");
		}
		else
		{
			System.out.println("Player " + line + " has the ball");
		}
	}

	@Override
	public void close() throws Exception
	{
		reader.close();
		writer.close();
	}
}
