package server;

import java.util.Random;

public class Ball {
	private static int carryingPlayerID = -1;
	
	public static int getPlayerID()
	{
		return carryingPlayerID;
	}
	
	public static void setPlayerID(int ID)
	{
		carryingPlayerID = ID;
	}

	public static void assignBall()
	{
		Random rand = new Random();
		int x = rand.nextInt(ServerProgram.getPlayers().size());
		String gottenID = ServerProgram.getPlayers().get(x);
		setPlayerID(Integer.parseInt(gottenID));
		ServerProgram.setBallOnField(true);
		System.out.println("Game has Started!");
	}
	
	public static void reAssignBall()
	{
		Random rand = new Random();
		int x = rand.nextInt(ServerProgram.getPlayers().size());
		String gottenID = ServerProgram.getPlayers().get(x);
		setPlayerID(Integer.parseInt(gottenID));
	}
}
