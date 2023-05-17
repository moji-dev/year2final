import java.util.Random;

public class NQueens {
    private int N;
    private int[] queens;
    private Random rand;
    private static final int MAX_ITER = 100000;

    public NQueens(int N) {
        this.N = N;
        queens = new int[N];
        rand = new Random();
    }

    private boolean isSolution() {
        return heuristic(queens) == 0;
    }

    private int heuristic(int[] state) {
        int h = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                if (state[i] == state[j] || Math.abs(state[i] - state[j]) == j - i)
                    h++;
        return h;
    }

    public void solve() {
        for (int i = 0; i < N; i++)
            queens[i] = rand.nextInt(N);

        int iter = 0;
        // say hi 40 times

        while (!isSolution() && iter++ < MAX_ITER) {
            int h = heuristic(queens);
            int[] newQueens = queens.clone();

            int i = rand.nextInt(N);
            int j = rand.nextInt(N);
            newQueens[i] = j;

            int newH = heuristic(newQueens);

            if (newH <= h || rand.nextDouble() < Math.exp((h - newH) / (double) iter))
                queens = newQueens;
        }

        if (isSolution())
            printQueens();
        else
            System.out.println("No solution found" + iter);
    }

    private void printQueens() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print((queens[i] == j ? "Q " : "# "));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NQueens problem = new NQueens(8);
        for (int i = 0; i < 1; i++) {
            problem.solve();
        }
    }
}
