
public class PercolationDFSFast extends PercolationDFS {

	public PercolationDFSFast(int size) {
		super(size);
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
		if (row == 0) {
			dfs(row, col);		
		}
		if (col - 1 >= 0) {
			if (isFull(row, col -1)) {
				dfs(row, col);
			}
		}
		if (col + 1 < myGrid.length) {
			if (isFull(row, col + 1)) {
				dfs(row, col);
			}
		}
		if (row - 1 >= 0) {
			if (isFull(row - 1, col)) {
				dfs(row, col);
			}
		}
		if (row + 1 < myGrid.length) {
			if (isFull(row + 1, col)) {
				dfs(row, col);
			}
		}
		
	}
	
}
