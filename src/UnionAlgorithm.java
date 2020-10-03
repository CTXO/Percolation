public class UnionAlgorithm {

    public int[] tracker;

    public UnionAlgorithm(int n) {

        tracker = new int[n];
        for (int i = 0; i < n; i++) {
            tracker[i] = i;
        }
        // 0,1,2,3,4,5,6,7,8,9
        //tracker = {0, 0, 2, 3, 5, 3, 5, 4, 9, 3};
    }

    private int numNodes(int root) {
        int count = 0;
        for (int i = 0; i < tracker.length; i++) {
            if (findRoot(i) == root) {
                count++;
            }
        }
        return count;
    }

    public int findRoot(int n) {
        if (tracker[n] == n) {
            return n;
        } else {
            int parent = tracker[n];
            tracker[n] = tracker[parent];
            return findRoot(parent);
        }
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (numNodes(rootP) < numNodes(rootQ)) {
            tracker[rootP] = rootQ;
        } else {
            tracker[rootQ] = rootP;
        }
    }

    public void pointParent() {
        for (int i = 0; i < tracker.length; i++) {
            System.out.println(i + "->" + tracker[i]);
        }
    }


    public static void main(String[] args) {
        /*UnionAlgorithm test = new UnionAlgorithm(10);
        test.union(4, 5);
        test.union(3, 4);
        test.union(7, 8);
        test.union(7, 5);
        test.union(0, 1);
        test.pointParent();*/

    }
}
