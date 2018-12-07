
public class PercolationDFSFast extends PercolationDFS {

	//constructor that calls super on size
	public PercolationDFSFast(int size) {
		super(size);
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
		if (row == 0) {
			dfs(row, col);		
		}
		//all of these else if's make sure current cell is in bounds
		//if current cell is full, do dfs on the current cell
		else if (col - 1 >= 0 && isFull(row, col -1)){
				dfs(row, col);	
		}
		else if (col + 1 < myGrid.length && isFull(row, col + 1)) {
				dfs(row, col);
		}
		else if (row - 1 >= 0 && isFull(row - 1, col)) {
				dfs(row, col);
		}
		else if (row + 1 < myGrid.length && isFull(row + 1, col)) {
				dfs(row, col);
		}
		
	}
	
}
