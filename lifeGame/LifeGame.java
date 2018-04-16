package lifeGame;

import java.awt.Container;
import javax.swing.JFrame;


import java.awt.EventQueue;

public class LifeGame extends JFrame {
	
	private static final long serialVersionUID = 2L;
	static MainPanel panel;
	
  public LifeGame(){
  	panel = new MainPanel();
  	setTitle("Life Game");

    setResizable(false);

    Container contentPane=getContentPane();
    contentPane.add(panel);

    pack();
  }

  public static void main(String[] args){  
  	
    EventQueue.invokeLater(new Runnable() {			
			public void run() {
				LifeGame lifeGame = new LifeGame();
		    lifeGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    lifeGame.setVisible(true);
		    
		    new Thread(new Runnable(){
		    	@Override
		    	public void run(){
		    		while(true){
		    			Board board;
		    			board = panel.getBoard();
			    		if(board.getStepFlag()){
								board.stepToNextGeneration();
								panel.repaint();
					    }
			    		try {
                Thread.sleep(50);
							} catch (InterruptedException e) {
                e.printStackTrace();
							}					
		    		}
		    	}
		    }).start();
		    

			}
    });
    
  }
}
