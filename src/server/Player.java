package server;

public class Player {
	private final int PlayerID;
	private boolean hasBall;
	
	public Player(int playerID)
	{
		this.PlayerID = playerID;
		this.hasBall = false;
	}
	
	public int getPlayerID()
	{
		return PlayerID;
	}
	
	public void passBall(int toPlayer, int fromPlayer)
	{
		
	}
	
	public boolean getHasBall()
	{
		return hasBall;
	}
	
	public void changeHasBall()
	{
		if(hasBall)
		{
			hasBall = false;
		}
		
		else if(hasBall)
		{
			hasBall = true;
		}
	}

}
