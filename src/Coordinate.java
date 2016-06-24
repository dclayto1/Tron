
public class Coordinate {

	private int x,y;
	
	public Coordinate()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	public void setCoordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Coordinate getCoordinates()
	{
		return this;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public boolean equals(Coordinate other)
	{
		return (this.x == other.x && this.y == other.y);
	}
	
	public String toString()
	{
		return x+","+y;
	}
}
