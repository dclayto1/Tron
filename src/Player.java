import java.awt.Color;
import java.util.HashSet;

public class Player {

	private PlayerID player_id;
	private Coordinate position;
	private Direction direction;
	private Color color;
	private boolean isAlive;
	
	public Player(PlayerID player_id, Coordinate initPosition,
					Direction initDirection, Color color)
	{
		this.player_id = player_id;
		this.position = initPosition;
		this.direction = initDirection;
		this.color = color;
	}
	
	public void turn(Direction newDirection)
	{
		if(newDirection == Direction.DOWN && this.direction == Direction.UP)
		{
			//opposite direction, don't let player move
		}	
		else if(newDirection == Direction.LEFT && this.direction == Direction.RIGHT)
		{
			//opposite direction, don't let player move
		}
		else if(newDirection == Direction.UP && this.direction == Direction.DOWN)
		{
			//opposite direction, don't let player move
		}
		else if(newDirection == Direction.RIGHT && this.direction == Direction.LEFT)
		{
			//opposite direction, don't let player move
		}
		else
		{
			this.direction = newDirection;
		}
		System.out.println("pressed");
	}
	
	public void move()
	{
		if(this.direction == Direction.UP)
		{
			this.position.setCoordinates(this.position.getX(), this.position.getY()-1);
		}
		else if(direction == Direction.RIGHT)
		{
			this.position.setCoordinates(this.position.getX()+1, this.position.getY());
		}
		else if(direction == Direction.DOWN)
		{
			this.position.setCoordinates(this.position.getX(), this.position.getY()+1);	
		}
		else //LEFT
		{
			this.position.setCoordinates(this.position.getX()-1, this.position.getY());
		}	
	}
	
	public Direction getDirection()
	{
		return this.direction;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	
	public boolean isPlayerAlive(Board gameBoard, Player[] playerArray)
	{
		if(isPlayerOutOfBoard(gameBoard.getMaxX()/10-1, gameBoard.getMaxY()/10-1))
		{
			return false;
		}
		else if(isPlayerCollidingIntoLine(gameBoard))
		{
			return false;
		}
		else{
			for(Player p : playerArray)
			{
				if(this != p)
				{
					if(isPlayerCollidingIntoPlayer(p))
					{
						return false;
					}
				}
			}
			
			
			return true;
		}
	}
	
	private boolean isPlayerOutOfBoard(int boardMaxX, int boardMaxY)
	{
		if(this.position.getX() < 0 || this.position.getY() < 0)
		{
			return true;
		}
		else if(this.position.getX() > boardMaxX || this.position.getY() > boardMaxY)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	private boolean isPlayerCollidingIntoLine(Board gameboard)
	{
		if(gameboard.getLocationValue(position) != Board.emptySpace)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isPlayerCollidingIntoPlayer(Player otherPlayer)
	{
		return this.position == otherPlayer.position;
	}
	
	public PlayerID getPlayerID()
	{
		return this.player_id;
	}
	
	public Coordinate getPosition()
	{
		return this.position;
	}
	
}
