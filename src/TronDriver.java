import java.awt.EventQueue;

import javax.swing.*;

public class TronDriver extends JFrame{

	private int WINDOW_WIDTH = 500;
	private int WINDOW_HEIGHT = 500;
	
	
	public TronDriver()
	{
		init();
	}
	
	private void init() {
        
        add(new Board(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        
        setTitle("Moving sprite");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
    }
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                TronDriver window = new TronDriver();
                window.setVisible(true);
            }
        });
		
		
	}

}
