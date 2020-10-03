public class Percolation {
    private final boolean[] grid;
    private final UnionAlgorithm links;
    private final UnionAlgorithm vLinks;
    private final int lastElem;
    private final int rowCount;
    private final int colCount;

    public Percolation(int row, int col) {
        links = new UnionAlgorithm(row * col + 2);
        vLinks = new UnionAlgorithm(row * col + 2);
        grid = new boolean[row * col + 2];
        lastElem = grid.length - 1;
        rowCount = row;
        colCount = col;
        for (int i = 1; i <= col; i++) {
            links.union(0, i);
            vLinks.union(0, i);
            vLinks.union(lastElem, lastElem - i);
        }
    }


    private int convertIndex(int row, int col) {
        return (row - 1) * colCount + col;
    }

    private int toRow(int index) {
        return ((index - 1) / colCount) + 1;
    }

    private int toCol(int index) {
        return ((index - 1) % colCount) + 1;
    }


    public boolean isOpen(int row, int col) {
        return grid[convertIndex(row, col)];
    }

    public boolean percolates() {
        boolean percolation = vLinks.findRoot(0) == vLinks.findRoot(lastElem);
        return percolation;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }


    public void open(int row, int col) {
        int index = convertIndex(row, col);
        grid[index] = true;
        int[] neighbors = {index - 1, index + 1, index - colCount, index + colCount};
        for (int neighbor : neighbors) {
            boolean falseNeighbor = (index % colCount == 0 && neighbor % colCount == 1) ||
                    (index % colCount == 1 && neighbor % colCount == 0);

            if ((neighbor >= 1 && neighbor < lastElem)) {
                if (isOpen(toRow(neighbor), toCol(neighbor)) && (index % colCount == neighbor % colCount ||
                        !falseNeighbor)) {
                    links.union(index, neighbor);
                    vLinks.union(index, neighbor);
                }
            }

        }

    }

    public void printGrid() {
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 1; j <= colCount; j++) {
                if (grid[convertIndex(i, j)]) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }

            System.out.println();

        }
    }

    public static void main(String[] args) {
        Percolation test = new Percolation(4, 5);

/*        test.open(3, 5);
        test.open(4, 1);
        test.open(2, 5);
        test.open(1, 5);

        test.printGrid();*/
    }
}

