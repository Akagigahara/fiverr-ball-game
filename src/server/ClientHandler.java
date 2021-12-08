package server;

import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClientHandler implements Runnable {
	private final Socket socket;
	
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run() {
		int playerID = ServerProgram.assignID();
		
        
		try 
        {
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            try
            {
            	writer.println(playerID);
            	
                System.out.println("New connection. player ID: " + playerID);
                ServerProgram.addPlayer(playerID);
            	System.out.println("The current players are in the game:");
            	System.out.println(ServerProgram.getPlayers().toString());
            	System.out.println("");
            	
                writer.println("SUCCESS");

                while (true)
                {
                	String line = scanner.nextLine();
                    String[] substrings = line.split(" ");
                    
                    switch (substrings[0].toLowerCase())
                    {
                    	case "pass":
                    		int fromPlayer = Integer.parseInt(substrings[1]);
                    		int toPlayer = Integer.parseInt(substrings[2]);
                    		if(Ball.getPlayerID() != fromPlayer)
                    		{
                    			writer.println("PLAYER DOES NOT HAVE BALL");
                    		}
                    		
                    		else if(!ServerProgram.checkPlayers(toPlayer))
                    		{
                    			writer.println("PLAYER NOT IN GAME");
                    		}
                    		
                    		else if(Ball.getPlayerID() == fromPlayer)
                    		{
                    			Ball.setPlayerID(toPlayer);
                    			writer.println("SUCCESS");
                    		}
                    		break;
                    	
                    	case "players":
                    		writer.println(ServerProgram.getPlayers());
                    		break;
                    	
                    	case "who":
                    		if(ServerProgram.isBallOnField())
                    		{
                    			writer.println(Ball.getPlayerID());
                    		}
                    		else
                    		{
                    			writer.println("GAME NOT STARTED");
                    		}
                    		break;
                    	
                    	default:
                    		throw new Exception("Unknown command: " + substrings[0]);
                    }
                }
            }
            catch (Exception e) 
            {
            	System.out.println("ERROR " + e.getMessage());
                writer.println("ERROR " + e.getMessage());
                socket.close();
                ServerProgram.removePlayer(playerID);
            }
        
        }
        
        catch(Exception e){}
        finally
        {
        	if(Ball.getPlayerID() == playerID && ServerProgram.getPlayers().size() == 0)
        	{
        		ServerProgram.changeEmptyField();
        		Ball.setPlayerID(-1);
        	}
        	else if(Ball.getPlayerID() == playerID)
        	{
        		Ball.reAssignBall();
        	}
        	System.out.println("Player " + playerID + " disconnected.");
        	System.out.println("The current players are in the game:");
        	System.out.println(ServerProgram.getPlayers().toString());
        	System.out.println("");
        	
           
        }
	}
}