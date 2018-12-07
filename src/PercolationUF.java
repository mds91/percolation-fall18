
public class PercolationUF implements IPercolate {

	int[][] myGrid;
	int myOpenCount;
	IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	public PercolationUF(IUnionFind finder, int size) {
		myGrid = new int[size][size];
		myFinder = finder;
		myFinder.initialize(size*size + 2);
		VTOP = size*size;
		VBOTTOM = size*size + 1;
	}
	
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
		// TODO Auto-generated method stub
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		myGrid[row][col] = OPEN;
		if (row == 0) {
			myFinder.union(VTOP, row*myGrid.length + col);
		}
		if (row == myGrid.length - 1) {
			myFinder.union(row*myGrid.length + col, VBOTTOM);
		}
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
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col] == OPEN;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		// TODO Auto-generated method stub
		return myFinder.connected(row*myGrid.length + col, VTOP);
	}

	@Override
	public boolean percolates() {
		// TODO Auto-generated method stub
		return myFinder.connected(VTOP, VBOTTOM);
	}

	@Override
	public int numberOfOpenSites() {
		// TODO Auto-generated method stub
		return myOpenCount;
	}
}
