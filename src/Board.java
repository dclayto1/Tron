import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	private int minX, maxX;
	private int minY, maxY;
	private int board[][];
	private int blockSize = 10;
	private int playerWidth = 10;
	private int playerHeight = 10;
	public static final int emptySpace = 0;
	private Player[] playerSet; 
	private boolean isStillPlaying;
	private Timer timer;
	private int DELAY = 100;
	
	public Board(int maxX, int maxY)
	{
		this.minX = 0;
		this.minY = 0;
		this.maxX = maxX;
		this.maxY = maxY;
		initBoard();
	}
	
	public Board(int minX, int maxX, int minY, int maxY)
	{
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		initBoard();
	}
	
	private void initBoard()
	{
		isStillPlaying = true;
		board = new int[getHeight()/blockSize][getWidth()/blockSize];
		createPlayers();
		startTimer();
        addKeyListener(new TAdapter());
        setFocusable(true);
	}
	
	private void startTimer()
	{
		timer = new Timer(DELAY, this);
        timer.start();
	}
	
	private void createPlayers()
	{
		playerSet = new Player[2];
		playerSet[0] = new Player(PlayerID.ONE, new Coordinate(this.maxX/playerWidth/2, this.maxY/playerHeight/2 - 1), Direction.LEFT, Color.RED);
		playerSet[1] = new Player(PlayerID.TWO, new Coordinate(this.maxX/playerWidth/2 - 1, this.maxY/playerHeight/2), Direction.RIGHT, Color.BLUE);
	}
	
	public void setLocationValue(Coordinate location, PlayerID playerID)
	{
		if(playerID == PlayerID.ONE)
		{
			board[location.getY()][location.getX()] = 1;
		}
		else if(playerID == PlayerID.TWO)
		{
			board[location.getY()][location.getX()] = 2;
		}
	}
	
	public int getLocationValue(Coordinate location)
	{
		return board[location.getY()][location.getX()];
	}
	
	public int getWidth()
	{
		return this.maxX - this.minX;
	}
	
	public int getHeight()
	{
		return this.maxY - this.minY;
	}
	
	public int getMinX()
	{
		return this.minX;
	}
	
	public int getMaxX()
	{
		return this.maxX;
	}
	
	public int getMinY()
	{
		return this.minY;
	}
	
	public int getMaxY()
	{
		return this.maxY;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(isStillPlaying)
		{
			//move players, then check if alive
			playerSet[0].move();
			playerSet[1].move();
			
			//if either player is not alive, game over
			if(!playerSet[0].isPlayerAlive(this, playerSet) || !playerSet[1].isPlayerAlive(this,  playerSet))
			{
				isStillPlaying = false;
				return;
			}
			
			//if all is well, update the gameboard
			updateBoard();
			
			//update the graphics
			repaint();
			
		}
		
	}
	
	private void updateBoard()
	{
		for(Player p : playerSet)
		{
			this.setLocationValue(p.getPosition(), p.getPlayerID());
		}
	}
	
	private void drawBoard(Graphics g)
	{	
		HashSet<Coordinate> playerLines = new HashSet<Coordinate>();
		
		for(int i=0; i<this.getHeight()/10; i++)
		{
			for(int j=0; j<this.getWidth()/10; j++)
			{
				Coordinate value = new Coordinate(j, i);
				if(this.getLocationValue(value) != this.emptySpace)
				{
					playerLines.add(value);
				}
			}
		}
		
		for(Player p : playerSet)
		{
			int playerMinX = p.getPosition().getX();
			int playerMinY = p.getPosition().getY();
			
			g.setColor(p.getColor());
			g.drawRect(playerMinX*playerWidth, playerMinY*playerHeight, playerWidth, playerHeight);
			
            Toolkit.getDefaultToolkit().sync();
		}
		
		for(Coordinate coord : playerLines)
		{
			if(this.getLocationValue(coord) == 1) //player 1
			{
				g.setColor(playerSet[0].getColor());
				if(playerSet[0].getPosition().equals(coord))
				{ 
					//player, do nothing since done earlier 
				}
				
				else{ //trail
					g.fillRect(coord.getX()*playerWidth, coord.getY()*playerHeight, playerWidth, playerHeight);
				}
			}
			else if(this.getLocationValue(coord) == 2) //player 2
			{
				g.setColor(playerSet[1].getColor());
				if(playerSet[1].getPosition().equals(coord))
				{
					//player, do nothing since done earlier 
				}
				else //trail
				{
					g.fillRect(coord.getX()*playerWidth, coord.getY()*playerHeight, playerWidth, playerHeight);
				}
			}
			
            Toolkit.getDefaultToolkit().sync();

		}
		
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBoard(g);
    }
	
	
	private class TAdapter extends KeyAdapter {
		
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            //Player One
            if ((key == KeyEvent.VK_UP)) {
            	playerSet[0].turn(Direction.UP);
            }
            if ((key == KeyEvent.VK_RIGHT)) {
            	playerSet[0].turn(Direction.RIGHT);
            }
            if ((key == KeyEvent.VK_DOWN)) {
            	playerSet[0].turn(Direction.DOWN);
            }
            if ((key == KeyEvent.VK_LEFT)) {
            	playerSet[0].turn(Direction.LEFT);
            }
            
            
            //Player Two
            if ((key == KeyEvent.VK_W)) {
            	playerSet[1].turn(Direction.UP);
            }
            if ((key == KeyEvent.VK_D)) {
            	playerSet[1].turn(Direction.RIGHT);
            }
            if ((key == KeyEvent.VK_S)) {
            	playerSet[1].turn(Direction.DOWN);
            }
            if ((key == KeyEvent.VK_A)) {
            	playerSet[1].turn(Direction.LEFT);
            }
            
            
        }
    }

	
	
}
