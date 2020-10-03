import java.util.Scanner;

public class PercolationSimulator {
    private Percolation table;

    public PercolationSimulator(int row, int col, int trials, int interval) {
        int[] thresholds = new int[trials];
        for (int trial = 1; trial <= trials; trial++) {
            table = new Percolation(row, col);
            int openSites = 0;
            for (int i = 0; i < row * col; i++) {

                openRandom();
                openSites++;
                if (interval != 0 && i != interval && i % interval == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println();
                    }

                    System.out.println("-------------------");
                    System.out.printf("%dth Grid %dX%d:%n", trial, row, col);
                    table.printGrid();
                    System.out.printf("Open sites: %d%n", openSites);

                }

                if (table.percolates()) {
                    break;
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println();
            }

            System.out.println("-------------------");
            System.out.printf("%dth Grid %dX%d:%n", trial, row, col);
            table.printGrid();
            System.out.printf("Percolated with %d open sites%n", openSites);
            thresholds[trial - 1] = openSites;

        }
        double mean = arrayMean(thresholds);
        System.out.println("***********************");
        System.out.println("Simulation done");
        System.out.printf("Number of trials: %d%n", trials);
        System.out.printf("Dimensions: %dX%d%n", row, col);
        System.out.printf("Mean of open sites to percolate: %.1f%n", mean);
        System.out.printf("Mean in percentage: %.2f%%", (mean / (row * col)) * 100);
        System.out.println();
    }

    private void openRandom() {
        int row;
        int col;
        do {
            row = randInt(1, table.getRowCount() + 1);
            col = randInt(1, table.getColCount() + 1);

        } while (table.isOpen(row, col));
        table.open(row, col);
    }

    private int randInt(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    private double arrayMean(int[] array) {
        int len = array.length;
        double sum = 0;
        for (int elem : array) {
            sum += elem;
        }
        return sum / len;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose the dimensions of your grid");
        System.out.print("rows:");
        int row = in.nextInt();
        System.out.println();
        System.out.print("columns:");
        int col = in.nextInt();
        System.out.println();
        System.out.println("How many times you want to simulate?");
        int trials = in.nextInt();
        System.out.println("How many sites you want to see opening at once? Type 0 to only see when it percolates.");
        int intervals = in.nextInt();
        System.out.print("Simulation about to start");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.print(".");
            }
            System.out.print(".");
        }
        System.out.println();
        PercolationSimulator simulator = new PercolationSimulator(row, col, trials, intervals);

    }

}
