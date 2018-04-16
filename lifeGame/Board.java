package lifeGame;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Board {
	//縦・横のCellの数
	public static int WIDTH = 100; 
	public static int HEIGHT = 60; 
	//Cellの辺の長さ
	public static int LENGTH = 10; 
	
	
	private Cell[][] cells = new Cell[WIDTH+2][HEIGHT+2];
	
	//カーソルの場所
	private Point position;
	//start状態かstop状態かを判定する.trueでstart,falseでstop
	private boolean stepFlag = false;
	
	public Board(){
		for(int i=0; i<WIDTH+2; i++){
			for(int j=0; j<HEIGHT+2; j++){
				cells[i][j] = new Cell();
			}
		}
		
		position = new Point(WIDTH/2, HEIGHT/2);
	}
	
	public Cell getCell(int x, int y){
		return cells[x][y];
	}
	
	public Point getPosition(){
		return position;
	}
	
	public boolean getStepFlag(){
		return stepFlag;
	}

	public int getAliveCellNum(int column){
		int aliveCellNum = 0;
		
		for(int i=1; i<=Board.HEIGHT; i++){
			aliveCellNum += cells[i][column].getStatus();
		}
		return aliveCellNum;
	}
	
	
	public void stepToNextGeneration(){
		Cell[][] nextCells = new Cell[WIDTH+2][HEIGHT+2];
		int sum;
		int status;
		
		for(int i=0; i<WIDTH+2; i++){
			for(int j=0; j<HEIGHT+2; j++){
				nextCells[i][j] = new Cell();
				
				if(i%(WIDTH+1)==0 || j%(HEIGHT+1)==0) continue;
				
				status = cells[i][j].getStatus();
				sum = cells[i][j].getAround(this, i, j);
				if(status==Cell.ALIVE){
					if(sum == 2 || sum == 3) nextCells[i][j].birth();					
				}
				if(status==Cell.DEAD){
					if(sum == 3) nextCells[i][j].birth();				
				}
			}
		}		
		cells = nextCells;
	}
	
	public void genocide(){
		for(int i=0; i<WIDTH+2; i++){
			for(int j=0; j<HEIGHT+2; j++){
				cells[i][j].kill();
			}
		}
	}
	
	public void birthRandomly(){
		Random random = new Random();
		
		for(int i=1; i<=WIDTH; i++){
			for(int j=1; j<=HEIGHT; j++){
				if(cells[i][j].getStatus()==Cell.DEAD){
					if(random.nextInt(5)==0) cells[i][j].birth();
				}
			}
		}
	}
	
	public void draw(Graphics g){
		for(int i=1; i<=WIDTH; i++){
			for(int j=1; j<=HEIGHT; j++){
				if(getCell(i, j).getStatus()==Cell.ALIVE) g.setColor(Color.YELLOW);
				else 																			g.setColor(Color.BLACK);
				g.fillRect((i-1)*LENGTH+1, (j-1)*LENGTH+1, LENGTH-1, LENGTH-1);
			}
		}
		g.setColor(Color.RED);
		g.drawRect((position.x-1)*LENGTH, (position.y-1)*LENGTH, LENGTH, LENGTH);
	}
	
	public void start(){
		stepFlag = true;
	}
	
	public void stop(){
		stepFlag = false;
	}
	
}