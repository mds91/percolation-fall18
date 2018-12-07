import java.util.Stack;

public class PercolationBFS extends PercolationDFSFast{

	//constructor that calls super on size
	public PercolationBFS(int size) {
		super(size);
	}
	
	@Override
	public void dfs(int row, int col) {
		//if is not in bounds
		if (! inBounds(row,col)) {
			return;
		}
		//if is full and not open
		if (isFull(row, col) || !isOpen(row, col)) {
			return;
		}
		//create a stack of ints
		Stack<Integer> s = new Stack<Integer> ();
		//add cell
		s.add(row * myGrid.length + col);
		//default is full
		myGrid[row][col] = FULL;
		while (s.size() > 0) {
			int curr = s.pop();
			//use / because row is multiplied by myGrid.length, so we want to divide it now
			row = curr/myGrid.length;
			//use % because row is added to myGrid.length, so we want the remainder because col = remainder
			col = curr%myGrid.length;
			
			//all four if's make sure that our cell is in bounds
			//then we check all four neighbors by +- to row and +- to col
			//if a neighbor is open and NOT full, then we mark it as full now
			//then add current cell to the stack
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
