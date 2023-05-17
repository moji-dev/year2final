import java.util.Random;

public class NQueens {
    private int N;
    private String queens;
    private Random rand;
    private static final int MAX_ITER = 10000;

    public NQueens(int N) {
        this.N = N;
        rand = new Random();
        queens = generateInitialState();
    }

    private String generateInitialState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String bin = Integer.toBinaryString(rand.nextInt(N));
            while (bin.length() < 3) {
                bin = "0" + bin;
            }
            sb.append(bin);
        }
        return sb.toString();
    }

    private int fitness(String state) {
        int fitness = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int a = Integer.parseInt(state.substring(3 * i, 3 * i + 3), 2);
                int b = Integer.parseInt(state.substring(3 * j, 3 * j + 3), 2);
                if (a != b && Math.abs(a - b) != j - i) {
                    fitness++;
                }
            }
        }
        return fitness;
    }

    public String solve() {
        int iter = 0;
        while (iter++ < MAX_ITER) {
            int fitness = fitness(queens);
            String newQueens = generateNewState(queens);

            int newFitness = fitness(newQueens);

            if (newFitness >= fitness || rand.nextDouble() < Math.exp((newFitness - fitness) / (double) iter))
                queens = newQueens;

            if (newFitness == N * (N - 1) / 2)
                break;
        }
        System.out.print(iter);

        return queens;
    }

    private String generateNewState(String state) {
        StringBuilder newState = new StringBuilder(state);
        int i = rand.nextInt(N) * 3;
        String bin = Integer.toBinaryString(rand.nextInt(N));
        while (bin.length() < 3) {
            bin = "0" + bin;
        }
        newState.replace(i, i + 3, bin);
        return newState.toString();
    }

    public static void main(String[] args) {
        NQueens problem = new NQueens(8);
        String solution = problem.solve();

        System.out.println(
                "Solution: " + solution + "\nFitness: " + problem.fitness(solution) + "\nIterations: " + MAX_ITER);
    }
}
