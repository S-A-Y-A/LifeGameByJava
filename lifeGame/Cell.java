package lifeGame;

public class Cell {
	public static final int ALIVE = 1;
	public static final int DEAD  = 0;
	
	private int status;
	
	public Cell(){
		status = DEAD;
	}
	
	public int getStatus(){
		return status;
	}
	
	public int getAround(Board board, int x, int y){
		int sum = 0;
		
		sum += board.getCell(x-1, y-1).getStatus();
		sum += board.getCell(x-1, y).getStatus();
		sum += board.getCell(x-1, y+1).getStatus();
		sum += board.getCell(x, y-1).getStatus();
		sum += board.getCell(x, y+1).getStatus();
		sum += board.getCell(x+1, y-1).getStatus();
		sum += board.getCell(x+1, y).getStatus();
		sum += board.getCell(x+1, y+1).getStatus();
		
		return sum;
	}
	
	public void birth(){
		status = ALIVE;
	}
	
	public void kill(){
		status = DEAD;
	}
}
