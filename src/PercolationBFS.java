import java.util.Stack;

public class PercolationBFS extends PercolationDFSFast{

	public PercolationBFS(int size) {
		super(size);
	}
	
	public void dfs(int row, int col) {
		if (! inBounds(row,col)) {
			return;
		}
		if (isFull(row, col) || !isOpen(row, col)) {
			return;
		}
		Stack<Integer> s = new Stack<Integer> ();
		s.add(row * myGrid.length + col);
		myGrid[row][col] = FULL;
		while (s.size() > 0) {
			int curr = s.pop();
			row = curr/myGrid.length;
			col = curr%myGrid.length;
			if (col - 1 >= 0) {
				if (isOpen(row, col - 1) && isFull(row, col - 1) == false) {
					myGrid[row][col - 1] = FULL;
					s.add(row*myGrid.length + (col - 1));
				}
			}
			if (col + 1 < myGrid.length) {
				if (isOpen(row, col + 1) && isFull(row, col + 1) == false) {
					myGrid[row][col + 1] = FULL;
					s.add(row*myGrid.length + (col + 1));
				}
			}
			if (row - 1 >= 0) {
				if (isOpen(row - 1, col) && isFull(row - 1, col) == false) {
					myGrid[row - 1][col] = FULL;
					s.add((row - 1)*myGrid.length + col);
				}
			}
			if (row + 1 < myGrid.length) {
				if (isOpen(row + 1, col) && isFull(row + 1, col) == false) {
					myGrid[row + 1][col] = FULL;
					s.add((row + 1)*myGrid.length + col);
				}
			}
		}
	}
}
