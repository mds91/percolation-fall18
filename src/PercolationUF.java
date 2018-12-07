
public class PercolationUF implements IPercolate {

	//create instance variables
	int[][] myGrid;
	int myOpenCount;
	IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	//constructor
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new int[size][size];
		myFinder = finder;
		myFinder.initialize(size*size + 2);
		VTOP = size*size;
		VBOTTOM = size*size + 1;
	}
	//returns false if not in bounds and true if is in bounds
	private boolean inBounds(int row, int col) {
		if (col < 0) {
			return false;
		}
		if (row < 0) {
			return false;
		}
		if (col >= myGrid.length){
			return false;
		}
		if (row >= myGrid.length) {
			return false;
		}
		else {
			return true;
		}
	}

	
	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (isOpen(row, col)) {
			return;
		}
		myOpenCount++;
		myGrid[row][col] = OPEN;
		//if in top row, union with VTOP
		if (row == 0) {
			myFinder.union(VTOP, row*myGrid.length + col);
		}
		//if in bottom row, union with VBOTTOM
		if (row == myGrid.length - 1) {
			myFinder.union(row*myGrid.length + col, VBOTTOM);
		}
		//if our neighbors are in bounds and are open, union them with us aka the current cell
		//aka (put them in same set as current cell)
		if (inBounds(row, col - 1) && isOpen(row, col - 1)) {
			myFinder.union(row*myGrid.length + col, row*myGrid.length + col - 1);
		}
		if (inBounds(row, col + 1) && isOpen(row, col + 1)) {
			myFinder.union(row*myGrid.length + col, row*myGrid.length + col + 1);
		}
		if (inBounds(row - 1, col) && isOpen(row - 1, col)) {
			myFinder.union(row*myGrid.length + col, (row - 1)*myGrid.length + col);
		}
		if (inBounds(row + 1, col) && isOpen(row + 1, col)) {
			myFinder.union(row*myGrid.length + col, (row + 1)*myGrid.length + col);
		}
		
		
	}

	@Override
	//if out of bounds, throw exception
	//returns true if current cell is marked as OPEN
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col] == OPEN;
		
	}

	@Override
	//if out of bounds, throw exception
	//returns true if current cell is in same set as VTOP
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(row*myGrid.length + col, VTOP);
	}

	@Override
	//returns true if VTOP and VBOTTOM are in the same set
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}

	@Override
	//returns the total number of open sites
	public int numberOfOpenSites() {
		return myOpenCount;
	}
}
