import java.util.Random;

public class zee {

    private static final int BOARD_SIZE = 8;
    private static final int MAX_FITNESS = 56;

    public static class State {
        private int[] queens;
        private int fitness;

        public State() {
            this.queens = new int[BOARD_SIZE];
            for (int i = 0; i < BOARD_SIZE; i++) {
                queens[i] = i;
            }
            this.fitness = computeFitness();
        }

        private int computeFitness() {
            int fitness = MAX_FITNESS;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = i + 1; j < BOARD_SIZE; j++) {
                    if (queens[i] == queens[j] || Math.abs(i - j) == Math.abs(queens[i] - queens[j])) {
                        fitness--;
                    }
                }
            }
            return fitness;
        }

        public State generateNeighbor() {
            State neighbor = new State();
            System.arraycopy(this.queens, 0, neighbor.queens, 0, BOARD_SIZE);
            int i = new Random().nextInt(BOARD_SIZE);
            int j = new Random().nextInt(BOARD_SIZE);
            swap(neighbor.queens, i, j);
            neighbor.fitness = neighbor.computeFitness();
            return neighbor;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int queen : queens) {
                sb.append(String.format("%3s", Integer.toBinaryString(queen)).replace(' ', '0'));
            }
            return sb.toString();
        }

        public int getFitness() {
            return this.fitness;
        }
    }

    public static State solve(int iterations) {
        if (iterations < 1) {
            throw new IllegalArgumentException("Number of iterations must be greater than 1");
        }
        State current = new State();
        State best = current;
        Random rand = new Random();
        for (int t = 1; t <= iterations; t++) {
            if (current.fitness == MAX_FITNESS) {
                break;
            }
            State next = current.generateNeighbor();
            int delta = next.fitness - current.fitness;
            if (delta > 0 || rand.nextDouble() < Math.exp(delta / t)) {
                current = next;
            }
            if (current.fitness > best.fitness) {
                best = current;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        State solution = zee.solve(1000);
        System.out.println("Solution: " + solution);
        System.out.println("Fitness: " + solution.getFitness());
    }
}
