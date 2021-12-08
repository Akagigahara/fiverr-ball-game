package client;

import java.util.Scanner;

public class ClientStart
{
	public static void main(String[] args)
	{
		System.out.println("Connecting...");
		
		try
		{
			Scanner in = new Scanner(System.in);
			
			try(Client client = new Client())
			{
				int playerID = client.getPlayerID();
				
				while(true)
				{
					System.out.println("Your ID: " + playerID);
					System.out.println(client.getPlayers());
					System.out.println("");
					System.out.println("Please use Pass to pass the ball to another player, Who to see who own the ball and Close to exit");
					
					String line = in.nextLine().toLowerCase();
					switch(line)
					{
						case "pass":
							System.out.println("Pass the ball to:");
							System.out.println(client.getPlayers());
							client.pass(Integer.parseInt(in.nextLine()), playerID);
							System.out.println("Press Enter to continue.");
							in.nextLine();
							break;
						case "who":
							client.getPlayerWhoHasBall();
							System.out.println("Press Enter to continue.");
							in.nextLine();
							break;
						case "close":
							client.close();
							break;
						default:
							System.out.println("Unknown command: " +  line);
							System.out.println("Press Enter to continue.");
							in.nextLine();
							break;
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
