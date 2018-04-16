package lifeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel 
	implements MouseListener, KeyListener {
  
  private static final long serialVersionUID = 1L;
	public static final int WIDTH = Board.WIDTH;
	public static final int HEIGHT = Board.HEIGHT;
	public static final int LENGTH = Board.LENGTH;
	

  private Board board = new Board();
  private JLabel birth_kill = new JLabel("Space : Birth/Kill");
  private JLabel direction  = new JLabel("↑↓←→ : Move");
  private JLabel next       = new JLabel("n : Next");
  private JLabel random     = new JLabel("r : Random");
  private JLabel genocide   = new JLabel("g : Genocide");
  private JLabel start_stop = new JLabel("s : Start/Stop");
  private JLabel quit       = new JLabel("q : Quit");

  public MainPanel(){
    setPreferredSize(new Dimension(WIDTH*LENGTH + 150, HEIGHT*LENGTH));
    setLayout(null);
    
    birth_kill.setBounds(WIDTH*LENGTH+5, 0, 150, 20);
    direction.setBounds(WIDTH*LENGTH+5, 20, 150, 20);
    next.setBounds(WIDTH*LENGTH +5, 40, 150, 20);
    random.setBounds(WIDTH*LENGTH+5, 60, 150, 20);
    genocide.setBounds(WIDTH*LENGTH+5, 80, 150, 20);
    start_stop.setBounds(WIDTH*LENGTH+5, 100, 150, 20);
    quit.setBounds(WIDTH*LENGTH+5, 120, 150, 20);
    add(birth_kill);
    add(direction);
    add(next);
    add(random);
    add(genocide);
    add(start_stop);
    add(quit);
    
    setFocusable(true);
    setBackground(Color.BLUE);
    addKeyListener(this);
    addMouseListener(this);
  }
  
  public Board getBoard(){
  	return board;
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    board.draw(g);
  }

  public void mouseClicked(MouseEvent e){
    int x=e.getX();
    int y=e.getY();

    if(x%Board.LENGTH==0 || y%Board.LENGTH==0) return;
    
    Cell cell = board.getCell((x/Board.LENGTH)+1, (y/Board.LENGTH)+1);
    int status = cell.getStatus();
    
    if(status==Cell.ALIVE) cell.kill();
    if(status==Cell.DEAD)  cell.birth();

    board.getPosition().setLocation((x/Board.LENGTH)+1, (y/Board.LENGTH)+1);
    
    repaint();
  }

  public void mouseEntered(MouseEvent e){}

  public void mouseExited(MouseEvent e){}

  public void mousePressed(MouseEvent e){}

  public void mouseReleased(MouseEvent e){}

  public void keyPressed(KeyEvent e){
    int key=e.getKeyCode();
    switch(key){
      case KeyEvent.VK_Q :
        System.exit(0);
        break;
      case KeyEvent.VK_G :
        board.genocide();
        board.stop();
        break;
      case KeyEvent.VK_S :
        if(board.getStepFlag()) board.stop();
        else										board.start();
        break;
      case KeyEvent.VK_R :
      	board.birthRandomly();
      	break;
      case KeyEvent.VK_N :
      	board.stepToNextGeneration();
      	break;
      case KeyEvent.VK_UP :
      	if(board.getPosition().y > 1) board.getPosition().translate(0, -1);
      	break;
      case KeyEvent.VK_DOWN :
      	if(board.getPosition().y < Board.HEIGHT) board.getPosition().translate(0, 1);
      	break;
      case KeyEvent.VK_LEFT :
      	if(board.getPosition().x > 1) board.getPosition().translate(-1, 0);
      	break;
      case KeyEvent.VK_RIGHT :
      	if(board.getPosition().x < Board.WIDTH) board.getPosition().translate(1, 0);
      	break;
      case KeyEvent.VK_SPACE :
      	int x = board.getPosition().x;
      	int y = board.getPosition().y;
      	Cell cell = board.getCell(x, y);
      	int status = cell.getStatus();     	
      	if(status == Cell.ALIVE) cell.kill();
      	if(status == Cell.DEAD)  cell.birth();
      	break;
      default :
      	break;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e){}

  public void keyReleased(KeyEvent e){}
}

